/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.ui.view.model;

import hr.kbratko.bll.concrete.model.GenreDomainModel;
import hr.kbratko.ui.base.view.model.BaseDomainModelTransferable;
import java.awt.datatransfer.DataFlavor;
import java.util.Collection;
import java.util.stream.Stream;

/**
 *
 * @author kbratko
 */
public final class GenreTransferable
  extends BaseDomainModelTransferable<Integer, GenreDomainModel> {

  public static final DataFlavor DEFAULT_FLAVOR =
                                 new DataFlavor(GenreDomainModel.class,
                                                "Genre");
  private static final Collection<DataFlavor> SUPPORTED_FLAVORS =
                                              Stream.of(DEFAULT_FLAVOR).toList();

  public GenreTransferable(GenreDomainModel genre) {
    super(genre);
  }

  @Override
  public DataFlavor getDefaultFlavor() {
    return DEFAULT_FLAVOR;
  }

  @Override
  public Collection<DataFlavor> getSupportedFlavors() {
    return SUPPORTED_FLAVORS;
  }

}
