/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.bll.concrete.factory;

import hr.kbratko.bll.base.manager.model.UserDomainModelManager;
import hr.kbratko.bll.concrete.manager.general.GeneralUserDomainModelManager;
import hr.kbratko.dal.concrete.factory.UserTableModelRepositoryFactory;

/**
 *
 * @author kbratko
 */
public final class UserDomainModelManagerFactory {

  private UserDomainModelManagerFactory() {
  }

  public static UserDomainModelManager getManager() {
    return new GeneralUserDomainModelManager(
      UserTableModelRepositoryFactory.getRepository()
    );
  }

}
