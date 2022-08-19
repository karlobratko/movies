/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.kbratko.dal.base.repo;

import hr.kbratko.dal.base.model.Identifiable;
import hr.kbratko.dal.concrete.status.DeleteStatus;
import hr.kbratko.dal.concrete.status.UpdateStatus;
import java.util.UUID;

/**
 *
 * @author kbratko
 * @param <TKey>
 * @param <TModel>
 */
public interface IdentifiableRepository<TKey, TModel extends Identifiable<TKey>>
  extends ReadWriteRepository<TModel> {

  TModel readById(TKey id)
    throws Exception;

  UpdateStatus update(UUID guid, TModel model)
    throws Exception;

  DeleteStatus delete(UUID guid)
    throws Exception;

}
