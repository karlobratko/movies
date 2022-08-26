/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.bll.concrete.manager;

import hr.kbratko.bll.base.manager.model.ActorDomainModelManager;
import hr.kbratko.bll.base.manager.model.DirectorDomainModelManager;
import hr.kbratko.bll.base.manager.model.GenreDomainModelManager;
import hr.kbratko.bll.base.manager.model.MovieDomainModelManager;
import hr.kbratko.bll.base.manager.model.MovieGenreDomainModelManager;
import hr.kbratko.bll.base.manager.model.PersonDomainModelManager;
import hr.kbratko.bll.base.manager.model.UserDomainModelManager;
import hr.kbratko.bll.concrete.factory.ActorDomainModelManagerFactory;
import hr.kbratko.bll.concrete.factory.DirectorDomainModelManagerFactory;
import hr.kbratko.bll.concrete.factory.GenreDomainModelManagerFactory;
import hr.kbratko.bll.concrete.factory.MovieDomainModelManagerFactory;
import hr.kbratko.bll.concrete.factory.MovieGenreDomainModelManagerFactory;
import hr.kbratko.bll.concrete.factory.PersonDomainModelManagerFactory;
import hr.kbratko.bll.concrete.factory.UserDomainModelManagerFactory;
import hr.kbratko.bll.concrete.model.ActorDomainModel;
import hr.kbratko.bll.concrete.model.DirectorDomainModel;
import hr.kbratko.bll.concrete.model.GenreDomainModel;
import hr.kbratko.bll.concrete.model.MovieDomainModel;
import hr.kbratko.bll.concrete.model.MovieGenreDomainModel;
import hr.kbratko.bll.concrete.model.PersonDomainModel;
import hr.kbratko.dal.concrete.projection.GenreProjection;
import hr.kbratko.dal.concrete.projection.MovieProjection;
import hr.kbratko.dal.concrete.projection.PersonProjection;
import hr.kbratko.dal.concrete.repo.parser.rss.RssMovieProjectionRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

/**
 *
 * @author kbratko
 */
public final class DomainManager {

  private final ActorDomainModelManager _actorManager = ActorDomainModelManagerFactory
                                        .getManager();
  private final DirectorDomainModelManager _directorManager = DirectorDomainModelManagerFactory
                                           .getManager();
  private final GenreDomainModelManager _genreManager = GenreDomainModelManagerFactory
                                        .getManager();
  private final MovieDomainModelManager _movieManager = MovieDomainModelManagerFactory
                                        .getManager();
  private final MovieGenreDomainModelManager _movieGenreManager = MovieGenreDomainModelManagerFactory
                                             .getManager();
  private final PersonDomainModelManager _personManager = PersonDomainModelManagerFactory
                                         .getManager();
  private final UserDomainModelManager _userManager = UserDomainModelManagerFactory
                                       .getManager();

  public ActorDomainModelManager getActorManager() {
    return _actorManager;
  }

  public DirectorDomainModelManager getDirectorManager() {
    return _directorManager;
  }

  public GenreDomainModelManager getGenreManager() {
    return _genreManager;
  }

  public MovieGenreDomainModelManager getMovieGenreManager() {
    return _movieGenreManager;
  }

  public MovieDomainModelManager getMovieManager() {
    return _movieManager;
  }

  public PersonDomainModelManager getPersonManager() {
    return _personManager;
  }

  public UserDomainModelManager getUserManager() {
    return _userManager;
  }

  public void loadRss()
    throws Exception {
    var rssRepository = new RssMovieProjectionRepository();
    Collection<MovieProjection> movies = rssRepository.readAll();

    for (MovieProjection movie : movies) {

      Collection<Optional<PersonDomainModel>> directors = new ArrayList<>();
      for (PersonProjection director : movie.directors())
        directors
          .add(this.getPersonManager()
            .add(new PersonDomainModel(director.fName(),
                                       director.lName())));

      Collection<Optional<PersonDomainModel>> actors = new ArrayList<>();
      for (PersonProjection actor : movie.actors())
        actors
          .add(this.getPersonManager()
            .add(new PersonDomainModel(actor.fName(),
                                       actor.lName())));

      Collection<Optional<GenreDomainModel>> genres = new ArrayList<>();
      for (GenreProjection genre : movie.genres())
        genres
          .add(this.getGenreManager().add(new GenreDomainModel(genre.name())));

      Optional<MovieDomainModel> addedMovie =
                                 this.getMovieManager()
                                   .add(new MovieDomainModel(movie.title(),
                                                             movie
                                                               .originalTitle(),
                                                             movie
                                                               .publishedDate(),
                                                             movie
                                                               .durationMinutes(),
                                                             movie.description(),
                                                             movie.webPath(),
                                                             movie.imagePath(),
                                                             false));

      for (Optional<PersonDomainModel> director : directors)
        if (director.isPresent())
          this.getDirectorManager()
            .add(new DirectorDomainModel(addedMovie.get().getId(),
                                         director.get().getId()));

      for (Optional<PersonDomainModel> actor : actors)
        if (actor.isPresent())
          this.getActorManager()
            .add(new ActorDomainModel(addedMovie.get().getId(),
                                      actor.get().getId()));

      for (Optional<GenreDomainModel> genre : genres)
        if (genre.isPresent())
          this.getMovieGenreManager()
            .add(new MovieGenreDomainModel(addedMovie.get().getId(),
                                           genre.get().getId()));

    }
  }

  public void deleteAll()
    throws Exception {
    this.getActorManager().removeAll();
    this.getDirectorManager().removeAll();
    this.getMovieGenreManager().removeAll();
    this.getPersonManager().removeAll();
    this.getGenreManager().removeAll();
    this.getMovieManager().removeAll();
  }

}
