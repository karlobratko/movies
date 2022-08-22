/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.dal.concrete.model;

import hr.kbratko.dal.base.model.BaseTableModel;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

/**
 *
 * @author kbratko
 */
public final class MovieTableModel
  extends BaseTableModel<Integer> {

  private final String _title;
  private final String _originalTitle;
  private final Date _publishedDate;
  private final Date _showingDate;
  private final int _durationMinutes;
  private final Optional<String> _description;
  private final Optional<String> _webPath;
  private final Optional<String> _imagePath;
  private final boolean _isFavorite;

  public MovieTableModel(String title,
                         String originalTitle,
                         Date publishedDate,
                         Date showingDate,
                         int durationMinutes,
                         String description,
                         String webPath,
                         String imagePath,
                         boolean isFavorite) {
    super();
    this._title = title;
    this._originalTitle = originalTitle;
    this._publishedDate = publishedDate;
    this._showingDate = showingDate;
    this._durationMinutes = durationMinutes;
    this._description = Optional.ofNullable(description);
    this._webPath = Optional.ofNullable(webPath);
    this._imagePath = Optional.ofNullable(imagePath);
    this._isFavorite = isFavorite;
  }

  public MovieTableModel(String title,
                         String originalTitle,
                         Date publishedDate,
                         Date showingDate,
                         int durationMinutes,
                         String description,
                         String webPath,
                         String imagePath,
                         boolean isFavorite,
                         Integer id,
                         UUID guid,
                         Integer createdBy,
                         Integer updatedBy,
                         Integer deletedBy,
                         Date createDate,
                         Date updateDate,
                         Date deleteDate) {
    super(id,
          guid,
          createdBy,
          updatedBy,
          deletedBy,
          createDate,
          updateDate,
          deleteDate);
    this._title = title;
    this._originalTitle = originalTitle;
    this._publishedDate = publishedDate;
    this._showingDate = showingDate;
    this._durationMinutes = durationMinutes;
    this._description = Optional.ofNullable(description);
    this._webPath = Optional.ofNullable(webPath);
    this._imagePath = Optional.ofNullable(imagePath);
    this._isFavorite = isFavorite;
  }

  public String getTitle() {
    return this._title;
  }

  public String getOriginalTitle() {
    return this._originalTitle;
  }

  public Date getPublishedDate() {
    return this._publishedDate;
  }

  public Date getShowingDate() {
    return this._showingDate;
  }

  public int getDurationMinutes() {
    return this._durationMinutes;
  }

  public Optional<String> getDescription() {
    return this._description;
  }

  public Optional<String> getWebPath() {
    return _webPath;
  }

  public Optional<String> getImagePath() {
    return this._imagePath;
  }

  public boolean isFavorite() {
    return this._isFavorite;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;

    if (obj == null || obj.getClass() != this.getClass())
      return false;

    var movie = (MovieTableModel) obj;

    return movie._originalTitle.equals(this._originalTitle) &&
           movie._publishedDate.equals(this._publishedDate);
  }

  @Override
  public int hashCode() {
    return this._originalTitle.hashCode() + this._publishedDate.hashCode();
  }

  @Override
  public String toString() {
    return String.format("%d, %s", this._id, this._publishedDate);
  }

}
