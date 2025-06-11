package model.pixel;

/**
 * Represents an Abstract Color which is a type of Color.
 */
abstract class AbstractPixel implements Pixel {
  protected int color1;
  protected int color2;
  protected int color3;
  protected final int maxValue;
  protected int value;
  protected double intensity;
  protected double luma;

  /**
   * Abstract constructor of pixel.
   * @param color1 Red on the RGB scale
   * @param color2 Green on the RGB scale
   * @param color3 Blue on the RGB scale
   * @param maxValue the max value each component can runProcessor to.
   * @throws IllegalArgumentException if the colors or max value are less than 0
   *         or any of the colors exceed the max value.
   */
  protected AbstractPixel(int color1, int color2, int color3, int maxValue)
          throws IllegalArgumentException {
    if (color1 < 0 || color2 < 0 || color3 < 0 || maxValue < 0) {
      throw new IllegalArgumentException("One or more inputs are less than 0.");
    }
    this.maxValue = maxValue;
    if (color1 > this.maxValue || color2 > this.maxValue || color3 > this.maxValue) {
      throw new IllegalArgumentException(String.format("Value of one or more colors exceeds %d",
              this.maxValue));
    }
    this.color1 = color1;
    this.color2 = color2;
    this.color3 = color3;
    this.updateComponents();
  }


  /**
   * update value, intensity and luma for this pixel.
   */
  protected void updateComponents() {
    this.value = Math.max(Math.max(this.color1, this.color2), this.color3);
    this.intensity = ((double) this.color1 + this.color2 + this.color3) / 3.0;
    this.luma = 0.2126 * this.color1 + 0.7152 * this.color2 + 0.0722 * this.color3;
  }
}
