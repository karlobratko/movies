/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.ui.view.model;

import hr.kbratko.bll.concrete.model.PersonDomainModel;
import hr.kbratko.ui.base.view.model.BaseDomainModelTransferable;
import hr.kbratko.ui.base.view.model.BaseExportDomainModelHandler;
import java.util.function.Function;
import javax.swing.JList;

/**
 *
 * @author kbratko
 */
public final class ExportPersonHandler
  extends BaseExportDomainModelHandler<Integer, PersonDomainModel> {

  public ExportPersonHandler(JList<PersonDomainModel> source,
                             int transferAction,
                             Function<PersonDomainModel, BaseDomainModelTransferable<Integer, PersonDomainModel>> transferableGenerator) {
    super(source, transferAction, transferableGenerator);
  }

}
