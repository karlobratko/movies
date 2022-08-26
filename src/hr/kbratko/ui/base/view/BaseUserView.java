/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.ui.base.view;

import hr.kbratko.bll.concrete.model.UserDomainModel;

/**
 *
 * @author kbratko
 */
public abstract class BaseUserView extends BaseView {

  private final UserDomainModel _loggedInUser;

  public BaseUserView(UserDomainModel loggedInUser, CardContainer parentWindow) {
    super(parentWindow);
    this._loggedInUser = loggedInUser;
  }

  public UserDomainModel getLoggedInUser() {
    return _loggedInUser;
  }

}
