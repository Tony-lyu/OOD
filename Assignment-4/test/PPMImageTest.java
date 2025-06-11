import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.image.Image;
import model.image.PPMImage;

import static model.image.factory.ImageFactory.createImage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for class PPM Image.
 */
public class PPMImageTest {

  /**
   * Setting up the images to be tested.
   */
  @BeforeClass
  public static void setup() {
    String koalaPath = "res/Koala.ppm";
    String photoPath = "res/photo.ppm";

    // Horizontal flipped koala
    Image koalaHorizontal = createImage(koalaPath);
    koalaHorizontal.flipHorizontal();
    koalaHorizontal.toFile("res/horizontal-Koala.ppm");

    // Vertical flipped koala
    Image koalaVertical = createImage(koalaPath);
    koalaVertical.flipVertical();
    koalaVertical.toFile("res/vertical-Koala.ppm");

    // Brightened koala
    Image koalaBrighten = createImage(koalaPath);
    koalaBrighten.brightenColor(50);
    koalaBrighten.toFile("res/Koala-brighten-50.ppm");

    // Darkened koala
    Image koalaDarkened = createImage(koalaPath);
    koalaDarkened.darkenColor(50);
    koalaDarkened.toFile("res/Koala-darken-50.ppm");

    // Greyscale Red
    Image koalaRed = createImage(koalaPath);
    koalaRed.convertGreyscaleRed();
    koalaRed.toFile("res/red-koala.ppm");

    // Greyscale Green
    Image koalaGreen = createImage(koalaPath);
    koalaGreen.convertGreyscaleGreen();
    koalaGreen.toFile("res/green-koala.ppm");

    // Greyscale Blue
    Image koalaBlue = createImage(koalaPath);
    koalaBlue.convertGreyscaleBlue();
    koalaBlue.toFile("res/blue-koala.ppm");

    // Greyscale Value
    Image koalaValue = createImage(koalaPath);
    koalaValue.convertGreyscaleValue();
    koalaValue.toFile("res/value-koala.ppm");

    // Greyscale Intensity
    Image koalaIntensity = createImage(koalaPath);
    koalaIntensity.convertGreyscaleIntensity();
    koalaIntensity.toFile("res/intensity-koala.ppm");

    // Greyscale Luma
    Image koalaLuma = createImage(koalaPath);
    koalaLuma.convertGreyscaleLuma();
    koalaLuma.toFile("res/luma-koala.ppm");

    // Loading photo : Example photo

    // Horizontal flipped photo
    Image photoHorizontal = createImage(photoPath);
    photoHorizontal.flipHorizontal();
    photoHorizontal.toFile("res/horizontal-photo.ppm");

    // Vertical flipped photo
    Image photoVertical = createImage(photoPath);
    photoVertical.flipVertical();
    photoVertical.toFile("res/vertical-photo.ppm");

    // Brightened photo
    Image photoBrighten = createImage(photoPath);
    photoBrighten.brightenColor(100);
    photoBrighten.toFile("res/photo-brighten-100.ppm");

    // Darkened photo
    Image photoDarkened = createImage(photoPath);
    photoDarkened.darkenColor(100);
    photoDarkened.toFile("res/photo-darken-100.ppm");

    // Greyscale Red
    Image photoRed = createImage(photoPath);
    photoRed.convertGreyscaleRed();
    photoRed.toFile("res/red-photo.ppm");

    // Greyscale Green
    Image photoGreen = createImage(photoPath);
    photoGreen.convertGreyscaleGreen();
    photoGreen.toFile("res/green-photo.ppm");

    // Greyscale Blue
    Image photoBlue = createImage(photoPath);
    photoBlue.convertGreyscaleBlue();
    photoBlue.toFile("res/blue-photo.ppm");

    // Greyscale Value
    Image photoValue = createImage(photoPath);
    photoValue.convertGreyscaleValue();
    photoValue.toFile("res/value-photo.ppm");

    // Greyscale Intensity
    Image photoIntensity = createImage(photoPath);
    photoIntensity.convertGreyscaleIntensity();
    photoIntensity.toFile("res/intensity-photo.ppm");

    // Greyscale Luma
    Image photoLuma = createImage(photoPath);
    photoLuma.convertGreyscaleLuma();
    photoLuma.toFile("res/luma-photo.ppm");

  }

  /**
   * clearing unwanted images created by the tests.
   */
  @AfterClass
  public static void finish() {
    File koalaHorizontal = new File("res/horizontal-Koala.ppm");
    File koalaVertical = new File("res/vertical-Koala.ppm");
    File koalaBrighten = new File("res/Koala-brighten-50.ppm");
    File koalaDarkened = new File("res/Koala-darken-50.ppm");
    File koalaRed = new File("res/red-koala.ppm");
    File koalaGreen = new File("res/green-koala.ppm");
    File koalaBlue = new File("res/blue-koala.ppm");
    File koalaValue = new File("res/value-koala.ppm");
    File koalaIntensity = new File("res/intensity-koala.ppm");
    File koalaLuma = new File("res/luma-koala.ppm");
    File photoHorizontal = new File("res/horizontal-photo.ppm");
    File photoVertical = new File("res/vertical-photo.ppm");
    File photoBrighten = new File("res/photo-brighten-100.ppm");
    File photoDarkened = new File("res/photo-darken-100.ppm");
    File photoRed = new File("res/red-photo.ppm");
    File photoGreen = new File("res/green-photo.ppm");
    File photoBlue = new File("res/blue-photo.ppm");
    File photoValue = new File("res/value-photo.ppm");
    File photoIntensity = new File("res/intensity-photo.ppm");
    File photoLuma = new File("res/luma-photo.ppm");

    File[] files = {
        koalaHorizontal, koalaVertical, koalaBrighten, koalaDarkened, koalaRed,
        koalaGreen, koalaBlue, koalaBlue, koalaValue, koalaIntensity, koalaLuma,
        photoHorizontal, photoVertical, photoBrighten, photoDarkened, photoRed,
        photoGreen, photoBlue, photoBlue, photoValue, photoIntensity, photoLuma
    };
    for (File file : files) {
      if (file.delete()) {
        boolean success = file.exists();
        assertFalse(success);
      }
    }
  }

  @Test
  public void allGreyscale() {
    String koalaPath = "res/Koala.ppm";
    Image koalaRed = createImage(koalaPath);
    Image koalaGreen = createImage(koalaPath);
    Image koalaBlue = createImage(koalaPath);
    Image koalaValue = createImage(koalaPath);
    Image koalaIntensity = createImage(koalaPath);
    Image koalaLuma = createImage(koalaPath);

    koalaRed.convertGreyscaleRed();
    koalaGreen.convertGreyscaleGreen();
    koalaBlue.convertGreyscaleBlue();
    koalaValue.convertGreyscaleValue();
    koalaIntensity.convertGreyscaleIntensity();
    koalaLuma.convertGreyscaleLuma();

    String photoPath = "res/photo.ppm";
    Image photoRed = createImage(photoPath);
    Image photoGreen = createImage(photoPath);
    Image photoBlue = createImage(photoPath);
    Image photoValue = createImage(photoPath);
    Image photoIntensity = createImage(photoPath);
    Image photoLuma = createImage(photoPath);

    photoRed.convertGreyscaleRed();
    photoGreen.convertGreyscaleGreen();
    photoBlue.convertGreyscaleBlue();
    photoValue.convertGreyscaleValue();
    photoIntensity.convertGreyscaleIntensity();
    photoLuma.convertGreyscaleLuma();

    Image[] images = {koalaRed, koalaGreen, koalaBlue, koalaValue, koalaIntensity, koalaLuma,
                      photoRed, photoGreen, photoBlue, photoValue, photoIntensity, photoLuma};
    Scanner sc;
    for (Image image : images) {
      image.toFile("res/temp.ppm");
      try {
        sc = new Scanner(new FileInputStream("res/temp.ppm"));
      } catch (FileNotFoundException e) {
        break;
      }
      StringBuilder builder = new StringBuilder();

      while (sc.hasNextLine()) {
        String temp = sc.nextLine();
        if (temp.charAt(0) != '#') {
          builder.append(temp).append(System.lineSeparator());
        }
      }

      sc = new Scanner(builder.toString());
      // Skips token
      sc.next();
      int width = sc.nextInt();
      int height = sc.nextInt();
      // Skips max value
      sc.next();

      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int r = sc.nextInt();
          int g = sc.nextInt();
          int b = sc.nextInt();
          assertEquals(r, g);
          assertEquals(g, b);
          assertEquals(r, b);
        }
      }
    }
    File file = new File("res/temp.ppm");
    assertTrue(file.delete());
  }

  /**
   * Test for horizontal flip.
   */
  @Test
  public void testFlipHorizontal() {
    Image i1 = new PPMImage("res/Koala.ppm");
    i1.flipHorizontal();
    Image i2 = new PPMImage("res/horizontal-Koala.ppm");
    assertEquals(i1, i2);

    Image p1 = new PPMImage("res/photo.ppm");
    p1.flipHorizontal();
    Image p2 = new PPMImage("res/horizontal-photo.ppm");
    assertEquals(p1, p2);
  }

  /**
   * Test for horizontal flip twice.
   */
  @Test
  public void testFlipHorizontalTwice() {
    Image i1 = new PPMImage("res/Koala.ppm");
    Image i2 = new PPMImage("res/Koala.ppm");
    i1.flipHorizontal();
    i1.flipHorizontal();
    assertEquals(i1, i2);

    Image p1 = new PPMImage("res/photo.ppm");
    Image p2 = new PPMImage("res/photo.ppm");
    p1.flipHorizontal();
    p1.flipHorizontal();
    assertEquals(p1, p2);
  }

  /**
   * Test for vertical flip.
   */
  @Test
  public void testFlipVertical() {
    Image i1 = new PPMImage("res/Koala.ppm");
    i1.flipVertical();
    Image i2 = new PPMImage("res/vertical-Koala.ppm");
    assertEquals(i1, i2);

    Image p1 = new PPMImage("res/photo.ppm");
    p1.flipVertical();
    Image p2 = new PPMImage("res/vertical-photo.ppm");
    assertEquals(p1, p2);
  }

  /**
   * Test for horizontal flip twice.
   */
  @Test
  public void testFlipVerticalTwice() {
    Image i1 = new PPMImage("res/Koala.ppm");
    Image i2 = new PPMImage("res/Koala.ppm");
    i1.flipVertical();
    i1.flipVertical();
    assertEquals(i1, i2);

    Image p1 = new PPMImage("res/photo.ppm");
    Image p2 = new PPMImage("res/photo.ppm");
    p1.flipVertical();
    p2.flipVertical();
    assertEquals(p1, p2);
  }

  /**
   * Test for brighten.
   */
  @Test
  public void testBrighten() {
    Image i1 = new PPMImage("res/Koala.ppm");
    i1.brightenColor(50);
    Image i2 = new PPMImage("res/Koala-brighten-50.ppm");
    assertEquals(i1, i2);

    Image p1 = new PPMImage("res/photo.ppm");
    p1.brightenColor(100);
    Image p2 = new PPMImage("res/photo-brighten-100.ppm");
    assertEquals(p1, p2);
  }

  /**
   * Test for invalid brighten.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBrighten() {
    Image i1 = new PPMImage("res/Koala.ppm");
    i1.brightenColor(-2);
  }

  /**
   * Test for darken.
   */
  @Test
  public void testDarken() {
    Image i1 = new PPMImage("res/Koala.ppm");
    i1.darkenColor(50);
    Image i2 = new PPMImage("res/Koala-darken-50.ppm");
    assertEquals(i1, i2);

    Image p1 = new PPMImage("res/photo.ppm");
    p1.darkenColor(100);
    Image p2 = new PPMImage("res/photo-darken-100.ppm");
    assertEquals(p1, p2);
  }

  /**
   * Test for invalid darken.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDarken() {
    Image i1 = new PPMImage("res/Koala.ppm");
    i1.darkenColor(-5);
  }

  /**
   * Test for grey scale red.
   */
  @Test
  public void testGreyScaleRed() {
    Image i1 = new PPMImage("res/Koala.ppm");
    i1.convertGreyscaleRed();
    Image i2 = new PPMImage("res/red-koala.ppm");
    assertEquals(i1, i2);

    Image p1 = new PPMImage("res/photo.ppm");
    p1.convertGreyscaleRed();
    Image p2 = new PPMImage("res/red-photo.ppm");
    assertEquals(p1, p2);
  }

  /**
   * Test for doing greyscale on grey scaled image.
   */
  @Test
  public void testGreyScaleRedAfterGreyScale() {
    Image i1 = new PPMImage("res/Koala.ppm");
    Image i2 = new PPMImage("res/Koala.ppm");
    i1.convertGreyscaleRed();
    i2.convertGreyscaleRed();
    i1.convertGreyscaleIntensity();
    assertEquals(i1, i2);

    Image p1 = new PPMImage("res/photo.ppm");
    Image p2 = new PPMImage("res/photo.ppm");
    p1.convertGreyscaleRed();
    p2.convertGreyscaleRed();
    p1.convertGreyscaleIntensity();
    assertEquals(p1, p2);
  }

  /**
   * Test for grey scale green.
   */
  @Test
  public void testGreyScaleGreen() {
    Image i1 = new PPMImage("res/Koala.ppm");
    i1.convertGreyscaleGreen();
    Image i2 = new PPMImage("res/green-koala.ppm");
    assertEquals(i1, i2);

    Image p1 = new PPMImage("res/photo.ppm");
    p1.convertGreyscaleGreen();
    Image p2 = new PPMImage("res/green-photo.ppm");
    assertEquals(p1, p2);
  }

  /**
   * Test for grey scale blue.
   */
  @Test
  public void testGreyScaleBlue() {
    Image i1 = new PPMImage("res/Koala.ppm");
    i1.convertGreyscaleBlue();
    Image i2 = new PPMImage("res/blue-koala.ppm");
    assertEquals(i1, i2);

    Image p1 = new PPMImage("res/photo.ppm");
    p1.convertGreyscaleBlue();
    Image p2 = new PPMImage("res/blue-photo.ppm");
    assertEquals(p1, p2);
  }

  /**
   * Test for grey scale value.
   */
  @Test
  public void testGreyScaleValue() {
    Image i1 = new PPMImage("res/Koala.ppm");
    i1.convertGreyscaleValue();
    Image i2 = new PPMImage("res/value-koala.ppm");
    assertEquals(i1, i2);

    Image p1 = new PPMImage("res/photo.ppm");
    p1.convertGreyscaleValue();
    Image p2 = new PPMImage("res/value-photo.ppm");
    assertEquals(p1, p2);
  }

  /**
   * Test for grey scale intensity.
   */
  @Test
  public void testGreyScaleIntensity() {
    Image i1 = new PPMImage("res/Koala.ppm");
    i1.convertGreyscaleIntensity();
    Image i2 = new PPMImage("res/intensity-koala.ppm");
    assertEquals(i1, i2);

    Image p1 = new PPMImage("res/photo.ppm");
    p1.convertGreyscaleIntensity();
    Image p2 = new PPMImage("res/intensity-photo.ppm");
    assertEquals(p1, p2);
  }

  /**
   * Test for grey scale luma.
   */
  @Test
  public void testGreyScaleLuma() {
    Image i1 = new PPMImage("res/Koala.ppm");
    i1.convertGreyscaleLuma();
    Image i2 = new PPMImage("res/luma-koala.ppm");
    assertEquals(i1, i2);

    Image p1 = new PPMImage("res/photo.ppm");
    p1.convertGreyscaleLuma();
    Image p2 = new PPMImage("res/luma-photo.ppm");
    assertEquals(p1, p2);
  }

  /**
   * Test for equals.
   */
  @Test
  public void testEquals() {
    Image i1 = new PPMImage("res/Koala.ppm");
    assertTrue(i1.equals(i1));
  }

  @Test
  public void testHash() {
    Image i1 = new PPMImage("res/Koala.ppm");
    assertEquals(i1.hashCode(), 1984169649);
    Image i2 = new PPMImage("res/photo.ppm");
    assertEquals(i2.hashCode(), -1362913485);
    i2.flipVertical();
    assertEquals(i2.hashCode(), 1243157199);
    i1.flipHorizontal();
    assertEquals(i1.hashCode(), 1482849745);
  }
}