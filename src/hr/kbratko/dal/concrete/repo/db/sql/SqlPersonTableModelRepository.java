/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.dal.concrete.repo.db.sql;

import hr.kbratko.dal.base.repo.db.sql.SqlDatabaseRepository;
import hr.kbratko.dal.base.repo.model.PersonTableModelRepository;
import hr.kbratko.dal.concrete.model.PersonTableModel;
import java.sql.CallableStatement;
import java.sql.ResultSet;

/**
 *
 * @author kbratko
 */
public final class SqlPersonTableModelRepository
  extends SqlDatabaseRepository<Integer, PersonTableModel>
  implements PersonTableModelRepository {

  private static final String FNAME = "FName";
  private static final String LNAME = "LName";

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

}
