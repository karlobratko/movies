/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.dal.concrete.repo.db.sql;

import hr.kbratko.dal.base.repo.db.sql.SqlDatabaseRepository;
import hr.kbratko.dal.base.repo.model.GenreTableModelRepository;
import hr.kbratko.dal.concrete.model.GenreTableModel;
import java.sql.CallableStatement;
import java.sql.ResultSet;

/**
 *
 * @author kbratko
 */
public final class SqlGenreTableModelRepository
  extends SqlDatabaseRepository<Integer, GenreTableModel>
  implements GenreTableModelRepository {

  private static final String NAME = "Name";

  @Override
  protected String getModelName() {
    return "Genre";
  }

  @Override
  protected int getParameterCount() {
    return 1;
  }

  @Override
  protected void parameterize(final CallableStatement statement,
                              final GenreTableModel model)
    throws Exception {
    statement.setString(NAME, model.getName());
  }

  @Override
  protected GenreTableModel partiallyModel(final ResultSet resultSet)
    throws Exception {
    return new GenreTableModel(resultSet.getString(NAME));
  }

}
