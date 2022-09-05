/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.lib.utility;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author kbratko
 */
public final class IconUtility {

  private IconUtility() {
  }

  public static ImageIcon createIcon(File file, int width, int height)
    throws IOException {
    BufferedImage bufferedImage = ImageIO.read(file);
    Image image = bufferedImage.getScaledInstance(width,
                                                  height,
                                                  Image.SCALE_SMOOTH);
    return new ImageIcon(image);
  }

}
