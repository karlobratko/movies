/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.bll.concrete.factory;

import hr.kbratko.bll.concrete.manager.UserDomainModelManager;

/**
 *
 * @author kbratko
 */
public final class UserDomainModelManagerFactory {

  private UserDomainModelManagerFactory() {
  }

  public static hr.kbratko.bll.base.manager.model.UserDomainModelManager getManager() {
    return new UserDomainModelManager();
  }

}
