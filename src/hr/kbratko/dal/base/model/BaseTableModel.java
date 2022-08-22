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

  protected TKey _id;
  protected UUID _guid;
  protected TKey _createdBy;
  protected TKey _updatedBy;
  protected Optional<TKey> _deletedBy;

  protected Date _createDate;
  protected Date _updateDate;
  protected Optional<Date> _deleteDate;

  public BaseTableModel() {
  }

  public BaseTableModel(TKey id,
                        UUID guid,
                        TKey createdBy,
                        TKey updatedBy,
                        TKey deletedBy,
                        Date createDate,
                        Date updateDate,
                        Date deleteDate) {
    this._id = id;
    this._guid = guid;
    this._createdBy = createdBy;
    this._updatedBy = updatedBy;
    this._deletedBy = Optional.ofNullable(deletedBy);
    this._createDate = createDate;
    this._updateDate = updateDate;
    this._deleteDate = Optional.ofNullable(deleteDate);
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
  public Date getCreateDate() {
    return this._createDate;
  }

  @Override
  public TKey getCreatedBy() {
    return this._createdBy;
  }

  @Override
  public Date getUpdateDate() {
    return this._updateDate;
  }

  @Override
  public TKey getUpdatedBy() {
    return this._updatedBy;
  }

  @Override
  public Optional<Date> getDeleteDate() {
    return this._deleteDate;
  }

  @Override
  public Optional<TKey> getDeletedBy() {
    return this._deletedBy;
  }

  @Override
  public void setId(final TKey id) {
    this._id = id;
  }

  @Override
  public void setGuid(final UUID guid) {
    this._guid = guid;
  }

  @Override
  public void setCreateDate(final Date createDate) {
    this._createDate = createDate;
  }

  @Override
  public void setCreatedBy(final TKey createdBy) {
    this._createdBy = createdBy;
  }

  @Override
  public void setUpdateDate(final Date updateDate) {
    this._updateDate = updateDate;
  }

  @Override
  public void setUpdatedBy(final TKey updatedBy) {
    this._updatedBy = updatedBy;
  }

  @Override
  public void setDeleteDate(final Date deleteDate) {
    this._deleteDate = Optional.ofNullable(deleteDate);
  }

  @Override
  public void setDeletedBy(final TKey deletedBy) {
    this._deletedBy = Optional.ofNullable(deletedBy);
  }

}
