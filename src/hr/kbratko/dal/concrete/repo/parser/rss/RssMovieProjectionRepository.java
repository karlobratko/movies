/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.dal.concrete.repo.parser.rss;

import hr.kbratko.dal.base.repo.ReadOnlyRepository;
import hr.kbratko.dal.concrete.projection.GenreProjection;
import hr.kbratko.dal.concrete.projection.MovieProjection;
import hr.kbratko.dal.concrete.projection.PersonProjection;
import hr.kbratko.lib.factory.HttpURLConnectionFactory;
import hr.kbratko.dal.concrete.repo.parser.ParserFactory;
import hr.kbratko.lib.utility.FileUtility;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.events.Characters;

/**
 *
 * @author kbratko
 */
public final class RssMovieProjectionRepository
  implements ReadOnlyRepository {

  private static final String RSS_URL = "https://www.blitz-cinestar.hr/rss.aspx?najava";

  private static final String SEPARATOR = ",";

  private static final String IMAGE_DIR = "assets";
  private static final String DEFAULT_IMAGE_EXT = ".jpg";

  private static final DateTimeFormatter PUB_DATE_FORMATTER = DateTimeFormatter.RFC_1123_DATE_TIME;

  @Override
  public Collection readAll()
    throws Exception {
    Collection<MovieProjection> movies = new ArrayList<>();

    var connection = HttpURLConnectionFactory.getHttpURLConnection(RSS_URL);

    try ( InputStream istream = connection.getInputStream()) {
      var reader = ParserFactory.getSAXParser(istream);

      Optional<TagType> tagType = Optional.empty();

      String title = null;
      String originalTitle = null;
      LocalDateTime publishedDate = null;
      int durationMinutes = 0;
      String description = null;
      Collection<PersonProjection> directors = null;
      Collection<PersonProjection> actors = null;
      Collection<GenreProjection> genres = null;
      String webPath = null;
      String imagePath = null;

      while (reader.hasNext()) {
        var event = reader.nextEvent();

        switch (event.getEventType()) {
          case XMLStreamConstants.START_ELEMENT -> {
            tagType =
            TagType.fromString(event.asStartElement().getName().getLocalPart());

            if (tagType.isPresent() && tagType.get() == TagType.MOVIE) {
              title = null;
              originalTitle = null;
              publishedDate = null;
              durationMinutes = 0;
              description = null;
              directors = new ArrayList<>();
              actors = new ArrayList<>();
              genres = new ArrayList<>();
              webPath = null;
              imagePath = null;
            }

            break;
          }
          case XMLStreamConstants.END_ELEMENT -> {
            tagType =
            TagType.fromString(event.asEndElement().getName().getLocalPart());

            if (tagType.isPresent() && tagType.get() == TagType.MOVIE)
              movies.add(new MovieProjection(title,
                                             originalTitle,
                                             publishedDate,
                                             durationMinutes,
                                             description,
                                             directors,
                                             actors,
                                             genres,
                                             webPath,
                                             imagePath));
            break;
          }
          case XMLStreamConstants.CHARACTERS -> {
            if (tagType.isEmpty())
              break;
            Characters characters = event.asCharacters();
            String data = characters.getData().trim();
            if (data.isEmpty())
              break;

            switch (tagType.get()) {
              case TITLE -> {
                title = data.trim();
                break;
              }
              case ORIGINAL_TITLE -> {
                originalTitle = data.trim();
                break;
              }
              case PUB_DATE -> {
                publishedDate = LocalDateTime.parse(data.trim(),
                                                    PUB_DATE_FORMATTER);
                break;
              }
              case DURATION_MINUTES -> {
                durationMinutes = Integer.parseInt(data.trim());
                break;
              }
              case DESCRIPTION -> {
                if (data.contains("<div")) {
                  int startIdx = data.indexOf(">", data.lastIndexOf("<div")) + 1;
                  description =
                  data.substring(startIdx, data.indexOf("</div>", startIdx));
                }
                else if (data.contains("<img")) {
                  int startIdx = data.indexOf(">", data.indexOf("<img")) + 1;
                  description =
                  data.substring(startIdx, data.indexOf("<br />", startIdx));
                }
                else
                  description = data.trim();
                break;
              }
              case DIRECTORS -> {
                String[] fullNames = data.split(SEPARATOR);
                if (Objects.nonNull(directors) && fullNames.length > 0)
                  directors.addAll(
                    Stream
                      .of(fullNames)
                      .map(RssMovieProjectionRepository::parsePerson)
                      .filter(Optional::isPresent)
                      .map(Optional::get)
                      .toList());
                break;
              }
              case ACTORS -> {
                String[] fullNames = data.split(SEPARATOR);
                if (Objects.nonNull(actors) && fullNames.length > 0)
                  actors.addAll(
                    Stream
                      .of(fullNames)
                      .map(RssMovieProjectionRepository::parsePerson)
                      .filter(Optional::isPresent)
                      .map(Optional::get)
                      .toList());
                break;
              }
              case GENRES -> {
                String[] genreNames = data.split(SEPARATOR);
                if (Objects.nonNull(genres) && genreNames.length > 0)
                  genres.addAll(
                    Stream
                      .of(genreNames)
                      .map(x -> new GenreProjection(x.trim()))
                      .toList());
                break;
              }
              case WEB_PATH -> {
                webPath = data.trim();
              }
              case IMAGE_PATH -> {
                Optional<String> localPath = getImagePath(data.trim());
                if (localPath.isPresent())
                  imagePath = localPath.get();
              }
            }

            break;
          }

        }
      }
    }

    return movies;
  }

  private static Optional<String> getImagePath(String url) {
    try {
      String extension = url.substring(url.lastIndexOf("."));
      if (extension.length() > 4)
        extension = DEFAULT_IMAGE_EXT;
      String imageName = UUID.randomUUID() + extension;
      String localPath = IMAGE_DIR + File.separator + imageName;

      FileUtility.copyFromUrl(url, localPath);
      return Optional.ofNullable(localPath);
    }
    catch (IOException ex) {
      Logger
        .getLogger(RssMovieProjectionRepository.class.getName())
        .log(Level.SEVERE, null, ex);
    }
    return Optional.empty();
  }

  private static Optional<PersonProjection> parsePerson(String fullName) {
    var name = fullName.trim().split(" ");

    return name.length != 0
             ? Optional.of(new PersonProjection(name[0],
                                                name.length > 1 ? name[1] : ""))
             : Optional.empty();
  }

  private enum TagType {
    MOVIE("item"),
    TITLE("title"),
    ORIGINAL_TITLE("orignaziv"),
    PUB_DATE("pubDate"),
    DURATION_MINUTES("trajanje"),
    DESCRIPTION("description"),
    DIRECTORS("redatelj"),
    ACTORS("glumci"),
    GENRES("zanr"),
    WEB_PATH("link"),
    IMAGE_PATH("plakat");

    private static final Map<String, TagType> _mappings = new HashMap<>();

    static {
      for (TagType status : TagType.values())
        _mappings.put(
          status.name,
          status
        );
    }

    private final String name;

    private TagType(String name) {
      this.name = name;
    }

    private static Optional<TagType> fromString(String name) {
      return Optional.ofNullable(_mappings.get(name));
    }
  }

}
