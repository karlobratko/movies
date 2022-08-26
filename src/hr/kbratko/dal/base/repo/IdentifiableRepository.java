/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.kbratko.dal.base.repo;

import hr.kbratko.dal.base.model.Identifiable;
import hr.kbratko.dal.concrete.status.DeleteStatus;
import hr.kbratko.dal.concrete.status.UpdateStatus;
import java.util.Optional;
import java.util.UUID;

/**
 *
 * @author kbratko
 * @param <TKey>
 * @param <TModel>
 */
public interface IdentifiableRepository<TKey, TModel extends Identifiable<TKey>>
  extends ReadWriteRepository<TModel> {

  Optional<TModel> readById(final TKey id)
    throws Exception;

  UpdateStatus update(final UUID guid, final TModel model)
    throws Exception;

  DeleteStatus delete(final UUID guid)
    throws Exception;

}
