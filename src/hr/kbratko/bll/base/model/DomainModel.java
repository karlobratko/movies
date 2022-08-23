/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.bll.base.model;

import hr.kbratko.dal.base.model.Identifiable;

/**
 *
 * @author kbratko
 * @param <TKey>
 */
public interface DomainModel<TKey>
  extends Identifiable<TKey> {

  boolean isAvailable();

  void setIsAvailable(boolean isAvailable);

}
