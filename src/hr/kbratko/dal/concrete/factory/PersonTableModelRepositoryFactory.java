/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.dal.concrete.factory;

import hr.kbratko.dal.base.repo.model.PersonTableModelRepository;
import hr.kbratko.dal.concrete.repo.db.sql.SqlPersonTableModelRepository;

/**
 *
 * @author kbratko
 */
public final class PersonTableModelRepositoryFactory {

  private PersonTableModelRepositoryFactory() {
  }

  public static final PersonTableModelRepository getRepository() {
    return new SqlPersonTableModelRepository();
  }

}
