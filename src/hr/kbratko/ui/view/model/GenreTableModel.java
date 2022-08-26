/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.ui.view.model;

import hr.kbratko.bll.concrete.model.GenreDomainModel;
import hr.kbratko.ui.base.view.model.BaseTableModel;
import java.util.List;

/**
 *
 * @author kbratko
 */
public class GenreTableModel
  extends BaseTableModel<Integer, GenreDomainModel> {

  private static final String[] COLUMN_NAMES = {"#",
                                                "Name"};

  public GenreTableModel(List<GenreDomainModel> genres) {
    this.setData(genres);
  }

  @Override
  public Class<?> getColumnClass(int columnIndex) {
    return switch (columnIndex) {
      case 0 ->
        Integer.class;
      default ->
        super.getColumnClass(columnIndex);
    };
  }

  @Override
  protected String[] getColumnNames() {
    return COLUMN_NAMES;
  }

  @Override
  public Integer getKeyOfRow(int row) {
    return (Integer) this.getValueAt(row, 0);
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    return switch (columnIndex) {
      case 0 ->
        this.getData().get(rowIndex).getId();
      case 1 ->
        this.getData().get(rowIndex).getName();
      default ->
        throw new RuntimeException("Column doex not exist");
    };
  }

}
