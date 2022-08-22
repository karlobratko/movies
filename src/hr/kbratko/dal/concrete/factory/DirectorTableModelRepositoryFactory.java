/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.dal.concrete.factory;

import hr.kbratko.dal.base.repo.model.DirectorTableModelRepository;
import hr.kbratko.dal.concrete.repo.db.sql.SqlDirectorTableModelRepository;

/**
 *
 * @author kbratko
 */
public final class DirectorTableModelRepositoryFactory {

  private DirectorTableModelRepositoryFactory() {
  }

  public static final DirectorTableModelRepository getRepository() {
    return new SqlDirectorTableModelRepository();
  }

}
