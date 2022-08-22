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

  StatusResult<CreateStatus, TModel> create(final TModel model,
                                            final Optional<TKey> createdBy)
    throws Exception;

  Collection<TModel> readAllAvailable()
    throws Exception;

  TModel readByIdAvailable(final TKey id)
    throws Exception;

  UpdateStatus update(final UUID guid, final TModel model,
                      final Optional<TKey> updatedBy)
    throws Exception;

  DeleteStatus delete(final UUID guid, final Optional<TKey> deletedBy)
    throws Exception;

}
