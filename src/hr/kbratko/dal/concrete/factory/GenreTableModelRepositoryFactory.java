/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.dal.concrete.factory;

import hr.kbratko.dal.base.repo.model.GenreTableModelRepository;
import hr.kbratko.dal.concrete.repo.db.sql.SqlGenreTableModelRepository;

/**
 *
 * @author kbratko
 */
public final class GenreTableModelRepositoryFactory {

  private GenreTableModelRepositoryFactory() {
  }

  public static final GenreTableModelRepository getRepository() {
    return new SqlGenreTableModelRepository();
  }

}
