/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.ui.base.view.model;

import javax.swing.TransferHandler;
import hr.kbratko.bll.base.model.DomainModel;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author kbratko
 * @param <TKey>
 * @param <TModel>
 */
public abstract class BaseImportDomainModelHandler<TKey, TModel extends DomainModel<TKey>>
  extends TransferHandler {

  public final DataFlavor _dataFlavor;
  public final JList<TModel> _destination;
  private final Consumer<TModel> _consumer;

  public BaseImportDomainModelHandler(DataFlavor dataFlavor,
                                      JList<TModel> destination,
                                      Consumer<TModel> consumer) {
    this._dataFlavor = dataFlavor;
    this._destination = destination;
    this._consumer = consumer;
  }

  @Override
  public boolean canImport(TransferSupport support) {
    System.out.println("canImport");
    return support.isDataFlavorSupported(this._dataFlavor);
  }

  @Override
  public boolean importData(TransferSupport support) {
    System.out.println("importData");
    var transferable =
    (BaseDomainModelTransferable<TKey, TModel>) support.getTransferable();

    try {
      var model =
      (TModel) transferable.getTransferData(this._dataFlavor);

      if (!((DefaultListModel<TModel>) _destination.getModel()).contains(model)) {

        this._consumer.accept(model);

        return true;
      }
    }
    catch (UnsupportedFlavorException |
           IOException ex) {
      Logger
        .getLogger(BaseImportDomainModelHandler.class.getName())
        .log(Level.SEVERE, null, ex);
    }
    return false;
  }

}
