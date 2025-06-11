package model.image.factory;

import model.image.Image;
import model.image.PPMImage;

/**
 * A factory for creating images. It uses the given parameters to parse the image and creates
 * an image according to the type of that image.
 */
public class ImageFactory {

  /**
   * Finds a file and parse its format to create an Image object of that format.
   * @param filepath the filepath to find it on a computer
   * @return the given type to image
   */
  public static Image createImage(String filepath) {
    String fileExtension = filepath.split("\\.")[filepath.split(
            "\\.").length - 1];

    if (fileExtension.equalsIgnoreCase("ppm")) {
      return new PPMImage(filepath);
    } else {
      throw new IllegalArgumentException("File extension does not exist!");
    }
  }

}
