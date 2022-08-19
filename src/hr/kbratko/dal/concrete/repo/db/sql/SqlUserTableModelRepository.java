/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.dal.concrete.repo.db.sql;

import hr.kbratko.dal.concrete.status.ReadMethod;
import hr.kbratko.dal.base.status.StatusResult;
import hr.kbratko.dal.base.repo.db.sql.SqlDatabaseRepository;
import hr.kbratko.dal.base.repo.model.UserTableModelRepository;
import hr.kbratko.dal.concrete.model.UserTableModel;
import hr.kbratko.dal.concrete.status.CreateStatus;
import hr.kbratko.dal.concrete.status.DeleteStatus;
import hr.kbratko.dal.concrete.status.RegistrationStatus;
import hr.kbratko.dal.concrete.status.UpdateStatus;
import hr.kbratko.lib.extensions.Collections;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.Types;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import javax.sql.DataSource;

/**
 *
 * @author kbratko
 */
public final class SqlUserTableModelRepository
  extends SqlDatabaseRepository<Integer, UserTableModel>
  implements UserTableModelRepository {

  private static final String ID = "Id";
  private static final String GUID = "Guid";
  private static final String CREATED_BY = "CreatedBy";
  private static final String CREATE_DATE = "CreateDate";
  private static final String UPDATED_BY = "UpdatedBy";
  private static final String UPDATE_DATE = "UpdateDate";
  private static final String DELETED_BY = "DeletedBy";
  private static final String DELETE_DATE = "DeleteDate";
  private static final String USERNAME = "Username";
  private static final String PASSWORD_HASH = "PasswordHash";
  private static final String IS_ADMIN = "IsAdmin";

  private static final String PROCEDURE_USER_CREATE = "{ ? = CALL [dbo].[UserCreate] (?, ?, ?, ?) }";
  private static final String PROCEDURE_USER_DELETE = "{ ? = CALL [dbo].[UserDelete] (?, ?) }";
  private static final String PROCEDURE_USER_READ = "{ CALL [dbo].[UserRead] (?, ?) }";
  private static final String PROCEDURE_USER_UPDATE = "{ ? = CALL [dbo].[UserUpdate] (?, ?, ?, ?, ?) }";
  private static final String PROCEDURE_USER_LOGIN = "{ CALL [dbo].[UserLogin] (?, ?) }";
  private static final String PROCEDURE_USER_REGISTER = "{ ? = CALL [dbo].[UserRegister] (?, ?, ?) }";

  private static final int USER_MANAGER_ID = 1;
  private static final int USER_DEFAULT_ID = -1;

  @Override
  public UserTableModel model(ResultSet resultSet)
    throws Exception {
    int tmpDeletedBy = resultSet.getInt(DELETED_BY);
    Optional<Integer> deletedBy = resultSet.wasNull() ? Optional.empty() : Optional.of(tmpDeletedBy);

    Date tmpDeleteDate = resultSet.getDate(DELETE_DATE);
    Optional<Date> deleteDate = resultSet.wasNull() ? Optional.empty() : Optional.of(tmpDeleteDate);

    return new UserTableModel(
      resultSet.getString(USERNAME),
      resultSet.getString(PASSWORD_HASH),
      resultSet.getBoolean(IS_ADMIN),
      resultSet.getInt(ID),
      UUID.fromString(resultSet.getString(GUID)),
      resultSet.getInt(CREATED_BY),
      resultSet.getInt(UPDATED_BY),
      deletedBy,
      resultSet.getDate(CREATE_DATE),
      resultSet.getDate(UPDATE_DATE),
      deleteDate
    );
  }

  @Override
  public StatusResult<CreateStatus, UserTableModel> create(UserTableModel model)
    throws Exception {
    return create(model, Optional.empty());
  }

  @Override
  public StatusResult<CreateStatus, UserTableModel> create(UserTableModel model,
                                                           Optional<Integer> createdBy)
    throws Exception {
    DataSource dataSource = this.getDataSource();
    try ( Connection connection = dataSource.getConnection();
          CallableStatement statement = connection.prepareCall(PROCEDURE_USER_CREATE)) {

      statement.registerOutParameter(1, Types.INTEGER);
      statement.setString(2, model.getUsername());
      statement.setString(3, model.getPasswordHash());
      statement.setBoolean(4, model.isAdmin());
      statement.setInt(5, createdBy.orElse(USER_MANAGER_ID));

      try ( ResultSet resultSet = statement.executeQuery()) {
        UserTableModel returnedModel = resultSet.next() ? model(resultSet) : null;

        return new StatusResult<>(CreateStatus.fromInteger(statement.getInt(1)), returnedModel);
      }
    }
  }

  @Override
  public DeleteStatus delete(UserTableModel model)
    throws Exception {
    return delete(model.getGuid());
  }

  @Override
  public DeleteStatus delete(UUID guid)
    throws Exception {
    return delete(guid, Optional.empty());
  }

  @Override
  public DeleteStatus delete(UUID guid, Optional<Integer> deletedBy)
    throws Exception {
    DataSource dataSource = this.getDataSource();
    try ( Connection connection = dataSource.getConnection();
          CallableStatement statement = connection.prepareCall(PROCEDURE_USER_DELETE)) {

      statement.registerOutParameter(1, Types.INTEGER);
      statement.setString(2, guid.toString());
      statement.setInt(3, deletedBy.orElse(USER_MANAGER_ID));

      statement.execute();

      return DeleteStatus.fromInteger(statement.getInt(1));
    }
  }

  @Override
  public UserTableModel login(UserTableModel model, String password)
    throws Exception {
    return login(model.getUsername(), password);
  }

  @Override
  public UserTableModel login(String username, String password)
    throws Exception {
    DataSource dataSource = this.getDataSource();
    try ( Connection connection = dataSource.getConnection();
          CallableStatement statement = connection.prepareCall(PROCEDURE_USER_LOGIN)) {

      statement.setString(1, username);
      statement.setString(2, password);

      try ( ResultSet resultSet = statement.executeQuery()) {
        return resultSet.next() ? model(resultSet) : null;
      }
    }
  }

  @Override
  public Collection<UserTableModel> readAll()
    throws Exception {
    return read(ReadMethod.ALL, Optional.empty());
  }

  @Override
  public Collection<UserTableModel> readAllAvailable()
    throws Exception {
    return read(ReadMethod.ALL_AVAILABLE, Optional.empty());
  }

  @Override
  public UserTableModel readById(Integer id)
    throws Exception {
    return Collections.firstOrDefault(read(ReadMethod.ONE, Optional.of(id)));
  }

  @Override
  public UserTableModel readByIdAvailable(Integer id)
    throws Exception {
    return Collections.firstOrDefault(read(ReadMethod.ONE_AVAILABLE, Optional.of(id)));
  }

  private Collection<UserTableModel> read(ReadMethod method, Optional<Integer> id)
    throws Exception {
    DataSource dataSource = this.getDataSource();
    try ( Connection connection = dataSource.getConnection();
          CallableStatement statement = connection.prepareCall(PROCEDURE_USER_READ)) {

      statement.setInt(1, method.toInteger());
      statement.setInt(2, id.orElse(USER_DEFAULT_ID));

      Collection<UserTableModel> collection = new ArrayList<>();
      try ( ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next())
          collection.add(model(resultSet));

        return collection;
      }
    }
  }

  @Override
  public StatusResult<RegistrationStatus, UserTableModel> register(UserTableModel model,
                                                                   String password)
    throws Exception {
    return register(model, password, Optional.empty());
  }

  @Override
  public StatusResult<RegistrationStatus, UserTableModel> register(UserTableModel model,
                                                                   String password,
                                                                   Optional<Integer> createdBy)
    throws Exception {
    return register(model.getUsername(), password, createdBy);
  }

  @Override
  public StatusResult<RegistrationStatus, UserTableModel> register(String username,
                                                                   String password)
    throws Exception {
    return register(username, password, Optional.empty());
  }

  @Override
  public StatusResult<RegistrationStatus, UserTableModel> register(String username,
                                                                   String password,
                                                                   Optional<Integer> createdBy)
    throws Exception {
    DataSource dataSource = this.getDataSource();
    try ( Connection connection = dataSource.getConnection();
          CallableStatement statement = connection.prepareCall(PROCEDURE_USER_REGISTER)) {

      statement.registerOutParameter(1, Types.INTEGER);
      statement.setString(2, username);
      statement.setString(3, password);
      statement.setInt(4, createdBy.orElse(USER_MANAGER_ID));

      try ( ResultSet resultSet = statement.executeQuery()) {
        UserTableModel returnedModel = resultSet.next() ? model(resultSet) : null;

        return new StatusResult<>(RegistrationStatus.fromInteger(statement.getInt(1)), returnedModel);
      }
    }
  }

  @Override
  public UpdateStatus update(UserTableModel model)
    throws Exception {
    return update(model.getGuid(), model);
  }

  @Override
  public UpdateStatus update(UUID guid, UserTableModel model)
    throws Exception {
    return update(guid, model, Optional.empty());
  }

  @Override
  public UpdateStatus update(UUID guid, UserTableModel model, Optional<Integer> updatedBy)
    throws Exception {
    DataSource dataSource = this.getDataSource();
    try ( Connection connection = dataSource.getConnection();
          CallableStatement statement = connection.prepareCall(PROCEDURE_USER_UPDATE)) {

      statement.registerOutParameter(1, Types.INTEGER);
      statement.setString(2, guid.toString());
      statement.setString(3, model.getUsername());
      statement.setString(4, model.getPasswordHash());
      statement.setBoolean(5, model.isAdmin());
      statement.setInt(6, updatedBy.orElse(USER_MANAGER_ID));

      statement.execute();

      return UpdateStatus.fromInteger(statement.getInt(1));
    }
  }

}
