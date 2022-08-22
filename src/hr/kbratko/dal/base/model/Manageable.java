/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.kbratko.dal.base.model;

import java.util.Date;
import java.util.Optional;

/**
 *
 * @author kbratko
 * @param <TKey>
 */
public interface Manageable<TKey> {

  Date getCreateDate();

  TKey getCreatedBy();

  Date getUpdateDate();

  TKey getUpdatedBy();

  Optional<Date> getDeleteDate();

  Optional<TKey> getDeletedBy();

  void setCreateDate(final Date createDate);

  void setCreatedBy(final TKey createdBy);

  void setUpdateDate(final Date updateDate);

  void setUpdatedBy(final TKey updatedBy);

  void setDeleteDate(final Date deleteDate);

  void setDeletedBy(final TKey deletedBy);

}
