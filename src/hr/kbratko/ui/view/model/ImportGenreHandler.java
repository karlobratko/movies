/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.ui.view.model;

import hr.kbratko.bll.concrete.model.GenreDomainModel;
import hr.kbratko.ui.base.view.model.BaseImportDomainModelHandler;
import java.util.function.Consumer;
import javax.swing.JList;

/**
 *
 * @author kbratko
 */
public final class ImportGenreHandler
  extends BaseImportDomainModelHandler<Integer, GenreDomainModel> {

  public ImportGenreHandler(JList<GenreDomainModel> destination,
                            Consumer<GenreDomainModel> consumer) {
    super(GenreTransferable.DEFAULT_FLAVOR, destination, consumer);
  }

}
