package model.image;

/**
 * Represents an Image in the Image Processor.
 */
public interface Image {

  /**
   * flips this image horizontally.
   */
  void flipHorizontal();

  /**
   * flips this image vertically.
   */
  void flipVertical();

  /**
   * brighten this image by a value given by the parameter.
   * @param change the value to be brightened.
   */
  void brightenColor(int change);

  /**
   * darken this image by a value given by the parameter.
   * @param change the value to be darkened.
   */
  void darkenColor(int change);

  /**
   * Converts this image to greyscale by setting all components of each pixel in the Image
   * to its red value on the RGB scale.
   */
  void convertGreyscaleRed();

  /**
   * Converts this image to greyscale by setting all components of each pixel in the Image
   * to its green value on the RGB scale.
   */
  void convertGreyscaleGreen();

  /**
   * Converts this image to greyscale by setting all components of each pixel in the Image
   * to its blue value on the RGB scale.
   */
  void convertGreyscaleBlue();

  /**
   * Converts this image to greyscale by setting all components of each pixel in the Image
   * to the value of each pixel.
   */
  void convertGreyscaleValue();

  /**
   * Converts this image to greyscale by setting all components of each pixel in the Image
   * to the intensity of each pixel.
   */
  void convertGreyscaleIntensity();

  /**
   * Converts this image to greyscale by setting all components of each pixel in the Image
   * to the luma of each pixel.
   */
  void convertGreyscaleLuma();

  /**
   * Write the converted image to a new file on the computer and the file path is given by
   * the parameter.
   * @param filepath the filepath to written to
   */
  void toFile(String filepath);
}
