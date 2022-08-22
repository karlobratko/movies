/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.dal.concrete.model;

import hr.kbratko.dal.base.model.BaseTableModel;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author kbratko
 */
public final class MovieGenreTableModel
  extends BaseTableModel<Integer> {

  private final int _movieFK;
  private final int _genreFK;

  public MovieGenreTableModel(int movieFK, int genreFK) {
    super();
    this._movieFK = movieFK;
    this._genreFK = genreFK;
  }

  public MovieGenreTableModel(int movieFK,
                              int genreFK,
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
    this._movieFK = movieFK;
    this._genreFK = genreFK;
  }

  public int getMovieFK() {
    return this._movieFK;
  }

  public int getGenreFK() {
    return this._genreFK;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;

    if (obj == null || obj.getClass() != this.getClass())
      return false;

    var movieGenre = (MovieGenreTableModel) obj;

    return movieGenre._movieFK == this._movieFK &&
           movieGenre._genreFK == this._genreFK;
  }

  @Override
  public int hashCode() {
    return this._movieFK + this._genreFK;
  }

  @Override
  public String toString() {
    return String.format("%d, %d %d", this._id, this._movieFK, this._genreFK);
  }

}
