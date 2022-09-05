/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.bll.concrete.manager.general;

import hr.kbratko.bll.base.manager.BaseDomainModelManager;
import hr.kbratko.bll.base.manager.model.GenreDomainModelManager;
import hr.kbratko.bll.concrete.model.GenreDomainModel;
import hr.kbratko.dal.base.repo.TableModelRepository;
import hr.kbratko.dal.base.repo.model.GenreTableModelRepository;
import hr.kbratko.dal.concrete.model.GenreTableModel;
import java.util.Collection;
import java.util.Optional;

/**
 *
 * @author kbratko
 */
public final class GeneralGenreDomainModelManager
  extends BaseDomainModelManager<Integer, GenreTableModel, GenreDomainModel>
  implements GenreDomainModelManager {

  private final GenreTableModelRepository _repository;

  public GeneralGenreDomainModelManager(GenreTableModelRepository repository) {
    this._repository = repository;
  }

  @Override
  public TableModelRepository<Integer, GenreTableModel> getRepository() {
    return _repository;
  }

  @Override
  public GenreDomainModel toDomainModel(GenreTableModel tableModel) {
    return new GenreDomainModel(tableModel.getName(),
                                tableModel.getId(),
                                tableModel.getGuid(),
                                tableModel.getDeleteDate().isEmpty());
  }

  @Override
  public GenreTableModel toTableModel(GenreDomainModel domainModel) {
    return new GenreTableModel(domainModel.getName(),
                               domainModel.getId(),
                               domainModel.getGuid(),
                               null,
                               null,
                               null,
                               null,
                               null,
                               null);
  }

  @Override
  public int removeAll()
    throws Exception {
    return removeAll(Optional.empty());
  }

  @Override
  public int removeAll(Optional<Integer> deletedBy)
    throws Exception {
    return ((GenreTableModelRepository) this.getRepository()).deleteAll(
      deletedBy);
  }

  @Override
  public Collection<GenreDomainModel> getByMovieFK(int movieFK)
    throws Exception {
    return ((GenreTableModelRepository) this.getRepository())
      .readByMovieFK(movieFK)
      .stream()
      .map(this::toDomainModel)
      .toList();
  }

}
