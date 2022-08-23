/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.dal.base.model;

import java.time.LocalDateTime;
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

  protected LocalDateTime _createDate;
  protected LocalDateTime _updateDate;
  protected Optional<LocalDateTime> _deleteDate;

  public BaseTableModel() {
  }

  public BaseTableModel(TKey id,
                        UUID guid,
                        TKey createdBy,
                        TKey updatedBy,
                        TKey deletedBy,
                        LocalDateTime createDate,
                        LocalDateTime updateDate,
                        LocalDateTime deleteDate) {
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
  public LocalDateTime getCreateDate() {
    return this._createDate;
  }

  @Override
  public TKey getCreatedBy() {
    return this._createdBy;
  }

  @Override
  public LocalDateTime getUpdateDate() {
    return this._updateDate;
  }

  @Override
  public TKey getUpdatedBy() {
    return this._updatedBy;
  }

  @Override
  public Optional<LocalDateTime> getDeleteDate() {
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
  public void setCreateDate(final LocalDateTime createDate) {
    this._createDate = createDate;
  }

  @Override
  public void setCreatedBy(final TKey createdBy) {
    this._createdBy = createdBy;
  }

  @Override
  public void setUpdateDate(final LocalDateTime updateDate) {
    this._updateDate = updateDate;
  }

  @Override
  public void setUpdatedBy(final TKey updatedBy) {
    this._updatedBy = updatedBy;
  }

  @Override
  public void setDeleteDate(final LocalDateTime deleteDate) {
    this._deleteDate = Optional.ofNullable(deleteDate);
  }

  @Override
  public void setDeletedBy(final TKey deletedBy) {
    this._deletedBy = Optional.ofNullable(deletedBy);
  }

}
