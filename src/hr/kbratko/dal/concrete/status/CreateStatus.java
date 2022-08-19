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
public enum CreateStatus
  implements Status {

  INTERNAL_ERROR(-1),
  SUCCESS(1),
  UNIQUE_VIOLATION(2),
  RECREATED(3);

  private static final Map<Integer, Status> _mappings = new HashMap<>();

  static {
    for (CreateStatus status : CreateStatus.values())
      _mappings.put(
        status.toInteger(),
        status
      );
  }

  private final int _value;

  private CreateStatus(int value) {
    this._value = value;
  }

  public static CreateStatus fromInteger(int value) throws Exception {
    return switch (value) {
      case -1 -> INTERNAL_ERROR;
      case 1 -> SUCCESS;
      case 2 -> UNIQUE_VIOLATION;
      case 3 -> RECREATED;
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
