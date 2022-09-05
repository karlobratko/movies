/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.ui.view.model;

import hr.kbratko.bll.concrete.model.PersonDomainModel;
import hr.kbratko.ui.base.view.model.BaseImportDomainModelHandler;
import java.util.function.Consumer;
import javax.swing.JList;

/**
 *
 * @author kbratko
 */
public final class ImportPersonHandler
  extends BaseImportDomainModelHandler<Integer, PersonDomainModel> {

  public ImportPersonHandler(JList<PersonDomainModel> destination,
                             Consumer<PersonDomainModel> consumer) {
    super(PersonTransferable.DEFAULT_FLAVOR, destination, consumer);
  }

  @Override
  public boolean canImport(TransferSupport support) {
    return super.canImport(support); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
  }

  @Override
  public boolean importData(TransferSupport support) {
    return super.importData(support); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
  }

}
