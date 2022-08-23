/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.dal.concrete.repo.db.sql;

import hr.kbratko.dal.base.status.StatusResult;
import hr.kbratko.dal.base.repo.db.sql.SqlDatabaseRepository;
import hr.kbratko.dal.base.repo.model.UserTableModelRepository;
import hr.kbratko.dal.concrete.model.UserTableModel;
import hr.kbratko.dal.concrete.status.RegistrationStatus;
import java.util.Optional;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.Types;
import java.sql.ResultSet;
import javax.sql.DataSource;

/**
 *
 * @author kbratko
 */
public final class SqlUserTableModelRepository
  extends SqlDatabaseRepository<Integer, UserTableModel>
  implements UserTableModelRepository {

  private static final String CREATED_BY = "CreatedBy";
  private static final String USERNAME = "Username";
  private static final String PASSWORD_HASH = "PasswordHash";
  private static final String PASSWORD = "Password";
  private static final String IS_ADMIN = "IsAdmin";

  private static final String PROCEDURE_USER_LOGIN = "{ CALL [dbo].[UserLogin] (?, ?) }";
  private static final String PROCEDURE_USER_REGISTER = "{ ? = CALL [dbo].[UserRegister] (?, ?, ?) }";

  @Override
  protected String getModelName() {
    return "User";
  }

  @Override
  protected int getParameterCount() {
    return 3;
  }

  @Override
  protected UserTableModel partiallyModel(final ResultSet resultSet)
    throws Exception {
    return new UserTableModel(resultSet.getString(USERNAME),
                              resultSet.getString(PASSWORD_HASH),
                              resultSet.getBoolean(IS_ADMIN));
  }

  @Override
  protected void parameterize(final CallableStatement statement,
                              final UserTableModel model)
    throws Exception {
    statement.setString(USERNAME, model.getUsername());
    statement.setString(PASSWORD_HASH, model.getPasswordHash());
    statement.setBoolean(IS_ADMIN, model.isAdmin());
  }

  @Override
  public UserTableModel login(final UserTableModel model, final String password)
    throws Exception {
    return login(model.getUsername(), password);
  }

  @Override
  public UserTableModel login(final String username, final String password)
    throws Exception {
    DataSource dataSource = this.getDataSource();
    try ( Connection connection = dataSource.getConnection();
          CallableStatement statement = connection.prepareCall(
                              PROCEDURE_USER_LOGIN)) {

      statement.setString(USERNAME, username);
      statement.setString(PASSWORD, password);

      try ( ResultSet resultSet = statement.executeQuery()) {
        return resultSet.next() ? model(resultSet) : null;
      }
    }
  }

  @Override
  public StatusResult<RegistrationStatus, UserTableModel> register(
    final UserTableModel model,
    final String password)
    throws Exception {
    return register(model, password, Optional.empty());
  }

  @Override
  public StatusResult<RegistrationStatus, UserTableModel> register(
    final UserTableModel model,
    final String password,
    final Optional<Integer> createdBy)
    throws Exception {
    return register(model.getUsername(), password, createdBy);
  }

  @Override
  public StatusResult<RegistrationStatus, UserTableModel> register(
    final String username,
    final String password)
    throws Exception {
    return register(username, password, Optional.empty());
  }

  @Override
  public StatusResult<RegistrationStatus, UserTableModel> register(
    final String username,
    final String password,
    final Optional<Integer> createdBy)
    throws Exception {
    DataSource dataSource = this.getDataSource();
    try ( Connection connection = dataSource.getConnection();
          CallableStatement statement = connection.prepareCall(
                              PROCEDURE_USER_REGISTER)) {

      statement.registerOutParameter(1, Types.INTEGER);
      statement.setString(USERNAME, username);
      statement.setString(PASSWORD, password);
      if (createdBy.isPresent())
        statement.setInt(CREATED_BY, createdBy.get());
      else
        statement.setNull(CREATED_BY, Types.NULL);

      try ( ResultSet resultSet = statement.executeQuery()) {
        UserTableModel returnedModel = resultSet.next() ? model(resultSet)
                                         : null;

        return new StatusResult<>(RegistrationStatus.fromInteger(statement
          .getInt(1)), returnedModel);
      }
    }
  }

}
