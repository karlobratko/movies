/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.bll.concrete.manager.general;

import hr.kbratko.bll.base.manager.BaseDomainModelManager;
import hr.kbratko.bll.base.manager.model.DirectorDomainModelManager;
import hr.kbratko.bll.concrete.model.DirectorDomainModel;
import hr.kbratko.dal.base.repo.TableModelRepository;
import hr.kbratko.dal.base.repo.model.DirectorTableModelRepository;
import hr.kbratko.dal.concrete.model.DirectorTableModel;

/**
 *
 * @author kbratko
 */
public final class GeneralDirectorDomainModelManager
  extends BaseDomainModelManager<Integer, DirectorTableModel, DirectorDomainModel>
  implements DirectorDomainModelManager {

  private final DirectorTableModelRepository _repository;

  public GeneralDirectorDomainModelManager(
    DirectorTableModelRepository repository) {
    this._repository = repository;
  }

  @Override
  public TableModelRepository<Integer, DirectorTableModel> getRepository() {
    return _repository;
  }

  @Override
  public DirectorDomainModel toDomainModel(DirectorTableModel tableModel) {
    return new DirectorDomainModel(tableModel.getMovieFK(),
                                   tableModel.getPersonFK(),
                                   tableModel.getId(),
                                   tableModel.getGuid(),
                                   tableModel.getDeleteDate().isEmpty());
  }

  @Override
  public DirectorTableModel toTableModel(DirectorDomainModel domainModel) {
    return new DirectorTableModel(domainModel.getMovieFK(),
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
