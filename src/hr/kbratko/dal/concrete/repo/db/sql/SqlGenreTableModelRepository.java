/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.dal.concrete.repo.db.sql;

import hr.kbratko.dal.base.repo.db.sql.SqlDatabaseRepository;
import hr.kbratko.dal.base.repo.model.GenreTableModelRepository;
import hr.kbratko.dal.concrete.model.GenreTableModel;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import javax.sql.DataSource;

/**
 *
 * @author kbratko
 */
public final class SqlGenreTableModelRepository
  extends SqlDatabaseRepository<Integer, GenreTableModel>
  implements GenreTableModelRepository {

  private static final String NAME = "Name";
  private static final String DELETED_BY = "DeletedBy";

  private static final String PARAMETER_MOVIE_FK = "MovieFK";

  private static final String PROCEDURE_DELETE_ALL = "{ ? = CALL [dbo].[GenreDeleteAll] (?) }";
  private static final String PROCEDURE_READ_BY_MOVIE_FK = "{ CALL [dbo].[GenreReadByMovieFK] (?) }";

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

  @Override
  public Collection<GenreTableModel> readByMovieFK(int movieFK)
    throws Exception {
    DataSource dataSource = this.getDataSource();
    try ( Connection connection = dataSource.getConnection();
          CallableStatement statement = connection.prepareCall(
                              PROCEDURE_READ_BY_MOVIE_FK)) {

      statement.setInt(PARAMETER_MOVIE_FK, movieFK);

      Collection<GenreTableModel> collection = new ArrayList<>();
      try ( ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next())
          collection.add(this.model(resultSet));

        return collection;
      }
    }
  }

}
