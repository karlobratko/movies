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
import java.util.Arrays;
import java.util.Optional;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author kbratko
 */
public final class FileUtility {

  private static final String DIALOG_UPLOAD_TEXT = "Upload";

  private FileUtility() {
  }

  public static Optional<File> uploadFile(String description,
                                          String... extensions) {
    JFileChooser chooser =
                 new JFileChooser(
                   FileSystemView.getFileSystemView().getHomeDirectory());
    chooser.setFileFilter(new FileNameExtensionFilter(description, extensions));
    chooser.setDialogTitle(DIALOG_UPLOAD_TEXT);
    chooser.setApproveButtonText(DIALOG_UPLOAD_TEXT);
    chooser.setApproveButtonToolTipText(DIALOG_UPLOAD_TEXT);
    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
      File selectedFile = chooser.getSelectedFile();
      String extension = selectedFile.getName()
             .substring(selectedFile.getName().lastIndexOf(".") + 1);

      return selectedFile.exists() &&
             Arrays.asList(extensions).contains(extension.toLowerCase())
               ? Optional.of(selectedFile)
               : Optional.empty();
    }
    return Optional.empty();
  }

  public static void copyFromUrl(String urlSource, String localDestination)
    throws MalformedURLException, IOException {
    createDirHierarchy(localDestination);
    HttpURLConnection con =
                      HttpURLConnectionFactory.getHttpURLConnection(urlSource);
    try ( InputStream is = con.getInputStream()) {
      Files.copy(is, Paths.get(localDestination));
    }
  }

  public static void copy(String source, String localDestination)
    throws MalformedURLException, IOException {
    createDirHierarchy(localDestination);
    Files.copy(Paths.get(source), Paths.get(localDestination));
  }

  private static void createDirHierarchy(String destination)
    throws IOException {
    String dir = destination.substring(0,
                                       destination.lastIndexOf(File.separator));
    if (!Files.exists(Paths.get(dir)))
      Files.createDirectories(Paths.get(dir));
  }

}
