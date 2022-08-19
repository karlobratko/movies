/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.kbratko.bll.base.manager.model;

import hr.kbratko.bll.base.manager.DomainModelManager;
import hr.kbratko.bll.concrete.model.UserDomainModel;
import hr.kbratko.dal.concrete.model.UserTableModel;
import java.util.Optional;

/**
 *
 * @author kbratko
 */
public interface UserDomainModelManager
  extends
  DomainModelManager<Integer, UserTableModel, UserDomainModel> {

  UserDomainModel login(UserDomainModel model)
    throws Exception;

  UserDomainModel login(String username, String password)
    throws Exception;

  UserDomainModel register(UserDomainModel model)
    throws Exception;

  UserDomainModel register(UserDomainModel model, Optional<Integer> createdBy)
    throws Exception;

  UserDomainModel register(String username, String password)
    throws Exception;

  UserDomainModel register(String username, String password, Optional<Integer> createdBy)
    throws Exception;

}
