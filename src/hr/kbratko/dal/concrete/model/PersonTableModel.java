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
public final class PersonTableModel
  extends BaseTableModel<Integer> {

  private final String _fName;
  private final String _lName;

  public PersonTableModel(String fName, String lName) {
    super();
    this._fName = fName;
    this._lName = lName;
  }

  public PersonTableModel(String fName,
                          String lName,
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
    this._fName = fName;
    this._lName = lName;
  }

  public String getFName() {
    return this._fName;
  }

  public String getLName() {
    return this._lName;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;

    if (obj == null || obj.getClass() != this.getClass())
      return false;

    var person = (PersonTableModel) obj;

    return person._fName.equals(this._fName) &&
           person._lName.equals(this._lName);
  }

    @Override
  public int hashCode() {
    return this._fName.hashCode() + this._lName.hashCode();
  }

  @Override
  public String toString() {
    return String.format("%d, %s %s", this._id, this._fName, this._lName);
  }

}
