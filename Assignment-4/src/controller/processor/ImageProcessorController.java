package controller.processor;

/**
 * Controller for the image processor. It runs the processor and perform action according to user
 * input.
 */
public interface ImageProcessorController {

  /**
   * Starts and runs the Image Processor.
   * @throws IllegalStateException when view does not render correctly.
   */
  void runProcessor() throws IllegalStateException;
}
