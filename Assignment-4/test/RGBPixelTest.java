import org.junit.Before;
import org.junit.Test;

import model.pixel.Pixel;
import model.pixel.RGBPixel;

import static org.junit.Assert.assertEquals;

/**
 * Test class for RGB Pixel.
 */
public class RGBPixelTest {
  private Pixel pixelRed;
  private Pixel pixelGreen;
  private Pixel pixelBlue;
  private Pixel pixelBlack;
  private Pixel pixelWhite;
  private Pixel pixelYellow;
  private Pixel pixelDarkSlateGray;
  private Pixel pixelOrchid;

  @Before
  public void setup() {
    this.pixelRed = new RGBPixel(255, 0, 0);
    this.pixelGreen = new RGBPixel(0, 255,0);
    this.pixelBlue = new RGBPixel(0,0,255);
    this.pixelBlack = new RGBPixel(0,0,0);
    this.pixelWhite = new RGBPixel(255,255,255);
    this.pixelYellow = new RGBPixel(255, 255, 0, 511);
    this.pixelDarkSlateGray = new RGBPixel(47, 79, 79, 127);
    this.pixelOrchid = new RGBPixel(218, 112, 214, 1023);
  }

  @Test
  public void testConstructor() {
    assertEquals(this.pixelRed, new RGBPixel(255, 0,0));
    assertEquals(this.pixelRed, new RGBPixel(255, 0,0,255));
    assertEquals(this.pixelGreen, new RGBPixel(0,255,0));
    assertEquals(this.pixelGreen, new RGBPixel(0, 255,0,255));
    assertEquals(this.pixelBlue, new RGBPixel(0,0,255));
    assertEquals(this.pixelBlue, new RGBPixel(0, 0,255,255));
    assertEquals(this.pixelBlack, new RGBPixel(0,0,0));
    assertEquals(this.pixelBlack, new RGBPixel(0, 0,0,255));
    assertEquals(this.pixelWhite, new RGBPixel(255,255,255));
    assertEquals(this.pixelWhite, new RGBPixel(255, 255,255,255));
    assertEquals(this.pixelYellow, new RGBPixel(255, 255, 0, 511));
    assertEquals(this.pixelDarkSlateGray, new RGBPixel(47, 79, 79,
            127));
    assertEquals(this.pixelOrchid, new RGBPixel(218, 112, 214, 1023));

  }

  @Test (expected = IllegalArgumentException.class)
  public void testNegativeRedComponent() {
    new RGBPixel(-1,0,0,511);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNegativeGreenComponent() {
    new RGBPixel(30,-5,20);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNegativeBlueComponent() {
    new RGBPixel(130,90,-100);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNegativeMaxValue() {
    new RGBPixel(0,0,0, -10);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAllNegative() {
    new RGBPixel(-100,-200,-245, -600);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testRedComponentGreaterThanMaxValue() {
    new RGBPixel(256,0,0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testGreenComponentGreaterThanMaxValue() {
    new RGBPixel(40,515,378, 511);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testBlueComponentGreaterThanMaxValue() {
    new RGBPixel(570,780,2000, 1023);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAllGreaterThanMaxValue() {
    new RGBPixel(1000,1000,1000, 127);
  }

  @Test
  public void brighten() {
    assertEquals(this.pixelRed, new RGBPixel(255,0,0));
    this.pixelRed.brighten(5);
    assertEquals(this.pixelRed, new RGBPixel(255, 5, 5));
    this.pixelRed.brighten(100);
    assertEquals(this.pixelRed, new RGBPixel(255, 105, 105));

    this.pixelGreen.brighten(1000);
    assertEquals(this.pixelGreen, new RGBPixel(255,255,255));

    this.pixelBlack.brighten(1);
    assertEquals(this.pixelBlack, new RGBPixel(1,1,1));

    assertEquals(this.pixelWhite, new RGBPixel(255,255,255));
    this.pixelWhite.brighten(2);
    assertEquals(this.pixelWhite, new RGBPixel(255,255,255));

    this.pixelYellow.brighten(20);
    assertEquals(this.pixelYellow, new RGBPixel(275,275,20,511));
    this.pixelYellow.brighten(20);
    assertEquals(this.pixelYellow, new RGBPixel(295,295,40,511));

    this.pixelDarkSlateGray.brighten(100);
    assertEquals(this.pixelDarkSlateGray, new RGBPixel(127,127,127,
            127));
    assertEquals(this.pixelDarkSlateGray, new RGBPixel(127,127,127));

    this.pixelOrchid.brighten(500);
    assertEquals(this.pixelOrchid, new RGBPixel(718,612,714,
            1023));
  }

  @Test
  public void testBrightenThenDarken() {
    this.pixelBlack.brighten(30);
    assertEquals(this.pixelBlack, new RGBPixel(30,30,30));
    this.pixelBlack.darken(15);
    assertEquals(this.pixelBlack, new RGBPixel(15,15,15));
    this.pixelBlue.brighten(100);
    assertEquals(this.pixelBlue, new RGBPixel(100, 100, 255));
    this.pixelBlue.darken(1);
    assertEquals(this.pixelBlue, new RGBPixel(99, 99, 254));
    this.pixelBlack.brighten(30);
    assertEquals(this.pixelBlack, new RGBPixel(45, 45, 45));
    this.pixelBlack.darken(15);
    assertEquals(this.pixelBlack, new RGBPixel(30, 30, 30));
    this.pixelBlue.brighten(100);
    assertEquals(this.pixelBlue, new RGBPixel(199, 199, 255));
    this.pixelBlue.darken(1);
    assertEquals(this.pixelBlue, new RGBPixel(198, 198, 254));
  }

  @Test
  public void testGreyThenDarken() {
    this.pixelRed.setAllIntensity();
    assertEquals(this.pixelRed, new RGBPixel(85, 85, 85));
    this.pixelRed.darken(50);
    assertEquals(this.pixelRed, new RGBPixel(35, 35, 35));
    this.pixelGreen.setAllGreen();
    assertEquals(this.pixelGreen, new RGBPixel(255, 255, 255));
    this.pixelGreen.darken(10);
    assertEquals(this.pixelGreen, new RGBPixel(245, 245, 245));
  }

  @Test
  public void testGreyThenBrighten() {
    this.pixelRed.setAllIntensity();
    assertEquals(this.pixelRed, new RGBPixel(85, 85, 85));
    this.pixelRed.brighten(50);
    assertEquals(this.pixelRed, new RGBPixel(135, 135, 135));
    this.pixelGreen.setAllGreen();
    assertEquals(this.pixelGreen, new RGBPixel(255, 255, 255));
    this.pixelGreen.brighten(10);
    assertEquals(this.pixelGreen, new RGBPixel(255, 255, 255));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testBrightenNegativeValue() {
    this.pixelBlue.brighten(-10);
  }

  @Test
  public void darken() {
    assertEquals(this.pixelRed, new RGBPixel(255,0,0));
    this.pixelRed.darken(5);
    assertEquals(this.pixelRed, new RGBPixel(250, 0, 0));
    this.pixelRed.darken(100);
    assertEquals(this.pixelRed, new RGBPixel(150, 0, 0));

    this.pixelGreen.darken(1000);
    assertEquals(this.pixelGreen, new RGBPixel(0,0,0));

    this.pixelBlack.darken(1);
    assertEquals(this.pixelBlack, new RGBPixel(0,0,0));

    assertEquals(this.pixelWhite, new RGBPixel(255,255,255));
    this.pixelWhite.darken(2);
    assertEquals(this.pixelWhite, new RGBPixel(253,253,253));
    this.pixelWhite.darken(253);
    assertEquals(this.pixelWhite, this.pixelBlack);

    this.pixelYellow.darken(20);
    assertEquals(this.pixelYellow, new RGBPixel(235,235,0,511));
    assertEquals(this.pixelYellow, new RGBPixel(235,235,0));
    this.pixelYellow.darken(20);
    assertEquals(this.pixelYellow, new RGBPixel(215,215,0,511));

    this.pixelDarkSlateGray.darken(100);
    assertEquals(this.pixelDarkSlateGray, new RGBPixel(0,0,0,
            127));
    assertEquals(this.pixelDarkSlateGray, new RGBPixel(0,0,0));
    assertEquals(this.pixelDarkSlateGray, this.pixelBlack);

    this.pixelOrchid.darken(2);
    assertEquals(this.pixelOrchid, new RGBPixel(216,110,212,
            1023));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testDarkenNegativeValue() {
    this.pixelBlue.brighten(-255);
  }

  @Test
  public void setAllRed() {
    this.pixelRed.setAllRed();
    assertEquals(this.pixelRed, new RGBPixel(255,255,255));
    assertEquals(this.pixelRed, this.pixelWhite);

    this.pixelGreen.setAllRed();
    assertEquals(this.pixelGreen, new RGBPixel(0,0,0));
    assertEquals(this.pixelGreen, this.pixelBlack);

    this.pixelBlue.setAllRed();
    assertEquals(this.pixelBlue, new RGBPixel(0,0,0));
    assertEquals(this.pixelBlue, this.pixelBlack);
    assertEquals(this.pixelBlue, this.pixelGreen);

    this.pixelBlack.setAllRed();
    assertEquals(this.pixelBlack, new RGBPixel(0,0,0));
    assertEquals(this.pixelBlack, this.pixelGreen);
    assertEquals(this.pixelBlack, this.pixelBlue);

    this.pixelWhite.setAllRed();
    assertEquals(this.pixelWhite, new RGBPixel(255,255,255));
    assertEquals(this.pixelWhite, this.pixelRed);

    this.pixelYellow.setAllRed();
    assertEquals(this.pixelYellow, new RGBPixel(255,255,255));

    this.pixelDarkSlateGray.setAllRed();
    assertEquals(this.pixelDarkSlateGray, new RGBPixel(47,47,47));

    this.pixelOrchid.setAllRed();
    assertEquals(this.pixelOrchid, new RGBPixel(218,218,218));
  }

  @Test
  public void setAllGreen() {
    this.pixelRed.setAllGreen();
    assertEquals(this.pixelRed, new RGBPixel(0,0,0));
    assertEquals(this.pixelRed, this.pixelBlack);

    this.pixelGreen.setAllGreen();
    assertEquals(this.pixelGreen, new RGBPixel(255,255,255));
    assertEquals(this.pixelGreen, this.pixelWhite);

    this.pixelBlue.setAllGreen();
    assertEquals(this.pixelBlue, new RGBPixel(0,0,0));
    assertEquals(this.pixelBlue, this.pixelBlack);
    assertEquals(this.pixelBlue, this.pixelRed);

    this.pixelBlack.setAllGreen();
    assertEquals(this.pixelBlack, new RGBPixel(0,0,0));
    assertEquals(this.pixelBlack, this.pixelRed);
    assertEquals(this.pixelBlack, this.pixelBlue);

    this.pixelWhite.setAllGreen();
    assertEquals(this.pixelWhite, new RGBPixel(255,255,255));
    assertEquals(this.pixelWhite, this.pixelGreen);

    this.pixelYellow.setAllGreen();
    assertEquals(this.pixelYellow, new RGBPixel(255,255,255));

    this.pixelDarkSlateGray.setAllGreen();
    assertEquals(this.pixelDarkSlateGray, new RGBPixel(79,79,79));

    this.pixelOrchid.setAllGreen();
    assertEquals(this.pixelOrchid, new RGBPixel(112,112,112));
  }

  @Test
  public void setAllBlue() {
    this.pixelRed.setAllBlue();
    assertEquals(this.pixelRed, new RGBPixel(0,0,0));
    assertEquals(this.pixelRed, this.pixelBlack);

    this.pixelGreen.setAllBlue();
    assertEquals(this.pixelGreen, new RGBPixel(0,0,0));
    assertEquals(this.pixelGreen, this.pixelBlack);
    assertEquals(this.pixelGreen, this.pixelRed);

    this.pixelBlue.setAllBlue();
    assertEquals(this.pixelBlue, new RGBPixel(255,255,255));
    assertEquals(this.pixelBlue, this.pixelWhite);

    this.pixelBlack.setAllBlue();
    assertEquals(this.pixelBlack, new RGBPixel(0,0,0));
    assertEquals(this.pixelBlack, this.pixelRed);
    assertEquals(this.pixelBlack, this.pixelGreen);

    this.pixelWhite.setAllBlue();
    assertEquals(this.pixelWhite, new RGBPixel(255,255,255));
    assertEquals(this.pixelWhite, this.pixelBlue);

    this.pixelYellow.setAllBlue();
    assertEquals(this.pixelYellow, new RGBPixel(0,0,0));

    this.pixelDarkSlateGray.setAllBlue();
    assertEquals(this.pixelDarkSlateGray, new RGBPixel(79,79,79));

    this.pixelOrchid.setAllBlue();
    assertEquals(this.pixelOrchid, new RGBPixel(214,214,214));
  }

  @Test
  public void setAllValue() {
    this.pixelRed.setAllValue();
    assertEquals(this.pixelRed, new RGBPixel(255,255,255));
    assertEquals(this.pixelRed, this.pixelWhite);
    this.pixelGreen.setAllValue();
    assertEquals(this.pixelGreen, new RGBPixel(255,255,255));
    assertEquals(this.pixelGreen, this.pixelWhite);
    this.pixelBlue.setAllValue();
    assertEquals(this.pixelBlue, new RGBPixel(255,255,255));
    assertEquals(this.pixelBlue, this.pixelWhite);
    this.pixelBlack.setAllValue();
    assertEquals(this.pixelBlack, new RGBPixel(0,0,0));
    this.pixelWhite.setAllValue();
    assertEquals(this.pixelWhite, new RGBPixel(255,255,255));

    this.pixelYellow.setAllValue();
    assertEquals(this.pixelYellow, new RGBPixel(255,255,255));

    this.pixelDarkSlateGray.setAllValue();
    assertEquals(this.pixelDarkSlateGray, new RGBPixel(79,79,79));

    this.pixelOrchid.setAllValue();
    assertEquals(this.pixelOrchid, new RGBPixel(218,218,218));
  }

  @Test
  public void setAllIntensity() {
    this.pixelRed.setAllIntensity();
    assertEquals(this.pixelRed, new RGBPixel(85,85,85));
    this.pixelGreen.setAllIntensity();
    assertEquals(this.pixelGreen, new RGBPixel(85,85,85));
    this.pixelBlue.setAllIntensity();
    assertEquals(this.pixelBlue, new RGBPixel(85,85,85));
    this.pixelBlack.setAllIntensity();
    assertEquals(this.pixelBlack, new RGBPixel(0,0,0));
    this.pixelWhite.setAllIntensity();
    assertEquals(this.pixelWhite, new RGBPixel(255,255,255));

    this.pixelYellow.setAllIntensity();
    assertEquals(this.pixelYellow, new RGBPixel(170,170,170));

    this.pixelDarkSlateGray.setAllIntensity();
    assertEquals(this.pixelDarkSlateGray, new RGBPixel(68,68,68));

    this.pixelOrchid.setAllIntensity();
    assertEquals(this.pixelOrchid, new RGBPixel(181,181,181));
  }

  @Test
  public void setAllLuma() {
    this.pixelRed.setAllLuma();
    assertEquals(this.pixelRed, new RGBPixel(54,54,54));
    this.pixelGreen.setAllLuma();
    assertEquals(this.pixelGreen, new RGBPixel(182,182,182));
    this.pixelBlue.setAllLuma();
    assertEquals(this.pixelBlue, new RGBPixel(18,18,18));
    this.pixelBlack.setAllLuma();
    assertEquals(this.pixelBlack, new RGBPixel(0,0,0));
    this.pixelWhite.setAllLuma();
    assertEquals(this.pixelWhite, new RGBPixel(254,254,254));

    this.pixelYellow.setAllLuma();
    assertEquals(this.pixelYellow, new RGBPixel(236,236,236));

    this.pixelDarkSlateGray.setAllLuma();
    assertEquals(this.pixelDarkSlateGray, new RGBPixel(72,72,72));

    this.pixelOrchid.setAllLuma();
    assertEquals(this.pixelOrchid, new RGBPixel(141,141,141));
  }

  @Test
  public void testToString() {
    assertEquals(this.pixelRed.toString(), "255\n0\n0\n");
    assertEquals(this.pixelGreen.toString(), "0\n255\n0\n");
    assertEquals(this.pixelBlue.toString(), "0\n0\n255\n");
    assertEquals(this.pixelBlack.toString(), "0\n0\n0\n");
    assertEquals(this.pixelWhite.toString(), "255\n255\n255\n");
    assertEquals(this.pixelYellow.toString(), "255\n255\n0\n");
    assertEquals(this.pixelDarkSlateGray.toString(), "47\n79\n79\n");
    assertEquals(this.pixelOrchid.toString(), "218\n112\n214\n");
  }

  @Test
  public void testEquals() {
    assertEquals(this.pixelGreen, this.pixelGreen);
    assertEquals(this.pixelBlue, this.pixelBlue);
    assertEquals(this.pixelRed, this.pixelRed);
    assertEquals(this.pixelWhite, this.pixelWhite);
    assertEquals(this.pixelYellow, this.pixelYellow);
  }

  @Test
  public void testHash() {
    assertEquals(this.pixelYellow.hashCode(), 282751);
    assertEquals(this.pixelWhite.hashCode(), 283006);
    assertEquals(this.pixelRed.hashCode(), 274846);
  }

}