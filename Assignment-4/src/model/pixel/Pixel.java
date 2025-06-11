package model.pixel;

/**
 * Represents a Color Representation in the Image Processor.
 */
public interface Pixel {

  /**
   * Brightens the Pixel by the given amount.
   * @param change the amount the Pixel will be brightened.
   * @throws IllegalArgumentException if the change is negative.
   */
  void brighten(int change) throws IllegalArgumentException;

  /**
   * Darkens the Pixel by the given amount.
   * @param change the amount the Pixel will be darkened.
   * @throws IllegalArgumentException if the change is negative.
   */
  void darken(int change) throws IllegalArgumentException;

  /**
   * Sets all the components equal to the red component.
   */
  void setAllRed();

  /**
   * Sets all the components equal to the green component.
   */
  void setAllGreen();

  /**
   * Sets all the components equal to the blue component.
   */
  void setAllBlue();

  /**
   * Sets all the components equal to the value component.
   */
  void setAllValue();

  /**
   * Sets all the components equal to the intensity component.
   */
  void setAllIntensity();

  /**
   * Sets all the components equal to the luma component.
   */
  void setAllLuma();
}
