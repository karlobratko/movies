/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.bll.concrete.factory;

import hr.kbratko.bll.base.manager.model.PersonDomainModelManager;
import hr.kbratko.bll.concrete.manager.general.GeneralPersonDomainModelManager;
import hr.kbratko.dal.concrete.factory.PersonTableModelRepositoryFactory;

/**
 *
 * @author kbratko
 */
public final class PersonDomainModelManagerFactory {

  private PersonDomainModelManagerFactory() {
  }

  public static PersonDomainModelManager getManager() {
    return new GeneralPersonDomainModelManager(
      PersonTableModelRepositoryFactory.getRepository()
    );
  }

}
