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
public final class GenreDomainModel
  extends BaseDomainModel<Integer> {

  private final String _name;

  public GenreDomainModel(String name) {
    super();
    this._name = name;
  }

  public GenreDomainModel(String name,
                          Integer id,
                          UUID guid,
                          boolean isAvailable) {
    super(id, guid, isAvailable);
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

    var genre = (GenreDomainModel) obj;

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
