/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.bll.concrete.manager.general;

import hr.kbratko.bll.base.manager.BaseDomainModelManager;
import hr.kbratko.bll.base.manager.model.PersonDomainModelManager;
import hr.kbratko.bll.concrete.model.PersonDomainModel;
import hr.kbratko.dal.base.repo.TableModelRepository;
import hr.kbratko.dal.base.repo.model.PersonTableModelRepository;
import hr.kbratko.dal.concrete.model.PersonTableModel;

/**
 *
 * @author kbratko
 */
public final class GeneralPersonDomainModelManager
  extends BaseDomainModelManager<Integer, PersonTableModel, PersonDomainModel>
  implements PersonDomainModelManager {

  private final PersonTableModelRepository _repository;

  public GeneralPersonDomainModelManager(PersonTableModelRepository repository) {
    this._repository = repository;
  }

  @Override
  public TableModelRepository<Integer, PersonTableModel> getRepository() {
    return _repository;
  }

  @Override
  public PersonDomainModel toDomainModel(PersonTableModel tableModel) {
    return new PersonDomainModel(tableModel.getFName(),
                                 tableModel.getLName(),
                                 tableModel.getId(),
                                 tableModel.getGuid(),
                                 tableModel.getDeleteDate().isEmpty());
  }

  @Override
  public PersonTableModel toTableModel(PersonDomainModel domainModel) {
    return new PersonTableModel(domainModel.getFName(),
                                domainModel.getLName(),
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
