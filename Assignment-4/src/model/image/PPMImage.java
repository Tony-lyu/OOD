package model.image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import model.pixel.Pixel;
import model.pixel.RGBPixel;

/**
 * A class that represent a PPM Image. It implements Image which contains several methods that
 * can be used by all images. It has a filePath to lead the program to find it on a pc, height
 * and width, max Value it can have, and the photo is accessed, modified or enhanced by dividing
 * it into Pixels.
 */
public class PPMImage implements Image {
  private final String filePath;
  private int height;
  private int width;
  private int maxValue;
  private Pixel[][] colors;

  /**
   * Constructor for PPMImage that takes in a String filePath.
   *
   * @param filePath The filePath to lead program find the image on the computer
   */
  public PPMImage(String filePath) {
    this.filePath = filePath;
    this.initFile();
  }

  /**
   * Initializes the width, height, and colors from a PPM file.
   *
   * @throws IllegalArgumentException if the PPM file does not exist, or it is invalid.
   */
  protected void initFile() throws IllegalArgumentException {
    Scanner scanner;
    try {
      scanner = new Scanner(new FileInputStream(this.filePath));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File does not exist!");
    }
    StringBuilder builder = new StringBuilder();

    while (scanner.hasNextLine()) {
      String temp = scanner.nextLine();
      if (temp.charAt(0) != '#') {
        builder.append(temp).append(System.lineSeparator());
      }
    }

    scanner = new Scanner(builder.toString());
    String token;

    token = scanner.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file");
    }
    this.width = scanner.nextInt();
    this.height = scanner.nextInt();
    this.maxValue = scanner.nextInt();

    this.colors = new Pixel[this.height][this.width];

    for (int i = 0; i < this.height; i++) {
      Pixel[] row = new Pixel[this.width];
      for (int j = 0; j < this.width; j++) {
        int r = scanner.nextInt();
        int g = scanner.nextInt();
        int b = scanner.nextInt();
        row[j] = new RGBPixel(r, g, b, this.maxValue);
      }
      this.colors[i] = row;
    }
  }


  @Override
  public void flipHorizontal() {
    for (int i = 0; i < this.height; i++) {
      List<Pixel> reverseRow = new ArrayList<>(Arrays.asList(this.colors[i]));
      Collections.reverse(reverseRow);
      this.colors[i] = reverseRow.toArray(new Pixel[0]);
    }
  }

  @Override
  public void flipVertical() {
    List<Pixel[]> reverseVertical = new ArrayList<>(Arrays.asList(this.colors));
    Collections.reverse(reverseVertical);
    this.colors = reverseVertical.toArray(new Pixel[0][0]);
  }

  @Override
  public void brightenColor(int change) {
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        this.colors[i][j].brighten(change);
      }
    }
  }

  @Override
  public void darkenColor(int change) {
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        this.colors[i][j].darken(change);
      }
    }
  }

  @Override
  public void convertGreyscaleRed() {
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        this.colors[i][j].setAllRed();
      }
    }
  }

  @Override
  public void convertGreyscaleGreen() {
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        this.colors[i][j].setAllGreen();
      }
    }
  }

  @Override
  public void convertGreyscaleBlue() {
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        this.colors[i][j].setAllBlue();
      }
    }
  }

  @Override
  public void convertGreyscaleValue() {
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        this.colors[i][j].setAllValue();
      }
    }
  }

  @Override
  public void convertGreyscaleIntensity() {
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        this.colors[i][j].setAllIntensity();
      }
    }
  }

  @Override
  public void convertGreyscaleLuma() {
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        this.colors[i][j].setAllLuma();
      }
    }
  }


  @Override
  public void toFile(String filepath) {
    File file = new File(filepath);
    FileOutputStream output;
    try {
      if (!file.exists()) {
        boolean success = file.createNewFile();
      }
      output = new FileOutputStream(file);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to create new file");
    }

    StringBuilder builder = new StringBuilder();
    builder.append(String.format("P3\n%d\n%d\n%d\n", this.width, this.height, this.maxValue));
    for (Pixel[] row : this.colors) {
      for (Pixel color : row) {
        builder.append(color.toString());
      }
    }

    byte[] byteArray = builder.toString().getBytes();

    try {
      output.write(byteArray);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to write to file.");
    }
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof PPMImage) {
      return equalsHelp(((PPMImage) o).colors);
    } else {
      return false;
    }
  }

  /**
   * Helper for the overridden .equals method in PPMImage.
   * Checks whether each Pixel in both pictures are the same.
   *
   * @param colors the colors that is being compared to this PPMImage's colors.
   * @return true if the colors are the same, otherwise false.
   */
  private boolean equalsHelp(Pixel[][] colors) {
    boolean result = true;
    for (int i = 0; i < this.height; i++) {
      for (int k = 0; k < this.width; k++) {
        result = result && this.colors[i][k].equals(colors[i][k]);
      }
    }
    return result;
  }

  @Override
  public int hashCode() {
    return Arrays.deepHashCode(this.colors);
  }
}
