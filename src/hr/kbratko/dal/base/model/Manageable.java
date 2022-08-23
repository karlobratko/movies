/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.kbratko.dal.base.model;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 *
 * @author kbratko
 * @param <TKey>
 */
public interface Manageable<TKey> {

  LocalDateTime getCreateDate();

  TKey getCreatedBy();

  LocalDateTime getUpdateDate();

  TKey getUpdatedBy();

  Optional<LocalDateTime> getDeleteDate();

  Optional<TKey> getDeletedBy();

  void setCreateDate(final LocalDateTime createDate);

  void setCreatedBy(final TKey createdBy);

  void setUpdateDate(final LocalDateTime updateDate);

  void setUpdatedBy(final TKey updatedBy);

  void setDeleteDate(final LocalDateTime deleteDate);

  void setDeletedBy(final TKey deletedBy);

}
