/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.kbratko.dal.base.model;

/**
 *
 * @author kbratko
 * @param <TKey>
 */
public interface TableModel<TKey>
  extends Identifiable<TKey>,
          Manageable<TKey> {

}
