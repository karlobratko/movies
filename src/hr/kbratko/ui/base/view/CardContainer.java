/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.kbratko.ui.base.view;

import javax.swing.JPanel;

/**
 *
 * @author kbratko
 */
public interface CardContainer {

  public void showPanel(String identifier);

  public void addPanel(String identifier, JPanel panel);

}
