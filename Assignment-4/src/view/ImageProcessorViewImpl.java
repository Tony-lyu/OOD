package view;

import java.io.IOException;

/**
 * Implementation of the image processor view. It records messages output by the program.
 */
public class ImageProcessorViewImpl implements ImageProcessorView {
  private final Appendable destination;

  /**
   * Constructor for this view implementation, it takes in a destination to record actions made.
   * @param destination to store messages.
   */
  public ImageProcessorViewImpl(Appendable destination) throws IllegalArgumentException {
    if (destination == null) {
      throw new IllegalArgumentException("destination cannot be null!");
    }
    this.destination = destination;
  }

  /**
   * Constructor for this view implementation where the destination is System.out.
   */
  public ImageProcessorViewImpl() {
    this(System.out);
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.destination.append(message);
  }
}
