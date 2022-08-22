/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.dal.concrete.factory;

import hr.kbratko.dal.base.repo.model.MovieGenreTableModelRepository;
import hr.kbratko.dal.concrete.repo.db.sql.SqlMovieGenreTableModelRepository;

/**
 *
 * @author kbratko
 */
public final class MovieGenreTableModelRepositoryFactory {

  private MovieGenreTableModelRepositoryFactory() {
  }

  public static final MovieGenreTableModelRepository getRepository() {
    return new SqlMovieGenreTableModelRepository();
  }

}
