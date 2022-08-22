/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.dal.base.repo.db.sql;

import hr.kbratko.dal.base.model.TableModel;
import hr.kbratko.dal.base.repo.TableModelRepository;
import hr.kbratko.dal.base.repo.db.DatabaseRepository;
import hr.kbratko.dal.base.status.StatusResult;
import hr.kbratko.dal.concrete.status.CreateStatus;
import hr.kbratko.dal.concrete.status.DeleteStatus;
import hr.kbratko.dal.concrete.status.ReadMethod;
import hr.kbratko.dal.concrete.status.UpdateStatus;
import hr.kbratko.lib.extensions.Collections;
import java.sql.CallableStatement;
import java.sql.Connection;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

/**
 *
 * @author kbratko
 * @param <TKey>
 * @param <TModel>
 */
public abstract class SqlDatabaseRepository<TKey, TModel extends TableModel<TKey>>
  implements DatabaseRepository,
             TableModelRepository<TKey, TModel> {

  private static final String PARAMETER_ID = "Id";
  private static final String PARAMETER_GUID = "Guid";
  private static final String PARAMETER_CREATED_BY = "CreatedBy";
  private static final String PARAMETER_UPDATED_BY = "UpdatedBy";
  private static final String PARAMETER_DELETED_BY = "DeletedBy";
  private static final String PARAMETER_METHOD = "Method";

  private static final String ID = "Id";
  private static final String GUID = "Guid";
  private static final String CREATED_BY = "CreatedBy";
  private static final String CREATE_DATE = "CreateDate";
  private static final String UPDATED_BY = "UpdatedBy";
  private static final String UPDATE_DATE = "UpdateDate";
  private static final String DELETED_BY = "DeletedBy";
  private static final String DELETE_DATE = "DeleteDate";

  private static String getParameterList(int parameterCount) {
    return String.join(",", "?".repeat(parameterCount).split(""));
  }

  protected abstract String getModelName();

  protected abstract int getParameterCount();

  protected abstract TModel partiallyModel(final ResultSet resultSet)
    throws Exception;

  protected abstract void parameterize(final CallableStatement statement, final TModel model)
    throws Exception;

  private String getCreateProcedureSignature() {
    return String.format("{ ? = CALL [dbo].[%sCreate] (%s) }",
                         this.getModelName(),
                         SqlDatabaseRepository.getParameterList(this
                           .getParameterCount() + 1));
  }

  private String getDeleteProcedureSignature() {
    return String.format("{ ? = CALL [dbo].[%sDelete] (?, ?) }",
                         this.getModelName());
  }

  private String getReadProcedureSignature() {
    return String.format("{ CALL [dbo].[%sRead] (?, ?) }",
                         this.getModelName());
  }

  private String getUpdateProcedureSignature() {
    return String.format("{ ? = CALL [dbo].[%sUpdate] (%s) }",
                         this.getModelName(),
                         SqlDatabaseRepository.getParameterList(this
                           .getParameterCount() + 2));
  }

  protected TModel model(final ResultSet resultSet)
    throws Exception {
    TModel model = partiallyModel(resultSet);

    TKey tmpDeletedBy = (TKey) resultSet.getObject(DELETED_BY);
    TKey deletedBy = resultSet.wasNull() ? null : tmpDeletedBy;

    Date tmpDeleteDate = resultSet.getTimestamp(DELETE_DATE);
    Date deleteDate = resultSet.wasNull() ? null : tmpDeleteDate;

    model.setId((TKey) resultSet.getObject(ID));
    model.setGuid(UUID.fromString(resultSet.getString(GUID)));
    model.setCreatedBy((TKey) resultSet.getObject(CREATED_BY));
    model.setUpdatedBy((TKey) resultSet.getObject(UPDATED_BY));
    model.setDeletedBy(deletedBy);
    model.setCreateDate(resultSet.getTimestamp(CREATE_DATE));
    model.setUpdateDate(resultSet.getTimestamp(UPDATE_DATE));
    model.setDeleteDate(deleteDate);

    return model;
  }

  @Override
  public DataSource getDataSource() {
    return DataSourceSingleton.getInstance();
  }

  @Override
  public StatusResult<CreateStatus, TModel> create(final TModel model)
    throws Exception {
    return create(model, Optional.empty());
  }

  @Override
  public StatusResult<CreateStatus, TModel> create(final TModel model,
                                                   final Optional<TKey> createdBy)
    throws Exception {
    DataSource dataSource = this.getDataSource();
    try ( Connection connection = dataSource.getConnection();
          CallableStatement statement = connection.prepareCall(
                              this.getCreateProcedureSignature())) {

      statement.registerOutParameter(1, Types.INTEGER);
      this.parameterize(statement, model);
      statement.setObject(PARAMETER_CREATED_BY, createdBy.orElse(null));

      try ( ResultSet resultSet = statement.executeQuery()) {
        TModel returnedModel = resultSet.next() ? this.model(resultSet) : null;

        return new StatusResult<>(CreateStatus.fromInteger(statement.getInt(1)),
                                  returnedModel);
      }
    }
  }

  @Override
  public DeleteStatus delete(final TModel model)
    throws Exception {
    return delete(model.getGuid());
  }

  @Override
  public DeleteStatus delete(final UUID guid)
    throws Exception {
    return delete(guid, Optional.empty());
  }

  @Override
  public DeleteStatus delete(final UUID guid, final Optional<TKey> deletedBy)
    throws Exception {
    DataSource dataSource = this.getDataSource();
    try ( Connection connection = dataSource.getConnection();
          CallableStatement statement = connection.prepareCall(
                              this.getDeleteProcedureSignature())) {

      statement.registerOutParameter(1, Types.INTEGER);
      statement.setString(PARAMETER_GUID, guid.toString());
      statement.setObject(PARAMETER_DELETED_BY, deletedBy.orElse(null));

      statement.execute();

      return DeleteStatus.fromInteger(statement.getInt(1));
    }
  }

  @Override
  public Collection<TModel> readAll()
    throws Exception {
    return read(ReadMethod.ALL, Optional.empty());
  }

  @Override
  public Collection<TModel> readAllAvailable()
    throws Exception {
    return read(ReadMethod.ALL_AVAILABLE, Optional.empty());
  }

  @Override
  public TModel readById(final TKey id)
    throws Exception {
    return Collections.firstOrDefault(read(ReadMethod.ONE, Optional.of(id)));
  }

  @Override
  public TModel readByIdAvailable(final TKey id)
    throws Exception {
    return Collections.firstOrDefault(read(ReadMethod.ONE_AVAILABLE, Optional
                                           .of(id)));
  }

  private Collection<TModel> read(final ReadMethod method,
                                  final Optional<TKey> id)
    throws Exception {
    DataSource dataSource = this.getDataSource();
    try ( Connection connection = dataSource.getConnection();
          CallableStatement statement = connection.prepareCall(
                              this.getReadProcedureSignature())) {

      statement.setInt(PARAMETER_METHOD, method.toInteger());
      statement.setObject(PARAMETER_ID, id.orElse(null));

      Collection<TModel> collection = new ArrayList<>();
      try ( ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next())
          collection.add(this.model(resultSet));

        return collection;
      }
    }
  }

  @Override
  public UpdateStatus update(final TModel model)
    throws Exception {
    return update(model.getGuid(), model);
  }

  @Override
  public UpdateStatus update(final UUID guid, final TModel model)
    throws Exception {
    return update(guid, model, Optional.empty());
  }

  @Override
  public UpdateStatus update(final UUID guid,
                             final TModel model,
                             final Optional<TKey> updatedBy)
    throws Exception {
    DataSource dataSource = this.getDataSource();
    try ( Connection connection = dataSource.getConnection();
          CallableStatement statement = connection.prepareCall(
                              this.getUpdateProcedureSignature())) {

      statement.registerOutParameter(1, Types.INTEGER);
      statement.setString(PARAMETER_GUID, guid.toString());
      this.parameterize(statement, model);
      statement.setObject(PARAMETER_UPDATED_BY, updatedBy.orElse(null));

      statement.execute();

      return UpdateStatus.fromInteger(statement.getInt(1));
    }
  }

}
