/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.kbratko.dal.base.status;

import java.util.Map;

/**
 *
 * @author kbratko
 */
public interface Status {

  int toInteger();

  Map<Integer, Status> getStatusMappings();

}
