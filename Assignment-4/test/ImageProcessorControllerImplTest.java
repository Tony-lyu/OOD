import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.InputMismatchException;

import controller.processor.ImageProcessorController;
import controller.processor.ImageProcessorControllerImpl;
import model.image.Image;
import view.ImageProcessorView;
import view.ImageProcessorViewImpl;

import static model.image.factory.ImageFactory.createImage;
import static org.junit.Assert.assertFalse;

/**
 * Test for image processor controller.
 */
public class ImageProcessorControllerImplTest {
  private final Appendable p = new StringBuilder();
  private final InputStream in = new ByteArrayInputStream("quit".getBytes());
  private final Readable r = new InputStreamReader(in);
  private final ImageProcessorView v = new ImageProcessorViewImpl(p);
  private final ImageProcessorController c = new ImageProcessorControllerImpl(v, r);
  private final String helpText = "\nhelp : shows the commands you can do with this program."
          + "\nshow : shows the images currently loaded."
          + "\nload <FILE-PATH> <NAME> :"
          + " loads the image at FILE-PATH and gives NAME to it for reference."
          + "\nhorizontal-flip <NAME> <NEW-NAME> :"
          + " flips the image horizontally with name NAME and loads it as NEW-NAME."
          + "\nvertical-flip <NAME> <NEW-NAME> :"
          + " flips the image vertically with name NAME and loads it as NEW-NAME."
          + "\ndarken <AMOUNT> <NAME> <NEW-NAME> :"
          + " darkens the image referenced by NAME by AMOUNT and loads it as NEW-NAME."
          + "\nbrighten <AMOUNT> <NAME> <NEW-NAME> :"
          + " brightens the image referenced by NAME by AMOUNT and loads it as NEW-NAME."
          + "\ngreyscale-<COMPONENT> <NAME> <NEW-NAME> :"
          + " creates the greyscale using the COMPONENT component referenced by NAME and "
          + "loads it as NEW-NAME."
          + "\nsave <FILE-PATH> <NAME> :"
          + " saves the image called NAME at FILE-PATH."
          + "\nquit : quits the image processor.\n\n";
  private final String welcomeMessage = "Welcome to our Image Processor!\n"
          + "Possible actions: " + helpText;


  /**
   * Setting up images to be tested for.
   */
  @BeforeClass
  public static void setup() {
    String koalaPath = "res/Koala.ppm";
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
  }

  /**
   * clearing unwanted images after being tested.
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
    File[] files = {koalaHorizontal, koalaVertical, koalaBrighten, koalaDarkened, koalaRed,
        koalaGreen, koalaBlue, koalaBlue, koalaValue, koalaIntensity, koalaLuma};
    for (File file : files) {
      if (file.delete()) {
        boolean success = file.exists();
        assertFalse(success);
      }
    }
  }

  /**
   * Test for valid constructor.
   */
  @Test
  public void constructor() {
    c.runProcessor();
    Assert.assertEquals(p.toString(), welcomeMessage);
  }

  /**
   * Test for invalid constructors.
   */
  @Test(expected = IllegalArgumentException.class)
  public void invalidConstructor() {
    try {
      new ImageProcessorControllerImpl(null, r);
    } catch (IllegalArgumentException e) {
      Assert.assertEquals(e.getMessage(), "View or reader cannot be null!");
    }
    try {
      new ImageProcessorControllerImpl(null, null);
    } catch (IllegalArgumentException e) {
      Assert.assertEquals(e.getMessage(), "View or reader cannot be null!");
    }
    new ImageProcessorControllerImpl(v, null);
  }

  @Test
  public void defaultQuit() {
    c.runProcessor();
    Assert.assertEquals(p.toString(), welcomeMessage);
  }

  /**
   * Test for calling help.
   */
  @Test
  public void testHelp() {
    Appendable p = new StringBuilder();
    InputStream in = new ByteArrayInputStream("help quit".getBytes());
    Readable r = new InputStreamReader(in);
    ImageProcessorView v = new ImageProcessorViewImpl(p);
    ImageProcessorController c = new ImageProcessorControllerImpl(v, r);

    c.runProcessor();
    Assert.assertEquals(p.toString(), welcomeMessage
            + helpText);
  }

  /**
   * Test for default show.
   */
  @Test
  public void defaultShow() {
    Appendable p = new StringBuilder();
    InputStream in = new ByteArrayInputStream("show quit".getBytes());
    Readable r = new InputStreamReader(in);
    ImageProcessorView v = new ImageProcessorViewImpl(p);
    ImageProcessorController c = new ImageProcessorControllerImpl(v, r);

    c.runProcessor();
    Assert.assertEquals(p.toString(), welcomeMessage
            + "\n"
            + "Loaded files:\n");
  }

  /**
   * Test for invalid loading with two wrong inputs.
   */
  @Test
  public void invalidBothInputLoad() {
    Appendable p = new StringBuilder();
    InputStream in = new ByteArrayInputStream("load wrong info quit".getBytes());
    Readable r = new InputStreamReader(in);
    ImageProcessorView v = new ImageProcessorViewImpl(p);
    ImageProcessorController c = new ImageProcessorControllerImpl(v, r);

    c.runProcessor();
    Assert.assertEquals(p.toString(), welcomeMessage
            + "Cannot load file\n" +
            "Invalid command.\n");
  }

  /**
   * Test for invalid loading with wrong input where filepath cannot be found.
   */
  @Test
  public void invalidFirstInputLoad() {
    Appendable p = new StringBuilder();
    InputStream in = new ByteArrayInputStream("load wrong name quit".getBytes());
    Readable r = new InputStreamReader(in);
    ImageProcessorView v = new ImageProcessorViewImpl(p);
    ImageProcessorController c = new ImageProcessorControllerImpl(v, r);

    c.runProcessor();
    Assert.assertEquals(p.toString(), welcomeMessage
            + "Cannot load file\n" +
            "Invalid command.\n");
  }

  /**
   * Test for invalid loading with one input.
   */
  @Test
  public void NotEnoughInputLoad() {
    Appendable p = new StringBuilder();
    InputStream in = new ByteArrayInputStream("load wrong quit".getBytes());
    Readable r = new InputStreamReader(in);
    ImageProcessorView v = new ImageProcessorViewImpl(p);
    ImageProcessorController c = new ImageProcessorControllerImpl(v, r);

    c.runProcessor();
    Assert.assertEquals(p.toString(), welcomeMessage
            + "Cannot load file\n" +
            "Invalid command.\n");
  }

  /**
   * Test for invalid command.
   */
  @Test
  public void testInvalidCommand() {
    Appendable p = new StringBuilder();
    InputStream in = new ByteArrayInputStream("hello".getBytes());
    Readable r = new InputStreamReader(in);
    ImageProcessorView v = new ImageProcessorViewImpl(p);
    ImageProcessorController c = new ImageProcessorControllerImpl(v, r);

    c.runProcessor();
    Assert.assertEquals(p.toString(), welcomeMessage
            + "Invalid command.\n");
  }


  /**
   * Test for invalid loading with no input.
   */
  @Test
  public void NoInputLoad() {
    Appendable p = new StringBuilder();
    InputStream in = new ByteArrayInputStream("load quit quit".getBytes());
    Readable r = new InputStreamReader(in);
    ImageProcessorView v = new ImageProcessorViewImpl(p);
    ImageProcessorController c = new ImageProcessorControllerImpl(v, r);

    c.runProcessor();
    Assert.assertEquals(p.toString(), welcomeMessage + "Cannot load file\n" +
            "Invalid command.\n");
  }

  /**
   * Test for valid loading.
   */
  @Test
  public void ValidLoad() {
    Appendable p = new StringBuilder();
    InputStream in = new ByteArrayInputStream("load res/Koala.ppm Koala quit".getBytes());
    Readable r = new InputStreamReader(in);
    ImageProcessorView v = new ImageProcessorViewImpl(p);
    ImageProcessorController c = new ImageProcessorControllerImpl(v, r);

    c.runProcessor();
    Assert.assertEquals(p.toString(), welcomeMessage
            + "Loaded image\n");
  }


  /**
   * Test for show after a successful load.
   */
  @Test
  public void showAfterLoad() {
    Appendable p = new StringBuilder();
    InputStream in = new ByteArrayInputStream("load res/Koala.ppm Koala show quit".getBytes());
    Readable r = new InputStreamReader(in);
    ImageProcessorView v = new ImageProcessorViewImpl(p);
    ImageProcessorController c = new ImageProcessorControllerImpl(v, r);

    c.runProcessor();
    Assert.assertEquals(p.toString(), welcomeMessage
            + "Loaded image\n"
            + "\n"
            + "Loaded files:\n"
            + "Koala\n");
  }

  /**
   * Test for horizontal flip.
   */
  @Test
  public void validHorizontalFlip() {
    Appendable p = new StringBuilder();
    InputStream in = new ByteArrayInputStream(("load res/Koala.ppm Koala "
            + "horizontal-flip Koala k quit").getBytes());
    Readable r = new InputStreamReader(in);
    ImageProcessorView v = new ImageProcessorViewImpl(p);
    ImageProcessorController c = new ImageProcessorControllerImpl(v, r);

    c.runProcessor();
    Assert.assertEquals(p.toString(), welcomeMessage
            + "Loaded image\n"
            + "Horizontal flip\n");
  }

  /**
   * Test for invalid horizontal flip.
   */
  @Test
  public void invalidHorizontalFlip() {
    Appendable p = new StringBuilder();
    InputStream in = new ByteArrayInputStream(("load res/Koala.ppm Koala "
            + "horizontal-flip quit quit").getBytes());
    Readable r = new InputStreamReader(in);
    ImageProcessorView v = new ImageProcessorViewImpl(p);
    ImageProcessorController c = new ImageProcessorControllerImpl(v, r);

    c.runProcessor();
    Assert.assertEquals(p.toString(), welcomeMessage
            + "Loaded image\n" +
            "Invalid command.\n");
  }

  /**
   * Test for vertical flip.
   */
  @Test
  public void validVerticalFlip() {
    Appendable p = new StringBuilder();
    InputStream in = new ByteArrayInputStream(("load res/Koala.ppm Koala "
            + "vertical-flip Koala k quit").getBytes());
    Readable r = new InputStreamReader(in);
    ImageProcessorView v = new ImageProcessorViewImpl(p);
    ImageProcessorController c = new ImageProcessorControllerImpl(v, r);

    c.runProcessor();
    Assert.assertEquals(p.toString(), welcomeMessage
            + "Loaded image\n"
            + "Vertical flip\n");
  }

  /**
   * Test for invalid vertical flip.
   */
  @Test
  public void invalidVerticalFlip() {
    Appendable p = new StringBuilder();
    InputStream in = new ByteArrayInputStream(("load res/Koala.ppm Koala "
            + "vertical-flip quit quit").getBytes());
    Readable r = new InputStreamReader(in);
    ImageProcessorView v = new ImageProcessorViewImpl(p);
    ImageProcessorController c = new ImageProcessorControllerImpl(v, r);

    c.runProcessor();
    Assert.assertEquals(p.toString(), welcomeMessage
            + "Loaded image\n" +
            "Invalid command.\n");
  }

  /**
   * Test for darken.
   */
  @Test
  public void validDarken() {
    Appendable p = new StringBuilder();
    InputStream in = new ByteArrayInputStream(("load res/Koala.ppm Koala "
            + "darken 50 Koala k quit").getBytes());
    Readable r = new InputStreamReader(in);
    ImageProcessorView v = new ImageProcessorViewImpl(p);
    ImageProcessorController c = new ImageProcessorControllerImpl(v, r);

    c.runProcessor();
    Assert.assertEquals(p.toString(), welcomeMessage
            + "Loaded image\n"
            + "Darkened\n");
  }

  /**
   * Test for invalid darken with wrong integer value.
   */
  @Test
  public void invalidDarkenWrongIntegerValue() {
    Appendable p = new StringBuilder();
    InputStream in = new ByteArrayInputStream(("load res/Koala.ppm Koala "
            + "darken -2 Koala k quit").getBytes());
    Readable r = new InputStreamReader(in);
    ImageProcessorView v = new ImageProcessorViewImpl(p);
    ImageProcessorController c = new ImageProcessorControllerImpl(v, r);
    try {
      c.runProcessor();
    } catch (IllegalArgumentException e) {
      Assert.assertEquals(e.getMessage(), "Incorrect Input for AMOUNT.\n");
    }
  }

  /**
   * Test for invalid darken with wrong value.
   */
  @Test
  public void invalidDarkenWrongValue() {
    Appendable p = new StringBuilder();
    InputStream in = new ByteArrayInputStream(("load res/Koala.ppm Koala "
            + "darken fifty Koala k quit").getBytes());
    Readable r = new InputStreamReader(in);
    ImageProcessorView v = new ImageProcessorViewImpl(p);
    ImageProcessorController c = new ImageProcessorControllerImpl(v, r);
    try {
      c.runProcessor();
    } catch (InputMismatchException e) {
      Assert.assertEquals(e.getMessage(), "Incorrect Input for AMOUNT.\n");
    }
  }

  /**
   * Test for invalid darken without enough inputs.
   */
  @Test
  public void invalidDarkenNotEnoughInput() {
    Appendable p = new StringBuilder();
    InputStream in = new ByteArrayInputStream(("load res/Koala.ppm Koala "
            + "darken 50 quit quit").getBytes());
    Readable r = new InputStreamReader(in);
    ImageProcessorView v = new ImageProcessorViewImpl(p);
    ImageProcessorController c = new ImageProcessorControllerImpl(v, r);
    c.runProcessor();
    Assert.assertEquals(p.toString(), welcomeMessage
            + "Loaded image\n" +
            "Invalid command.\n");
  }

  /**
   * Test for brighten.
   */
  @Test
  public void validBrighten() {
    Appendable p = new StringBuilder();
    InputStream in = new ByteArrayInputStream(("load res/Koala.ppm Koala "
            + "brighten 50 Koala k quit").getBytes());
    Readable r = new InputStreamReader(in);
    ImageProcessorView v = new ImageProcessorViewImpl(p);
    ImageProcessorController c = new ImageProcessorControllerImpl(v, r);

    c.runProcessor();
    Assert.assertEquals(p.toString(), welcomeMessage
            + "Loaded image\n"
            + "Brightened\n");
  }

  /**
   * Test for invalid brighten with wrong value.
   */
  @Test
  public void invalidBrightenWrongValue() {
    Appendable p = new StringBuilder();
    InputStream in = new ByteArrayInputStream(("load res/Koala.ppm Koala "
            + "brighten fifty Koala k quit").getBytes());
    Readable r = new InputStreamReader(in);
    ImageProcessorView v = new ImageProcessorViewImpl(p);
    ImageProcessorController c = new ImageProcessorControllerImpl(v, r);
    try {
      c.runProcessor();
    } catch (InputMismatchException e) {
      Assert.assertEquals(e.getMessage(), "Incorrect Input for AMOUNT.\n");
    }
  }

  /**
   * Test for invalid brighten with wrong integer value.
   */
  @Test
  public void invalidBrightenWrongIntegerValue() {
    Appendable p = new StringBuilder();
    InputStream in = new ByteArrayInputStream(("load res/Koala.ppm Koala "
            + "brighten -2 Koala k quit").getBytes());
    Readable r = new InputStreamReader(in);
    ImageProcessorView v = new ImageProcessorViewImpl(p);
    ImageProcessorController c = new ImageProcessorControllerImpl(v, r);
    try {
      c.runProcessor();
    } catch (IllegalArgumentException e) {
      Assert.assertEquals(e.getMessage(), "Incorrect Input for AMOUNT.\n");
    }
  }

  /**
   * Test for invalid brighten without enough inputs.
   */
  @Test
  public void invalidBrightenNotEnoughInput() {
    Appendable p = new StringBuilder();
    InputStream in = new ByteArrayInputStream(("load res/Koala.ppm Koala "
            + "brighten 50 quit quit").getBytes());
    Readable r = new InputStreamReader(in);
    ImageProcessorView v = new ImageProcessorViewImpl(p);
    ImageProcessorController c = new ImageProcessorControllerImpl(v, r);
    c.runProcessor();
    Assert.assertEquals(p.toString(), welcomeMessage
            + "Loaded image\n" +
            "Invalid command.\n");
  }

  /**
   * Test for valid greyscale-red.
   */
  @Test
  public void validGreyScaleRed() {
    Appendable p = new StringBuilder();
    InputStream in = new ByteArrayInputStream(("load res/Koala.ppm Koala "
            + "greyscale-red Koala grey-Koala quit").getBytes());
    Readable r = new InputStreamReader(in);
    ImageProcessorView v = new ImageProcessorViewImpl(p);
    ImageProcessorController c = new ImageProcessorControllerImpl(v, r);
    c.runProcessor();
    Assert.assertEquals(p.toString(), welcomeMessage
            + "Loaded image\n"
            + "Red Greyscale\n");
  }

  /**
   * Test for invalid greyscale-red without enough input.
   */
  @Test
  public void invalidGreyScaleRed() {
    Appendable p = new StringBuilder();
    InputStream in = new ByteArrayInputStream(("load res/Koala.ppm Koala "
            + "greyscale-red quit quit").getBytes());
    Readable r = new InputStreamReader(in);
    ImageProcessorView v = new ImageProcessorViewImpl(p);
    ImageProcessorController c = new ImageProcessorControllerImpl(v, r);
    c.runProcessor();
    Assert.assertEquals(p.toString(), welcomeMessage
            + "Loaded image\n" +
            "Invalid command.\n");
  }

  /**
   * Test for invalid greyscale-green without enough input.
   */
  @Test
  public void invalidGreyScaleGreen() {
    Appendable p = new StringBuilder();
    InputStream in = new ByteArrayInputStream(("load res/Koala.ppm Koala "
            + "greyscale-green quit quit").getBytes());
    Readable r = new InputStreamReader(in);
    ImageProcessorView v = new ImageProcessorViewImpl(p);
    ImageProcessorController c = new ImageProcessorControllerImpl(v, r);
    c.runProcessor();
    Assert.assertEquals(p.toString(), welcomeMessage
            + "Loaded image\n" +
            "Invalid command.\n");
  }

  /**
   * Test for valid greyscale-green.
   */
  @Test
  public void validGreyScaleGreen() {
    Appendable p = new StringBuilder();
    InputStream in = new ByteArrayInputStream(("load res/Koala.ppm Koala "
            + "greyscale-green Koala grey-Koala quit").getBytes());
    Readable r = new InputStreamReader(in);
    ImageProcessorView v = new ImageProcessorViewImpl(p);
    ImageProcessorController c = new ImageProcessorControllerImpl(v, r);
    c.runProcessor();
    Assert.assertEquals(p.toString(), welcomeMessage
            + "Loaded image\n"
            + "Green Greyscale\n");
  }

  /**
   * Test for invalid greyscale-blue without enough input.
   */
  @Test
  public void invalidGreyScaleBlue() {
    Appendable p = new StringBuilder();
    InputStream in = new ByteArrayInputStream(("load res/Koala.ppm Koala "
            + "greyscale-blue quit quit").getBytes());
    Readable r = new InputStreamReader(in);
    ImageProcessorView v = new ImageProcessorViewImpl(p);
    ImageProcessorController c = new ImageProcessorControllerImpl(v, r);
    c.runProcessor();
    Assert.assertEquals(p.toString(), welcomeMessage
            + "Loaded image\n" +
            "Invalid command.\n");
  }

  /**
   * Test for valid greyscale-blue.
   */
  @Test
  public void validGreyScaleBlue() {
    Appendable p = new StringBuilder();
    InputStream in = new ByteArrayInputStream(("load res/Koala.ppm Koala "
            + "greyscale-blue Koala grey-Koala quit").getBytes());
    Readable r = new InputStreamReader(in);
    ImageProcessorView v = new ImageProcessorViewImpl(p);
    ImageProcessorController c = new ImageProcessorControllerImpl(v, r);
    c.runProcessor();
    Assert.assertEquals(p.toString(), welcomeMessage
            + "Loaded image\n"
            + "Blue Greyscale\n");
  }

  /**
   * Test for valid greyscale-value.
   */
  @Test
  public void validGreyScaleValue() {
    Appendable p = new StringBuilder();
    InputStream in = new ByteArrayInputStream(("load res/Koala.ppm Koala "
            + "greyscale-value Koala grey-Koala quit").getBytes());
    Readable r = new InputStreamReader(in);
    ImageProcessorView v = new ImageProcessorViewImpl(p);
    ImageProcessorController c = new ImageProcessorControllerImpl(v, r);
    c.runProcessor();
    Assert.assertEquals(p.toString(), welcomeMessage
            + "Loaded image\n"
            + "Value Greyscale\n");
  }

  /**
   * Test for invalid greyscale-value without enough input.
   */
  @Test
  public void invalidGreyScaleValue() {
    Appendable p = new StringBuilder();
    InputStream in = new ByteArrayInputStream(("load res/Koala.ppm Koala "
            + "greyscale-value quit quit").getBytes());
    Readable r = new InputStreamReader(in);
    ImageProcessorView v = new ImageProcessorViewImpl(p);
    ImageProcessorController c = new ImageProcessorControllerImpl(v, r);
    c.runProcessor();
    Assert.assertEquals(p.toString(), welcomeMessage
            + "Loaded image\n" +
            "Invalid command.\n");
  }

  /**
   * Test for valid greyscale-intensity.
   */
  @Test
  public void validGreyScaleIntensity() {
    Appendable p = new StringBuilder();
    InputStream in = new ByteArrayInputStream(("load res/Koala.ppm Koala "
            + "greyscale-intensity Koala grey-Koala quit").getBytes());
    Readable r = new InputStreamReader(in);
    ImageProcessorView v = new ImageProcessorViewImpl(p);
    ImageProcessorController c = new ImageProcessorControllerImpl(v, r);
    c.runProcessor();
    Assert.assertEquals(p.toString(), welcomeMessage
            + "Loaded image\n"
            + "Intensity Greyscale\n");
  }

  /**
   * Test for invalid greyscale-intensity without enough input.
   */
  @Test
  public void invalidGreyScaleIntensity() {
    Appendable p = new StringBuilder();
    InputStream in = new ByteArrayInputStream(("load res/Koala.ppm Koala "
            + "greyscale-intensity quit quit").getBytes());
    Readable r = new InputStreamReader(in);
    ImageProcessorView v = new ImageProcessorViewImpl(p);
    ImageProcessorController c = new ImageProcessorControllerImpl(v, r);
    c.runProcessor();
    Assert.assertEquals(p.toString(), welcomeMessage
            + "Loaded image\n" +
            "Invalid command.\n");
  }

  /**
   * Test for valid greyscale-luma.
   */
  @Test
  public void validGreyScaleLuma() {
    Appendable p = new StringBuilder();
    InputStream in = new ByteArrayInputStream(("load res/Koala.ppm Koala "
            + "greyscale-luma Koala grey-Koala quit").getBytes());
    Readable r = new InputStreamReader(in);
    ImageProcessorView v = new ImageProcessorViewImpl(p);
    ImageProcessorController c = new ImageProcessorControllerImpl(v, r);
    c.runProcessor();
    Assert.assertEquals(p.toString(), welcomeMessage
            + "Loaded image\n"
            + "Luma Greyscale\n");
  }

  /**
   * Test for invalid greyscale-luma without enough input.
   */
  @Test
  public void invalidGreyScaleLuma() {
    Appendable p = new StringBuilder();
    InputStream in = new ByteArrayInputStream(("load res/Koala.ppm Koala "
            + "greyscale-luma quit quit").getBytes());
    Readable r = new InputStreamReader(in);
    ImageProcessorView v = new ImageProcessorViewImpl(p);
    ImageProcessorController c = new ImageProcessorControllerImpl(v, r);
    c.runProcessor();
    Assert.assertEquals(p.toString(), welcomeMessage
            + "Loaded image\n" +
            "Invalid command.\n");
  }

  /**
   * Test for valid saving.
   */
  @Test
  public void validSave() {
    Appendable p = new StringBuilder();
    InputStream in = new ByteArrayInputStream(("load res/Koala.ppm Koala "
            + "save res/Koala.ppm Koala quit").getBytes());
    Readable r = new InputStreamReader(in);
    ImageProcessorView v = new ImageProcessorViewImpl(p);
    ImageProcessorController c = new ImageProcessorControllerImpl(v, r);
    c.runProcessor();
    Assert.assertEquals(p.toString(), welcomeMessage
            + "Loaded image\n"
            + "Saved image\n");
  }

  /**
   * Test for invalid saving without enough input.
   */
  @Test
  public void invalidSaveNotEnoughInput() {
    Appendable p = new StringBuilder();
    InputStream in = new ByteArrayInputStream(("load res/Koala.ppm Koala "
            + "save quit quit").getBytes());
    Readable r = new InputStreamReader(in);
    ImageProcessorView v = new ImageProcessorViewImpl(p);
    ImageProcessorController c = new ImageProcessorControllerImpl(v, r);
    c.runProcessor();
    Assert.assertEquals(p.toString(), welcomeMessage
            + "Loaded image\n" +
            "Invalid command.\n");
  }

  @Test (expected = IllegalStateException.class)
  public void testOutputFailToSend() {
    Appendable p = new MockAppendable();
    InputStream in = new ByteArrayInputStream("help quit".getBytes());
    Readable r = new InputStreamReader(in);
    ImageProcessorView v = new ImageProcessorViewImpl(p);
    ImageProcessorController c = new ImageProcessorControllerImpl(v, r);
    c.runProcessor();
  }
}
