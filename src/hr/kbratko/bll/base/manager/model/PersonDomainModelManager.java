/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.kbratko.bll.base.manager.model;

import hr.kbratko.bll.base.manager.DomainModelManager;
import hr.kbratko.bll.concrete.model.PersonDomainModel;
import hr.kbratko.dal.concrete.model.PersonTableModel;
import java.util.Optional;

/**
 *
 * @author kbratko
 */
public interface PersonDomainModelManager
  extends DomainModelManager<Integer, PersonTableModel, PersonDomainModel> {

  int removeAll()
    throws Exception;

  int removeAll(Optional<Integer> deletedBy)
    throws Exception;

}
