/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.dal.concrete.factory;

import hr.kbratko.dal.base.repo.model.ActorTableModelRepository;
import hr.kbratko.dal.concrete.repo.db.sql.SqlActorTableModelRepository;

/**
 *
 * @author kbratko
 */
public final class ActorTableModelRepositoryFactory {

  private ActorTableModelRepositoryFactory() {
  }

  public static final ActorTableModelRepository getRepository() {
    return new SqlActorTableModelRepository();
  }

}