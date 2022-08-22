/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.bll.concrete.model;

import hr.kbratko.bll.base.model.BaseDomainModel;
import java.util.UUID;

/**
 *
 * @author kbratko
 */
public final class PersonDomainModel
  extends BaseDomainModel<Integer> {

  private final String _fName;
  private final String _lName;

  public PersonDomainModel(String fName,
                           String lName,
                           Integer id,
                           UUID guid,
                           boolean isAvailable) {
    super(id, guid, isAvailable);
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

    var user = (PersonDomainModel) obj;

    return user._fName.equals(this._fName) &&
           user._lName.equals(this._lName);
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
