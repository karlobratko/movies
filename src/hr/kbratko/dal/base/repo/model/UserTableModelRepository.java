/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.kbratko.dal.base.repo.model;

import hr.kbratko.dal.base.status.StatusResult;
import hr.kbratko.dal.base.repo.TableModelRepository;
import hr.kbratko.dal.concrete.model.UserTableModel;
import hr.kbratko.dal.concrete.status.RegistrationStatus;
import java.util.Optional;

/**
 *
 * @author kbratko
 */
public interface UserTableModelRepository
  extends TableModelRepository<Integer, UserTableModel> {

  UserTableModel login(UserTableModel model, String password)
    throws Exception;

  UserTableModel login(String username, String password)
    throws Exception;

  StatusResult<RegistrationStatus, UserTableModel> register(UserTableModel model,
                                                            String password)
    throws Exception;

  StatusResult<RegistrationStatus, UserTableModel> register(String username,
                                                            String password)
    throws Exception;

  StatusResult<RegistrationStatus, UserTableModel> register(UserTableModel model,
                                                            String password,
                                                            Optional<Integer> createdBy)
    throws Exception;

  StatusResult<RegistrationStatus, UserTableModel> register(String username,
                                                            String password,
                                                            Optional<Integer> createdBy)
    throws Exception;

}
