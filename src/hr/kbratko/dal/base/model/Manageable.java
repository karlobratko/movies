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

}
