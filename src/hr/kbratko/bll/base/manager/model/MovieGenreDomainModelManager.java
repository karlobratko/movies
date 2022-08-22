/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.kbratko.bll.base.manager.model;

import hr.kbratko.bll.base.manager.DomainModelManager;
import hr.kbratko.bll.concrete.model.MovieGenreDomainModel;
import hr.kbratko.dal.concrete.model.MovieGenreTableModel;

/**
 *
 * @author kbratko
 */
public interface MovieGenreDomainModelManager
  extends
  DomainModelManager<Integer, MovieGenreTableModel, MovieGenreDomainModel> {

}
