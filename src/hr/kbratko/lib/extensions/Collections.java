/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.lib.extensions;

import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author kbratko
 */
public final class Collections {

  private Collections() {
  }

  public static <T> T firstOrDefault(Collection<T> collection) {
    Iterator<T> iterator = collection.iterator();
    return iterator.hasNext() ? iterator.next() : null;
  }

}
