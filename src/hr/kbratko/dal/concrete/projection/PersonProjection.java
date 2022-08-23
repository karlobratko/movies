/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.dal.concrete.projection;

import hr.kbratko.dal.base.projection.Projection;

/**
 *
 * @author kbratko
 */
public final record PersonProjection(String fName, String lName)
  implements Projection {

}
