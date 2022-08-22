/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.dal.concrete.repo.db.sql;

import hr.kbratko.dal.base.repo.db.sql.SqlDatabaseRepository;
import hr.kbratko.dal.base.repo.model.MovieTableModelRepository;
import hr.kbratko.dal.concrete.model.MovieTableModel;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

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
  private static final String SHOWING_DATE = "ShowingDate";
  private static final String DURATION_MINUTES = "DurationMinutes";
  private static final String DESCRIPTION = "Description";
  private static final String WEB_PATH = "WebPath";
  private static final String IMAGE_PATH = "ImagePath";
  private static final String IS_FAVORITE = "IsFavorite";

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
                               resultSet.getTimestamp(PUBLISHED_DATE),
                               resultSet.getTimestamp(SHOWING_DATE),
                               resultSet.getInt(DURATION_MINUTES),
                               description,
                               webPath,
                               imagePath,
                               resultSet.getBoolean(IS_FAVORITE));
  }

  @Override
  protected void parameterize(final CallableStatement statement,
                              final MovieTableModel model)
    throws Exception {
    statement.setString(TITLE, model.getTitle());
    statement.setString(ORIGINAL_TITLE, model.getOriginalTitle());
    statement.setTimestamp(PUBLISHED_DATE, new Timestamp(model
                           .getPublishedDate().getTime()));
    statement.setTimestamp(SHOWING_DATE, new Timestamp(model.getShowingDate()
                           .getTime()));
    statement.setInt(DURATION_MINUTES, model.getDurationMinutes());
    statement.setString(DESCRIPTION, model.getDescription().orElse(null));
    statement.setString(WEB_PATH, model.getWebPath().orElse(null));
    statement.setString(IMAGE_PATH, model.getImagePath().orElse(null));
    statement.setBoolean(IS_FAVORITE, model.isFavorite());
  }

}
