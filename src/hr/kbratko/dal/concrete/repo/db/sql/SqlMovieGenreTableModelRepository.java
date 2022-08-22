/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.dal.concrete.repo.db.sql;

import hr.kbratko.dal.base.repo.db.sql.SqlDatabaseRepository;
import hr.kbratko.dal.base.repo.model.MovieGenreTableModelRepository;
import hr.kbratko.dal.concrete.model.MovieGenreTableModel;
import java.sql.CallableStatement;
import java.sql.ResultSet;

/**
 *
 * @author kbratko
 */
public final class SqlMovieGenreTableModelRepository
  extends SqlDatabaseRepository<Integer, MovieGenreTableModel>
  implements MovieGenreTableModelRepository {

  private static final String MOVIE_FK = "MovieFK";
  private static final String GENRE_FK = "GenreFK";

  @Override
  protected String getModelName() {
    return "MovieGenre";
  }

  @Override
  protected int getParameterCount() {
    return 2;
  }

  @Override
  protected void parameterize(final CallableStatement statement,
                              final MovieGenreTableModel model)
    throws Exception {
    statement.setInt(MOVIE_FK, model.getMovieFK());
    statement.setInt(GENRE_FK, model.getGenreFK());
  }

  @Override
  protected MovieGenreTableModel partiallyModel(final ResultSet resultSet)
    throws Exception {
    return new MovieGenreTableModel(resultSet.getInt(MOVIE_FK),
                                    resultSet.getInt(GENRE_FK));
  }

}
