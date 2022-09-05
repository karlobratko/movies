/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.ui.view.model;

import hr.kbratko.bll.concrete.model.MovieDomainModel;
import hr.kbratko.ui.base.view.model.BaseTableModel;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author kbratko
 */
public class MovieTableModel
  extends BaseTableModel<Integer, MovieDomainModel> {

  private static final DateTimeFormatter DEFAULT_PUBLISHED_DATE_FORMATTER =
                                         DateTimeFormatter.ofPattern(
                                           "yyyy/MM/dd");

  private static final String[] COLUMN_NAMES = {"#",
                                                "Title",
                                                "Original title",
                                                "Published date",
                                                "Duration minutes",
                                                "Favourite"};

  public MovieTableModel(List<MovieDomainModel> movies) {
    this.setData(movies);
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
        this.getData().get(rowIndex).getTitle();
      case 2 ->
        this.getData().get(rowIndex).getOriginalTitle();
      case 3 ->
        this.getData()
        .get(rowIndex)
        .getPublishedDate()
        .format(DEFAULT_PUBLISHED_DATE_FORMATTER);
      case 4 ->
        String.format("%d min",
                      this.getData().get(rowIndex).getDurationMinutes());
      case 5 ->
        this.getData().get(rowIndex).isFavorite() ? "Yes" : "No";
      default ->
        throw new RuntimeException("Column doex not exist");
    };
  }

}
