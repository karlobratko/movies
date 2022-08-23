/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.dal.base.repo.model;

import hr.kbratko.dal.base.repo.TableModelRepository;
import hr.kbratko.dal.concrete.model.GenreTableModel;
import java.util.Optional;

/**
 *
 * @author kbratko
 */
public interface GenreTableModelRepository
  extends TableModelRepository<Integer, GenreTableModel> {

  int deleteAll()
    throws Exception;

  int deleteAll(Optional<Integer> deletedBy)
    throws Exception;

}
