/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.dal.concrete.model;

import hr.kbratko.dal.base.model.BaseTableModel;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 *
 * @author kbratko
 */
public final class DirectorTableModel
  extends BaseTableModel<Integer> {

  private final int _movieFK;
  private final int _personFK;

  public DirectorTableModel(int movieFK, int personFK) {
    super();
    this._movieFK = movieFK;
    this._personFK = personFK;
  }

  public DirectorTableModel(int movieFK,
                            int personFK,
                            Integer id,
                            UUID guid,
                            Integer createdBy,
                            Integer updatedBy,
                            Integer deletedBy,
                            LocalDateTime createDate,
                            LocalDateTime updateDate,
                            LocalDateTime deleteDate) {
    super(id,
          guid,
          createdBy,
          updatedBy,
          deletedBy,
          createDate,
          updateDate,
          deleteDate);
    this._movieFK = movieFK;
    this._personFK = personFK;
  }

  public int getMovieFK() {
    return this._movieFK;
  }

  public int getPersonFK() {
    return this._personFK;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;

    if (obj == null || obj.getClass() != this.getClass())
      return false;

    var director = (DirectorTableModel) obj;

    return director._movieFK == this._movieFK &&
           director._personFK == this._personFK;
  }

  @Override
  public int hashCode() {
    return this._movieFK + this._personFK;
  }

  @Override
  public String toString() {
    return String.format("%d, %d %d", this._id, this._movieFK, this._personFK);
  }

}
