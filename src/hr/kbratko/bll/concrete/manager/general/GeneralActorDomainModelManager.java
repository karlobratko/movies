/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.bll.concrete.manager.general;

import hr.kbratko.bll.base.manager.BaseDomainModelManager;
import hr.kbratko.bll.base.manager.model.ActorDomainModelManager;
import hr.kbratko.bll.concrete.model.ActorDomainModel;
import hr.kbratko.dal.base.repo.TableModelRepository;
import hr.kbratko.dal.base.repo.model.ActorTableModelRepository;
import hr.kbratko.dal.concrete.model.ActorTableModel;

/**
 *
 * @author kbratko
 */
public final class GeneralActorDomainModelManager
  extends BaseDomainModelManager<Integer, ActorTableModel, ActorDomainModel>
  implements ActorDomainModelManager {

  private final ActorTableModelRepository _repository;

  public GeneralActorDomainModelManager(ActorTableModelRepository repository) {
    this._repository = repository;
  }

  @Override
  public TableModelRepository<Integer, ActorTableModel> getRepository() {
    return _repository;
  }

  @Override
  public ActorDomainModel toDomainModel(ActorTableModel tableModel) {
    return new ActorDomainModel(tableModel.getMovieFK(),
                                tableModel.getPersonFK(),
                                tableModel.getId(),
                                tableModel.getGuid(),
                                tableModel.getDeleteDate().isEmpty());
  }

  @Override
  public ActorTableModel toTableModel(ActorDomainModel domainModel) {
    return new ActorTableModel(domainModel.getMovieFK(),
                               domainModel.getPersonFK(),
                               domainModel.getId(),
                               domainModel.getGuid(),
                               null,
                               null,
                               null,
                               null,
                               null,
                               null);
  }

}
