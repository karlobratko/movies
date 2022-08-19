/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.kbratko.dal.base.repo;

import hr.kbratko.dal.base.status.StatusResult;
import hr.kbratko.dal.base.model.TableModel;
import hr.kbratko.dal.concrete.status.CreateStatus;
import hr.kbratko.dal.concrete.status.DeleteStatus;
import hr.kbratko.dal.concrete.status.UpdateStatus;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

/**
 *
 * @author kbratko
 * @param <TKey>
 * @param <TModel>
 */
public interface TableModelRepository<TKey, TModel extends TableModel<TKey>>
  extends IdentifiableRepository<TKey, TModel> {

  StatusResult<CreateStatus, TModel> create(TModel model, Optional<TKey> createdBy)
    throws Exception;

  Collection<TModel> readAllAvailable()
    throws Exception;

  TModel readByIdAvailable(TKey id)
    throws Exception;

  UpdateStatus update(UUID guid, TModel model, Optional<TKey> updatedBy)
    throws Exception;

  DeleteStatus delete(UUID guid, Optional<TKey> deletedBy)
    throws Exception;

}
