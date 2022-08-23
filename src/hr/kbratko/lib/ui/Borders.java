/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.lib.ui;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JComponent;

/**
 *
 * @author kbratko
 */
public final class Borders {

  private static final int DEFAULT_BORDER_WIDTH = 1;

  private static final Color COLOR_ERROR = Color.RED;
  private static final Color COLOR_WARNING = Color.ORANGE;
  private static final Color COLOR_SUCCESS = Color.GREEN;

  private Borders() {
  }

  public static void setBorderDefault(JComponent component) {
    try {
      component.setBorder(component
        .getClass()
        .getDeclaredConstructor()
        .newInstance()
        .getBorder());
    }
    catch (NoSuchMethodException |
           SecurityException |
           InstantiationException |
           IllegalAccessException |
           IllegalArgumentException |
           InvocationTargetException ex) {
      Logger.getLogger(Borders.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public static void setBorderError(JComponent component) {
    component.setBorder(BorderFactory.createLineBorder(COLOR_ERROR,
                                                       DEFAULT_BORDER_WIDTH));
  }

  public static void setBorderWarning(JComponent component) {
    component.setBorder(BorderFactory.createLineBorder(COLOR_WARNING,
                                                       DEFAULT_BORDER_WIDTH));
  }

  public static void setBorderSuccess(JComponent component) {
    component.setBorder(BorderFactory.createLineBorder(COLOR_SUCCESS,
                                                       DEFAULT_BORDER_WIDTH));
  }

}
