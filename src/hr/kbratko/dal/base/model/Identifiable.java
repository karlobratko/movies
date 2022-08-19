/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.dal.base.model;

import java.util.UUID;

/**
 *
 * @author kbratko
 * @param <TKey>
 */
public interface Identifiable<TKey> {

  TKey getId();

  UUID getGuid();

}
