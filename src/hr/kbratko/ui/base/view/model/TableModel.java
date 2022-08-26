/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.kbratko.ui.base.view.model;

import hr.kbratko.bll.base.model.DomainModel;
import java.util.List;

/**
 *
 * @author kbratko
 * @param <TKey>
 * @param <TModel>
 */
public interface TableModel<TKey, TModel extends DomainModel<TKey>> {

  List<TModel> getData();

  void setData(List<TModel> data);

}
