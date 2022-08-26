/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.ui.base.view;

import javax.swing.JPanel;

/**
 *
 * @author kbratko
 */
public abstract class BaseView extends JPanel {

  protected final CardContainer _cardContainer;

  public BaseView(CardContainer parentWindow) {
    this._cardContainer = parentWindow;
  }

  public CardContainer getCardContainer() {
    return _cardContainer;
  }

}
