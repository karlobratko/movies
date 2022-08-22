/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.dal.concrete.repo.db.sql;

import hr.kbratko.dal.base.repo.db.sql.SqlDatabaseRepository;
import hr.kbratko.dal.base.repo.model.ActorTableModelRepository;
import hr.kbratko.dal.concrete.model.ActorTableModel;
import java.sql.CallableStatement;
import java.sql.ResultSet;

/**
 *
 * @author kbratko
 */
public final class SqlActorTableModelRepository
  extends SqlDatabaseRepository<Integer, ActorTableModel>
  implements ActorTableModelRepository {

  private static final String MOVIE_FK = "MovieFK";
  private static final String PERSON_FK = "PersonFK";

  @Override
  protected String getModelName() {
    return "Actor";
  }

  @Override
  protected int getParameterCount() {
    return 2;
  }

  @Override
  protected void parameterize(final CallableStatement statement,
                              final ActorTableModel model)
    throws Exception {
    statement.setInt(MOVIE_FK, model.getMovieFK());
    statement.setInt(PERSON_FK, model.getPersonFK());
  }

  @Override
  protected ActorTableModel partiallyModel(final ResultSet resultSet)
    throws Exception {
    return new ActorTableModel(resultSet.getInt(MOVIE_FK),
                               resultSet.getInt(PERSON_FK));
  }

}
