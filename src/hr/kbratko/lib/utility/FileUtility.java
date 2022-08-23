/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.lib.utility;

import hr.kbratko.lib.factory.HttpURLConnectionFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author kbratko
 */
public class FileUtility {

  public static void copyFromUrl(String urlSource, String localDestination)
    throws MalformedURLException, IOException {
    createDirHierarchy(localDestination);
    HttpURLConnection con =
                      HttpURLConnectionFactory.getHttpURLConnection(urlSource);
    try ( InputStream is = con.getInputStream()) {
      Files.copy(is, Paths.get(localDestination));
    }
  }

  private static void createDirHierarchy(String destination)
    throws IOException {
    String dir = destination.substring(0,
                                       destination.lastIndexOf(File.separator));
    if (!Files.exists(Paths.get(dir)))
      Files.createDirectories(Paths.get(dir));
  }

}
