/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.kbratko.dal.base.repo;

import hr.kbratko.dal.base.status.StatusResult;
import hr.kbratko.dal.concrete.status.CreateStatus;
import hr.kbratko.dal.concrete.status.DeleteStatus;
import hr.kbratko.dal.concrete.status.UpdateStatus;

/**
 *
 * @author kbratko
 * @param <TModel>
 */
public interface ReadWriteRepository<TModel>
  extends ReadOnlyRepository<TModel> {

  StatusResult<CreateStatus, TModel> create(TModel model)
    throws Exception;

  UpdateStatus update(TModel model)
    throws Exception;

  DeleteStatus delete(TModel model)
    throws Exception;

}
