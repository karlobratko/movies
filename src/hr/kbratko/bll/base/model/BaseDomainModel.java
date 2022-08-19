/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.bll.base.model;

import java.util.UUID;

/**
 *
 * @author kbratko
 * @param <TKey>
 */
public abstract class BaseDomainModel<TKey>
  implements DomainModel<TKey> {

  protected final TKey _id;
  protected final UUID _guid;
  protected final boolean _isAvailable;

  public BaseDomainModel(TKey id, UUID guid, boolean isAvailable) {
    this._id = id;
    this._guid = guid;
    this._isAvailable = isAvailable;
  }

  @Override
  public TKey getId() {
    return this._id;
  }

  @Override
  public UUID getGuid() {
    return this._guid;
  }

  @Override
  public boolean isAvailable() {
    return this._isAvailable;
  }

}
