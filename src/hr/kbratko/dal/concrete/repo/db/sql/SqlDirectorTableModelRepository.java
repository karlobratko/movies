/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.dal.concrete.repo.db.sql;

import hr.kbratko.dal.base.repo.db.sql.SqlDatabaseRepository;
import hr.kbratko.dal.base.repo.model.DirectorTableModelRepository;
import hr.kbratko.dal.concrete.model.DirectorTableModel;
import java.sql.CallableStatement;
import java.sql.ResultSet;

/**
 *
 * @author kbratko
 */
public final class SqlDirectorTableModelRepository
  extends SqlDatabaseRepository<Integer, DirectorTableModel>
  implements DirectorTableModelRepository {

  private static final String MOVIE_FK = "MovieFK";
  private static final String PERSON_FK = "PersonFK";

  @Override
  protected String getModelName() {
    return "Director";
  }

  @Override
  protected int getParameterCount() {
    return 2;
  }

  @Override
  protected void parameterize(final CallableStatement statement,
                              final DirectorTableModel model)
    throws Exception {
    statement.setInt(MOVIE_FK, model.getMovieFK());
    statement.setInt(PERSON_FK, model.getPersonFK());
  }

  @Override
  protected DirectorTableModel partiallyModel(final ResultSet resultSet)
    throws Exception {
    return new DirectorTableModel(resultSet.getInt(MOVIE_FK),
                                  resultSet.getInt(PERSON_FK));
  }

}
