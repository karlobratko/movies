/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.bll.concrete.manager.general;

import hr.kbratko.bll.base.manager.BaseDomainModelManager;
import hr.kbratko.bll.base.manager.model.MovieGenreDomainModelManager;
import hr.kbratko.bll.concrete.model.MovieGenreDomainModel;
import hr.kbratko.dal.base.repo.TableModelRepository;
import hr.kbratko.dal.base.repo.model.MovieGenreTableModelRepository;
import hr.kbratko.dal.concrete.model.MovieGenreTableModel;
import java.util.Optional;

/**
 *
 * @author kbratko
 */
public final class GeneralMovieGenreDomainModelManager
  extends BaseDomainModelManager<Integer, MovieGenreTableModel, MovieGenreDomainModel>
  implements MovieGenreDomainModelManager {

  private final MovieGenreTableModelRepository _repository;

  public GeneralMovieGenreDomainModelManager(
    MovieGenreTableModelRepository repository) {
    this._repository = repository;
  }

  @Override
  public TableModelRepository<Integer, MovieGenreTableModel> getRepository() {
    return _repository;
  }

  @Override
  public MovieGenreDomainModel toDomainModel(MovieGenreTableModel tableModel) {
    return new MovieGenreDomainModel(tableModel.getMovieFK(),
                                     tableModel.getGenreFK(),
                                     tableModel.getId(),
                                     tableModel.getGuid(),
                                     tableModel.getDeleteDate().isEmpty());
  }

  @Override
  public MovieGenreTableModel toTableModel(MovieGenreDomainModel domainModel) {
    return new MovieGenreTableModel(domainModel.getMovieFK(),
                                    domainModel.getGenreFK(),
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
    return ((MovieGenreTableModelRepository) this.getRepository()).deleteAll(
      deletedBy);
  }

}
