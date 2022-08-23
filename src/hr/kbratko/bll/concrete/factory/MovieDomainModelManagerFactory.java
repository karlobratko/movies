/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.bll.concrete.factory;

import hr.kbratko.bll.base.manager.model.MovieDomainModelManager;
import hr.kbratko.bll.concrete.manager.general.GeneralMovieDomainModelManager;
import hr.kbratko.dal.concrete.factory.MovieTableModelRepositoryFactory;

/**
 *
 * @author kbratko
 */
public final class MovieDomainModelManagerFactory {

  private MovieDomainModelManagerFactory() {
  }

  public static MovieDomainModelManager getManager() {
    return new GeneralMovieDomainModelManager(
      MovieTableModelRepositoryFactory.getRepository()
    );
  }

}
