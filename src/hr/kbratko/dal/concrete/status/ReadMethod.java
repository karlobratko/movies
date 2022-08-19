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
public enum ReadMethod implements Status {

  ALL (1),
  ALL_AVAILABLE (2),
  ONE (3),
  ONE_AVAILABLE (4);

  private static final Map<Integer, Status> _mappings = new HashMap<>();

  static {
    for (ReadMethod status : ReadMethod.values())
      _mappings.put(
        status.toInteger(),
        status
      );
  }

  private final int _value;

  private ReadMethod(int value) {
    this._value = value;
  }

  public static ReadMethod fromInteger(int value) throws Exception {
    return switch (value) {
      case 1 -> ALL;
      case 2 -> ALL_AVAILABLE;
      case 3 -> ONE;
      case 4 -> ONE_AVAILABLE;
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
