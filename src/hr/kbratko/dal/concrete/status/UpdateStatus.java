/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package hr.kbratko.dal.concrete.status;

import hr.kbratko.dal.base.status.Status;
import java.util.HashMap;
import java.util.Map;
import javax.naming.OperationNotSupportedException;

/**
 *
 * @author kbratko
 */
public enum UpdateStatus
  implements Status {

  INTERNAL_ERROR(-1),
  SUCCESS(1),
  NOT_EXISTS(2),
  DELETED(3),
  UNIQUE_VIOLATION(4);

  private static final Map<Integer, Status> _mappings = new HashMap<>();

  static {
    for (UpdateStatus status : UpdateStatus.values())
      _mappings.put(
        status.toInteger(),
        status
      );
  }

  private final int _value;

  private UpdateStatus(int value) {
    this._value = value;
  }

  public static UpdateStatus fromInteger(int value) throws Exception {
    return switch (value) {
      case -1 -> INTERNAL_ERROR;
      case 1 -> SUCCESS;
      case 2 -> NOT_EXISTS;
      case 3 -> DELETED;
      case 4 -> UNIQUE_VIOLATION;
      default -> throw new OperationNotSupportedException();
    };
  }

  @Override
  public Map<Integer, Status> getStatusMappings() {
    return _mappings;
  }

  @Override
  public int toInteger() {
    return _value;
  }

}
