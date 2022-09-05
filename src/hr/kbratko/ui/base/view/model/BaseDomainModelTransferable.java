/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.ui.base.view.model;

import hr.kbratko.bll.base.model.DomainModel;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Collection;

/**
 *
 * @author kbratko
 * @param <TKey>
 * @param <TModel>
 */
public abstract class BaseDomainModelTransferable<TKey, TModel extends DomainModel<TKey>>
  implements DomainModelTransferable<TKey, TModel>,
             Transferable {

  public abstract Collection<DataFlavor> getSupportedFlavors();

  private final TModel _model;

  public BaseDomainModelTransferable(TModel model) {
    this._model = model;
  }

  @Override
  public TModel getModel() {
    return this._model;
  }

  @Override
  public Object getTransferData(DataFlavor df)
    throws UnsupportedFlavorException, IOException {
    if (this.isDataFlavorSupported(df))
      return this._model;

    throw new UnsupportedFlavorException(df);
  }

  @Override
  public DataFlavor[] getTransferDataFlavors() {
    return (DataFlavor[]) this.getSupportedFlavors().toArray();
  }

  @Override
  public boolean isDataFlavorSupported(DataFlavor df) {
    return this.getSupportedFlavors().contains(df);
  }

}
