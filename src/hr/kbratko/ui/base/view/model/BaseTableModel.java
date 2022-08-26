/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.ui.base.view.model;

import hr.kbratko.bll.base.model.DomainModel;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author kbratko
 * @param <TKey>
 * @param <TModel>
 */
public abstract class BaseTableModel<TKey, TModel extends DomainModel<TKey>>
  extends AbstractTableModel
  implements TableModel<TKey, TModel> {

  protected abstract String[] getColumnNames();

  public abstract TKey getKeyOfRow(int row);

  private List<TModel> _data;

  @Override
  public List<TModel> getData() {
    return this._data;
  }

  @Override
  public void setData(List<TModel> data) {
    this._data = data;
  }

  @Override
  public int getColumnCount() {
    return this.getColumnNames().length;
  }

  @Override
  public String getColumnName(int column) {
    return this.getColumnNames()[column];
  }

  @Override
  public int getRowCount() {
    return this.getData().size();
  }

}
