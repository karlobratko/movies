/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.bll.concrete.factory;

import hr.kbratko.bll.base.manager.model.GenreDomainModelManager;
import hr.kbratko.bll.concrete.manager.general.GeneralGenreDomainModelManager;
import hr.kbratko.dal.concrete.factory.GenreTableModelRepositoryFactory;

/**
 *
 * @author kbratko
 */
public final class GenreDomainModelManagerFactory {

  private GenreDomainModelManagerFactory() {
  }

  public static GenreDomainModelManager getManager() {
    return new GeneralGenreDomainModelManager(
      GenreTableModelRepositoryFactory.getRepository()
    );
  }

}
