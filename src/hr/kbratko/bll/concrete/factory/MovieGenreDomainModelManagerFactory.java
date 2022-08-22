/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.bll.concrete.factory;

import hr.kbratko.bll.base.manager.model.MovieGenreDomainModelManager;
import hr.kbratko.bll.concrete.manager.general.GeneralMovieGenreDomainModelManager;
import hr.kbratko.dal.concrete.factory.MovieGenreTableModelRepositoryFactory;

/**
 *
 * @author kbratko
 */
public final class MovieGenreDomainModelManagerFactory {

  private MovieGenreDomainModelManagerFactory() {
  }

  public static MovieGenreDomainModelManager getManager() {
    return new GeneralMovieGenreDomainModelManager(
      MovieGenreTableModelRepositoryFactory.getRepository()
    );
  }

}
