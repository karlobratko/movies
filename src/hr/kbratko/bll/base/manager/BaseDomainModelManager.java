/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.bll.base.manager;

import hr.kbratko.bll.base.model.DomainModel;
import hr.kbratko.dal.base.model.TableModel;
import hr.kbratko.dal.base.status.StatusResult;
import hr.kbratko.dal.concrete.status.CreateStatus;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import javax.naming.OperationNotSupportedException;

/**
 *
 * @author kbratko
 * @param <TKey>
 * @param <TTableModel>
 * @param <TDomainModel>
 */
public abstract class BaseDomainModelManager<TKey, TTableModel extends TableModel<TKey>, TDomainModel extends DomainModel<TKey>>
  implements DomainModelManager<TKey, TTableModel, TDomainModel> {

  @Override
  public TDomainModel add(TDomainModel model)
    throws Exception {
    return add(model, Optional.empty());
  }

  @Override
  public TDomainModel add(TDomainModel model, Optional<TKey> createdBy)
    throws Exception {
    StatusResult<CreateStatus, TTableModel> statusResult =
                                            this.getRepository().create(this
                                              .toTableModel(model),
                                                                        createdBy);

    return switch (statusResult.getStatus()) {
      case INTERNAL_ERROR ->
        null;
      case SUCCESS, UNIQUE_VIOLATION, RECREATED ->
        this.toDomainModel(statusResult.getModel());
      default ->
        throw new OperationNotSupportedException();
    };
  }

  @Override
  public int edit(TDomainModel model)
    throws Exception {
    return edit(model.getGuid(), model);
  }

  @Override
  public int edit(UUID guid, TDomainModel model)
    throws Exception {
    return edit(guid, model, Optional.empty());
  }

  @Override
  public int edit(TDomainModel model, Optional<TKey> updatedBy)
    throws Exception {
    return edit(model.getGuid(), model, updatedBy);
  }

  @Override
  public int edit(UUID guid, TDomainModel model, Optional<TKey> updatedBy)
    throws Exception {
    return switch (this.getRepository().update(guid, this.toTableModel(model),
                                               updatedBy)) {
      case INTERNAL_ERROR, NOT_EXISTS, DELETED, UNIQUE_VIOLATION ->
        0;
      case SUCCESS ->
        1;
      default ->
        throw new OperationNotSupportedException();
    };
  }

  @Override
  public Collection<TDomainModel> getAll()
    throws Exception {
    return this.getRepository().readAll().stream().map(this::toDomainModel)
      .toList();
  }

  @Override
  public Collection<TDomainModel> getAllIfAvailable()
    throws Exception {
    return this.getRepository().readAllAvailable().stream().map(
      this::toDomainModel).toList();
  }

  @Override
  public TDomainModel getById(TKey id)
    throws Exception {
    TTableModel tableModel = this.getRepository().readById(id);

    return Objects.nonNull(tableModel)
             ? this.toDomainModel(tableModel)
             : null;
  }

  @Override
  public TDomainModel getByIdIfAvailable(TKey id)
    throws Exception {
    TTableModel tableModel = this.getRepository().readByIdAvailable(id);

    return Objects.nonNull(tableModel)
             ? this.toDomainModel(tableModel)
             : null;
  }

  @Override
  public int remove(TDomainModel model)
    throws Exception {
    return remove(model.getGuid());
  }

  @Override
  public int remove(UUID guid)
    throws Exception {
    return remove(guid, Optional.empty());
  }

  @Override
  public int remove(TDomainModel model, Optional<TKey> deletedBy)
    throws Exception {
    return remove(model.getGuid(), deletedBy);
  }

  @Override
  public int remove(UUID guid, Optional<TKey> deletedBy)
    throws Exception {
    return switch (this.getRepository().delete(guid, deletedBy)) {
      case INTERNAL_ERROR, NOT_EXISTS, ALREADY_DELETED ->
        0;
      case SUCCESS ->
        1;
      default ->
        throw new OperationNotSupportedException();
    };
  }

}
