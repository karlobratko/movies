/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.dal.concrete.factory;

import hr.kbratko.dal.base.repo.model.MovieTableModelRepository;
import hr.kbratko.dal.concrete.repo.db.sql.SqlMovieTableModelRepository;

/**
 *
 * @author kbratko
 */
public final class MovieTableModelRepositoryFactory {

  private MovieTableModelRepositoryFactory() {
  }

  public static final MovieTableModelRepository getRepository() {
    return new SqlMovieTableModelRepository();
  }

}
