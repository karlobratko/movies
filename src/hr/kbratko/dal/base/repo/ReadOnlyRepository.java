/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.kbratko.dal.base.repo;

import java.util.Collection;

/**
 *
 * @author kbratko
 * @param <TModel>
 */
public interface ReadOnlyRepository<TModel> {

  Collection<TModel> readAll()
    throws Exception;

}
