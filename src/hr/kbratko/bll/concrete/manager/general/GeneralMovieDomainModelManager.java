/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.bll.concrete.manager.general;

import hr.kbratko.bll.base.manager.BaseDomainModelManager;
import hr.kbratko.bll.base.manager.model.MovieDomainModelManager;
import hr.kbratko.bll.concrete.model.MovieDomainModel;
import hr.kbratko.dal.base.repo.TableModelRepository;
import hr.kbratko.dal.base.repo.model.MovieTableModelRepository;
import hr.kbratko.dal.concrete.model.MovieTableModel;
import java.util.Optional;

/**
 *
 * @author kbratko
 */
public class GeneralMovieDomainModelManager
  extends BaseDomainModelManager<Integer, MovieTableModel, MovieDomainModel>
  implements MovieDomainModelManager {

  private final MovieTableModelRepository _repository;

  public GeneralMovieDomainModelManager(MovieTableModelRepository repository) {
    this._repository = repository;
  }

  @Override
  public TableModelRepository<Integer, MovieTableModel> getRepository() {
    return _repository;
  }

  @Override
  public MovieDomainModel toDomainModel(MovieTableModel tableModel) {
    return new MovieDomainModel(tableModel.getTitle(),
                                tableModel.getOriginalTitle(),
                                tableModel.getPublishedDate(),
                                tableModel.getDurationMinutes(),
                                tableModel.getDescription().orElse(null),
                                tableModel.getWebPath().orElse(null),
                                tableModel.getImagePath().orElse(null),
                                tableModel.isFavorite(),
                                tableModel.getId(),
                                tableModel.getGuid(),
                                tableModel.getDeleteDate().isEmpty());
  }

  @Override
  public MovieTableModel toTableModel(MovieDomainModel domainModel) {
    return new MovieTableModel(domainModel.getTitle(),
                               domainModel.getOriginalTitle(),
                               domainModel.getPublishedDate(),
                               domainModel.getDurationMinutes(),
                               domainModel.getDescription().orElse(null),
                               domainModel.getWebPath().orElse(null),
                               domainModel.getImagePath().orElse(null),
                               domainModel.isFavorite(),
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
    return ((MovieTableModelRepository) this.getRepository()).deleteAll(
      deletedBy);
  }

}
