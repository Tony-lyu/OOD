import java.io.InputStreamReader;

import controller.processor.ImageProcessorController;
import controller.processor.ImageProcessorControllerImpl;
import view.ImageProcessorViewImpl;

/**
 * Main class for the image processor.
 */
public class Main {
  /**
   * Main method for the image processor.
   * @param args takes in a String array of arguments to run.
   */
  public static void main(String[] args) {
    ImageProcessorController controller =
            new ImageProcessorControllerImpl(new ImageProcessorViewImpl(
                    System.out), new InputStreamReader(System.in));
    controller.runProcessor();
  }
}
