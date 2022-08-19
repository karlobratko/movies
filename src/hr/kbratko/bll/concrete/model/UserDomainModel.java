/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.bll.concrete.model;

import hr.kbratko.bll.base.model.BaseDomainModel;
import java.util.Objects;
import java.util.UUID;

/**
 *
 * @author kbratko
 */
public final class UserDomainModel
  extends BaseDomainModel<Integer> {

  private final String _username;
  private final String _password;
  private final boolean _isAdmin;

  public UserDomainModel(String username,
                         String password,
                         boolean isAdmin,
                         Integer id,
                         UUID guid,
                         boolean isAvailable) {
    super(id, guid, isAvailable);
    this._username = Objects.requireNonNull(username);
    this._password = password;
    this._isAdmin = isAdmin;
  }

  public String getUsername() {
    return _username;
  }

  public String getPassword() {
    return _password;
  }

  public boolean isAdmin() {
    return _isAdmin;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;

    if (obj == null || obj.getClass() != this.getClass())
      return false;

    var user = (UserDomainModel) obj;

    return (user._username == null
            ? this._username == null
            : user._username.equals(this._username));
  }

  @Override
  public int hashCode() {
    return this._username.hashCode();
  }

  @Override
  public String toString() {
    return String.format("%d, %s", this._id, this._username);
  }

}
