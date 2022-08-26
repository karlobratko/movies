/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.kbratko.bll.base.manager;

import hr.kbratko.bll.base.model.DomainModel;
import hr.kbratko.dal.base.model.TableModel;
import hr.kbratko.dal.base.repo.TableModelRepository;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

/**
 *
 * @author kbratko
 * @param <TKey>
 * @param <TTableModel>
 * @param <TDomainModel>
 */
public interface DomainModelManager<TKey, TTableModel extends TableModel<TKey>, TDomainModel extends DomainModel<TKey>> {

  TableModelRepository<TKey, TTableModel> getRepository();

  TDomainModel toDomainModel(TTableModel tableModel);

  TTableModel toTableModel(TDomainModel domainModel);

  Optional<TDomainModel> add(TDomainModel model)
    throws Exception;

  Optional<TDomainModel> add(TDomainModel model, Optional<TKey> createdBy)
    throws Exception;

  int edit(TDomainModel model)
    throws Exception;

  int edit(UUID guid, TDomainModel model)
    throws Exception;

  int edit(TDomainModel model, Optional<TKey> updatedBy)
    throws Exception;

  int edit(UUID guid, TDomainModel model, Optional<TKey> updatedBy)
    throws Exception;

  int remove(TDomainModel model)
    throws Exception;

  int remove(UUID guid)
    throws Exception;

  int remove(TDomainModel model, Optional<TKey> deletedBy)
    throws Exception;

  int remove(UUID guid, Optional<TKey> deletedBy)
    throws Exception;

  Optional<TDomainModel> getById(TKey id)
    throws Exception;

  Optional<TDomainModel> getByIdIfAvailable(TKey id)
    throws Exception;

  Collection<TDomainModel> getAll()
    throws Exception;

  Collection<TDomainModel> getAllIfAvailable()
    throws Exception;

}
