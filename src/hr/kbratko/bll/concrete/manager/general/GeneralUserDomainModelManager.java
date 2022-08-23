/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.bll.concrete.manager.general;

import hr.kbratko.bll.base.manager.BaseDomainModelManager;
import hr.kbratko.bll.base.manager.model.UserDomainModelManager;
import hr.kbratko.bll.concrete.model.UserDomainModel;
import hr.kbratko.dal.base.repo.TableModelRepository;
import hr.kbratko.dal.base.repo.model.UserTableModelRepository;
import hr.kbratko.dal.base.status.StatusResult;
import hr.kbratko.dal.concrete.model.UserTableModel;
import hr.kbratko.dal.concrete.status.RegistrationStatus;
import java.util.Objects;
import java.util.Optional;

/**
 *
 * @author kbratko
 */
public final class GeneralUserDomainModelManager
  extends BaseDomainModelManager<Integer, UserTableModel, UserDomainModel>
  implements UserDomainModelManager {

  private final UserTableModelRepository _repository;

  public GeneralUserDomainModelManager(UserTableModelRepository repository) {
    this._repository = repository;
  }

  @Override
  public TableModelRepository<Integer, UserTableModel> getRepository() {
    return _repository;
  }

  @Override
  public UserDomainModel toDomainModel(UserTableModel tableModel) {
    return new UserDomainModel(tableModel.getUsername(),
                               null,
                               tableModel.isAdmin(),
                               tableModel.getId(),
                               tableModel.getGuid(),
                               tableModel.getDeleteDate().isEmpty());
  }

  @Override
  public UserTableModel toTableModel(UserDomainModel domainModel) {
    return new UserTableModel(domainModel.getUsername(),
                              null,
                              domainModel.isAdmin(),
                              domainModel.getId(),
                              domainModel.getGuid(),
                              null,
                              null,
                              null,
                              null,
                              null,
                              null);
  }

  @Override
  public UserDomainModel login(UserDomainModel model)
    throws Exception {
    return login(model.getUsername(), model.getPassword());
  }

  @Override
  public UserDomainModel login(String username, String password)
    throws Exception {
    UserTableModel tableModel =
                   ((UserTableModelRepository) this.getRepository())
                     .login(username,
                            password);

    return Objects.nonNull(tableModel)
             ? this.toDomainModel(tableModel)
             : null;
  }

  @Override
  public UserDomainModel register(UserDomainModel model)
    throws Exception {
    return register(model, Optional.empty());
  }

  @Override
  public UserDomainModel register(String username, String password)
    throws Exception {
    return register(username, password, Optional.empty());
  }

  @Override
  public UserDomainModel register(UserDomainModel model,
                                  Optional<Integer> createdBy)
    throws Exception {
    return register(model.getUsername(), model.getPassword(), createdBy);
  }

  @Override
  public UserDomainModel register(String username, String password,
                                  Optional<Integer> createdBy)
    throws Exception {
    StatusResult<RegistrationStatus, UserTableModel> statusResult =
                                                     ((UserTableModelRepository) this
                                                      .getRepository())
                                                       .register(username,
                                                                 password,
                                                                 createdBy);

    return switch (statusResult.getStatus()) {
      case INTERNAL_ERROR, UNIQUE_VIOLATION, RECREATED ->
        null;
      case SUCCESS ->
        this.toDomainModel(statusResult.getModel());
    };
  }

}
