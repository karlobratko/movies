/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.dal.concrete.model;

import hr.kbratko.dal.base.model.BaseTableModel;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 *
 * @author kbratko
 */
public final class UserTableModel
  extends BaseTableModel<Integer> {

  private final String _username;
  private final String _passwordHash;
  private final boolean _isAdmin;

  public UserTableModel(String username,
                        String passwordHash,
                        boolean isAdmin,
                        Integer id,
                        UUID guid,
                        Integer createdBy,
                        Integer updatedBy,
                        Optional<Integer> deletedBy,
                        Date createDate,
                        Date updateDate,
                        Optional<Date> deleteDate) {
    super(id, guid, createdBy, updatedBy, deletedBy, createDate, updateDate, deleteDate);
    this._username = Objects.requireNonNull(username);
    this._passwordHash = passwordHash;
    this._isAdmin = isAdmin;
  }

  public String getUsername() {
    return this._username;
  }

  public String getPasswordHash() {
    return this._passwordHash;
  }

  public boolean isAdmin() {
    return this._isAdmin;
  }

  @Override
  public String toString() {
    return String.format("%d, %s", this._id, this._username);
  }

}
