/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.dal.base.status;

import hr.kbratko.dal.base.status.Status;

/**
 *
 * @author kbratko
 * @param <TStatus>
 * @param <TModel>
 */
public class StatusResult<TStatus extends Status, TModel> {

  private final TStatus _status;
  private final TModel _model;

  public StatusResult(TStatus status, TModel model) {
    this._status = status;
    this._model = model;
  }

  public TStatus getStatus() {
    return _status;
  }

  public TModel getModel() {
    return _model;
  }

}
