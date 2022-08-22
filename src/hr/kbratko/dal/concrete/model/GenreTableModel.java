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
public final class GenreTableModel
  extends BaseTableModel<Integer> {

  private final String _name;

  public GenreTableModel(String name) {
    super();
    this._name = name;
  }

  public GenreTableModel(String name,
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
    this._name = name;
  }

  public String getName() {
    return this._name;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;

    if (obj == null || obj.getClass() != this.getClass())
      return false;

    var genre = (GenreTableModel) obj;

    return genre._name.equals(this._name);
  }

  @Override
  public int hashCode() {
    return this._name.hashCode();
  }

  @Override
  public String toString() {
    return String.format("%d, %s", this._id, this._name);
  }

}
