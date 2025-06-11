package model.pixel;

import java.util.Objects;

/**
 * Represents the RGB representation for color which is a type of Pixel.
 */
public class RGBPixel extends AbstractPixel {

  /**
   * Creates a Pixel represented by RGB given first color, second color, third color,
   * and a max value.
   *
   * @param color1 the first color (representing red).
   * @param color2 the second color (representing green).
   * @param color3 the third color (representing blue).
   * @param maxValue the max value is the maximum value that a channel can be.
   * @throws IllegalArgumentException if the colors or max value are less than 0
   *         or any of the colors exceed the max value.
   */
  public RGBPixel(int color1, int color2, int color3, int maxValue)
          throws IllegalArgumentException {
    super(color1, color2, color3, maxValue);
  }

  /**
   * Creates a Pixel represented by RGB given first color, second color, third color and the
   * max value is set to 255.
   * @param color1 the first color (representing red).
   * @param color2 the second color (representing green).
   * @param color3 the third color (representing blue).
   * @throws IllegalArgumentException if the colors are less than 0 or exceed the max value.
   */
  public RGBPixel(int color1, int color2, int color3) throws IllegalArgumentException {
    super(color1, color2, color3, 255);
  }

  @Override
  public void brighten(int change) throws IllegalArgumentException {
    if (change < 0) {
      throw new IllegalArgumentException("Color cannot be brightened by a negative number!");
    }
    this.color1 += change;
    this.color2 += change;
    this.color3 += change;
    if (this.color1 > this.maxValue) {
      this.color1 = this.maxValue;
    }
    if (this.color2 > this.maxValue) {
      this.color2 = this.maxValue;
    }
    if (this.color3 > this.maxValue) {
      this.color3 = this.maxValue;
    }
    this.updateComponents();
  }

  @Override
  public void darken(int change) throws IllegalArgumentException {
    if (change < 0) {
      throw new IllegalArgumentException("Color cannot be darkened by a negative number!");
    }
    this.color1 -= change;
    this.color2 -= change;
    this.color3 -= change;
    if (this.color1 < 0) {
      this.color1 = 0;
    }
    if (this.color2 < 0) {
      this.color2 = 0;
    }
    if (this.color3 < 0) {
      this.color3 = 0;
    }
    this.updateComponents();
  }

  @Override
  public void setAllRed() {
    this.color2 = this.color1;
    this.color3 = this.color1;
    this.updateComponents();
  }

  @Override
  public void setAllGreen() {
    this.color1 = this.color2;
    this.color3 = this.color2;
    this.updateComponents();
  }

  @Override
  public void setAllBlue() {
    this.color1 = this.color3;
    this.color2 = this.color3;
    this.updateComponents();
  }

  @Override
  public void setAllValue() {
    this.color1 = this.value;
    this.color2 = this.value;
    this.color3 = this.value;
    this.updateComponents();
  }

  @Override
  public void setAllIntensity() {
    this.color1 = (int) this.intensity;
    this.color2 = (int) this.intensity;
    this.color3 = (int) this.intensity;
    this.updateComponents();
  }

  @Override
  public void setAllLuma() {
    this.color1 = (int) this.luma;
    this.color2 = (int) this.luma;
    this.color3 = (int) this.luma;
    this.updateComponents();
  }

  @Override
  public String toString() {
    return String.format("%d\n%d\n%d\n", this.color1, this.color2, this.color3);
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof RGBPixel)) {
      return false;
    }
    RGBPixel pixel = (RGBPixel) obj;
    return this.color1 == pixel.color1
            && this.color2 == pixel.color2
            && this.color3 == pixel.color3;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.color1, this.color2, this.color3);
  }
}
