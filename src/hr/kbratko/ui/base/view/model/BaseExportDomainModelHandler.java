/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.ui.base.view.model;

import hr.kbratko.bll.base.model.DomainModel;
import java.awt.datatransfer.Transferable;
import java.util.function.Function;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.TransferHandler;

/**
 *
 * @author kbratko
 * @param <TKey>
 * @param <TModel>
 */
public abstract class BaseExportDomainModelHandler<TKey, TModel extends DomainModel<TKey>>
  extends TransferHandler {

  public final int _transferAction;
  public final JList<TModel> _source;
  private final Function<TModel, BaseDomainModelTransferable<TKey, TModel>> _transferableGenerator;

  public BaseExportDomainModelHandler(JList<TModel> source,
                                      int transferAction,
                                      Function<TModel, BaseDomainModelTransferable<TKey, TModel>> transferableGenerator) {
    super(null);

    this._transferAction = transferAction;
    this._source = source;
    this._transferableGenerator = transferableGenerator;
  }

  @Override
  public int getSourceActions(JComponent c) {
    return this._transferAction;
  }

  @Override
  protected Transferable createTransferable(JComponent c) {
    return this._transferableGenerator
      .apply(this._source.getSelectedValue());
  }

}
