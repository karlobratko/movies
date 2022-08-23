/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.dal.concrete.repo.db.sql;

import hr.kbratko.dal.base.repo.db.sql.SqlDatabaseRepository;
import hr.kbratko.dal.base.repo.model.MovieTableModelRepository;
import hr.kbratko.dal.concrete.model.MovieTableModel;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Optional;
import javax.sql.DataSource;

/**
 *
 * @author kbratko
 */
public final class SqlMovieTableModelRepository
  extends SqlDatabaseRepository<Integer, MovieTableModel>
  implements MovieTableModelRepository {

  private static final String TITLE = "Title";
  private static final String ORIGINAL_TITLE = "OriginalTitle";
  private static final String PUBLISHED_DATE = "PublishedDate";
  private static final String DURATION_MINUTES = "DurationMinutes";
  private static final String DESCRIPTION = "Description";
  private static final String WEB_PATH = "WebPath";
  private static final String IMAGE_PATH = "ImagePath";
  private static final String IS_FAVORITE = "IsFavorite";
  private static final String DELETED_BY = "DeletedBy";

  private static final String PROCEDURE_DELETE_ALL = "{ ? = CALL [dbo].[MovieDeleteAll] (?) }";

  @Override
  protected String getModelName() {
    return "Movie";
  }

  @Override
  protected int getParameterCount() {
    return 8;
  }

  @Override
  protected MovieTableModel partiallyModel(final ResultSet resultSet)
    throws Exception {
    String tmpDescription = resultSet.getString(DESCRIPTION);
    String description = resultSet.wasNull() ? null : tmpDescription;

    String tmpWebPath = resultSet.getString(WEB_PATH);
    String webPath = resultSet.wasNull() ? null : tmpWebPath;

    String tmpImagePath = resultSet.getString(IMAGE_PATH);
    String imagePath = resultSet.wasNull() ? null : tmpImagePath;

    return new MovieTableModel(resultSet.getString(TITLE),
                               resultSet.getString(ORIGINAL_TITLE),
                               resultSet
                                 .getTimestamp(PUBLISHED_DATE)
                                 .toLocalDateTime(),
                               resultSet.getInt(DURATION_MINUTES),
                               description,
                               webPath,
                               imagePath,
                               resultSet.getBoolean(IS_FAVORITE)
    );
  }

  @Override
  protected void parameterize(final CallableStatement statement,
                              final MovieTableModel model)
    throws Exception {
    statement.setString(TITLE, model.getTitle());
    statement.setString(ORIGINAL_TITLE, model.getOriginalTitle());
    statement.setTimestamp(PUBLISHED_DATE,
                           Timestamp.valueOf(model.getPublishedDate()));
    statement.setInt(DURATION_MINUTES, model.getDurationMinutes());
    statement.setString(DESCRIPTION, model.getDescription().orElse(null));
    statement.setString(WEB_PATH, model.getWebPath().orElse(null));
    statement.setString(IMAGE_PATH, model.getImagePath().orElse(null));
    statement.setBoolean(IS_FAVORITE, model.isFavorite());
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
