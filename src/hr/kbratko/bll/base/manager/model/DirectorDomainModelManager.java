/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.kbratko.bll.base.manager.model;

import hr.kbratko.bll.base.manager.DomainModelManager;
import hr.kbratko.bll.concrete.model.DirectorDomainModel;
import hr.kbratko.dal.concrete.model.DirectorTableModel;

/**
 *
 * @author kbratko
 */
public interface DirectorDomainModelManager
  extends DomainModelManager<Integer, DirectorTableModel, DirectorDomainModel> {

}
