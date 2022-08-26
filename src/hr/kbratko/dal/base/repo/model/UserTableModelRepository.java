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

  Optional<UserTableModel> login(final UserTableModel model, final String password)
    throws Exception;

  Optional<UserTableModel> login(final String username, final String password)
    throws Exception;

  StatusResult<RegistrationStatus, UserTableModel> register(
    final UserTableModel model,
    final String password)
    throws Exception;

  StatusResult<RegistrationStatus, UserTableModel> register(
    final String username,
    final String password)
    throws Exception;

  StatusResult<RegistrationStatus, UserTableModel> register(
    final UserTableModel model,
    final String password,
    final Optional<Integer> createdBy)
    throws Exception;

  StatusResult<RegistrationStatus, UserTableModel> register(
    final String username,
    final String password,
    final Optional<Integer> createdBy)
    throws Exception;

}
