/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.bll.concrete.factory;

import hr.kbratko.bll.base.manager.model.DirectorDomainModelManager;
import hr.kbratko.bll.concrete.manager.general.GeneralDirectorDomainModelManager;
import hr.kbratko.dal.concrete.factory.DirectorTableModelRepositoryFactory;

/**
 *
 * @author kbratko
 */
public final class DirectorDomainModelManagerFactory {

  private DirectorDomainModelManagerFactory() {
  }

  public static DirectorDomainModelManager getManager() {
    return new GeneralDirectorDomainModelManager(
      DirectorTableModelRepositoryFactory.getRepository()
    );
  }

}
