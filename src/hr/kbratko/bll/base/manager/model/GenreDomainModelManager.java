/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.kbratko.bll.base.manager.model;

import hr.kbratko.bll.base.manager.DomainModelManager;
import hr.kbratko.bll.concrete.model.GenreDomainModel;
import hr.kbratko.dal.concrete.model.GenreTableModel;
import java.util.Collection;
import java.util.Optional;

/**
 *
 * @author kbratko
 */
public interface GenreDomainModelManager
  extends DomainModelManager<Integer, GenreTableModel, GenreDomainModel> {

  int removeAll()
    throws Exception;

  int removeAll(Optional<Integer> deletedBy)
    throws Exception;

  Collection<GenreDomainModel> getByMovieFK(int movieFK)
    throws Exception;

}
