/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.dal.concrete.repo.db.sql;

import hr.kbratko.dal.base.repo.db.sql.SqlDatabaseRepository;
import hr.kbratko.dal.base.repo.model.PersonTableModelRepository;
import hr.kbratko.dal.concrete.model.PersonTableModel;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.Optional;
import javax.sql.DataSource;

/**
 *
 * @author kbratko
 */
public final class SqlPersonTableModelRepository
  extends SqlDatabaseRepository<Integer, PersonTableModel>
  implements PersonTableModelRepository {

  private static final String FNAME = "FName";
  private static final String LNAME = "LName";
  private static final String DELETED_BY = "DeletedBy";

  private static final String PROCEDURE_DELETE_ALL = "{ ? = CALL [dbo].[PersonDeleteAll] (?) }";

  @Override
  protected String getModelName() {
    return "Person";
  }

  @Override
  protected int getParameterCount() {
    return 2;
  }

  @Override
  protected PersonTableModel partiallyModel(final ResultSet resultSet)
    throws Exception {
    return new PersonTableModel(resultSet.getString(FNAME),
                                resultSet.getString(LNAME));
  }

  @Override
  protected void parameterize(final CallableStatement statement,
                              final PersonTableModel model)
    throws Exception {
    statement.setString(FNAME, model.getFName());
    statement.setString(LNAME, model.getLName());
  }

  @Override
  public int deleteAll()
    throws Exception {
    return deleteAll(Optional.empty());
  }

  @Override
  public int deleteAll(Optional<Integer> deletedBy)
    throws Exception {
    DataSource dataSource = this.getDataSource();
    try ( Connection connection = dataSource.getConnection();
          CallableStatement statement = connection.prepareCall(
                              PROCEDURE_DELETE_ALL)) {

      statement.registerOutParameter(1, Types.INTEGER);
      if (deletedBy.isPresent())
        statement.setInt(DELETED_BY, deletedBy.get());
      else
        statement.setNull(DELETED_BY, Types.INTEGER);

      statement.execute();

      return statement.getInt(1);
    }
  }

}
