/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.dal.base.model;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

/**
 *
 * @author kbratko
 * @param <TKey>
 */
public abstract class BaseTableModel<TKey>
  implements TableModel<TKey> {

  protected final TKey _id;
  protected final UUID _guid;
  protected final TKey _createdBy;
  protected final TKey _updatedBy;
  protected final Optional<TKey> _deletedBy;

  protected final Date _createDate;
  protected final Date _updateDate;
  protected final Optional<Date> _deleteDate;

  public BaseTableModel(TKey id,
                        UUID guid,
                        TKey createdBy,
                        TKey updatedBy,
                        Optional<TKey> deletedBy,
                        Date createDate,
                        Date updateDate,
                        Optional<Date> deleteDate) {
    this._id = id;
    this._guid = guid;
    this._createdBy = createdBy;
    this._updatedBy = updatedBy;
    this._deletedBy = deletedBy;
    this._createDate = createDate;
    this._updateDate = updateDate;
    this._deleteDate = deleteDate;
  }

  @Override
  public TKey getId() {
    return _id;
  }

  @Override
  public UUID getGuid() {
    return _guid;
  }

  @Override
  public Date getCreateDate() {
    return _createDate;
  }

  @Override
  public TKey getCreatedBy() {
    return _createdBy;
  }

  @Override
  public Date getUpdateDate() {
    return _updateDate;
  }

  @Override
  public TKey getUpdatedBy() {
    return _updatedBy;
  }

  @Override
  public Optional<Date> getDeleteDate() {
    return _deleteDate;
  }

  @Override
  public Optional<TKey> getDeletedBy() {
    return _deletedBy;
  }

}
