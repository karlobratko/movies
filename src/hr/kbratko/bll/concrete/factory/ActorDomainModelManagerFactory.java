/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.bll.concrete.factory;

import hr.kbratko.bll.base.manager.model.ActorDomainModelManager;
import hr.kbratko.bll.concrete.manager.general.GeneralActorDomainModelManager;
import hr.kbratko.dal.concrete.factory.ActorTableModelRepositoryFactory;

/**
 *
 * @author kbratko
 */
public final class ActorDomainModelManagerFactory {

  private ActorDomainModelManagerFactory() {
  }

  public static ActorDomainModelManager getManager() {
    return new GeneralActorDomainModelManager(
      ActorTableModelRepositoryFactory.getRepository()
    );
  }

}
