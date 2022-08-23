/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package hr.kbratko.dal.concrete.projection;

import hr.kbratko.dal.base.projection.Projection;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 *
 * @author kbratko
 */
public final record MovieProjection(String title,
                                    String originalTitle,
                                    LocalDateTime publishedDate,
                                    int durationMinutes,
                                    String description,
                                    Collection<PersonProjection> directors,
                                    Collection<PersonProjection> actors,
                                    Collection<GenreProjection> genres,
                                    String webPath,
                                    String imagePath)
  implements Projection {

}
