package view;

import java.io.IOException;

/**
 * View of this Image processor. It displays the image once action is made.
 */
public interface ImageProcessorView {

  /**
   * Renders the message output and record it.
   * @param message message to render
   * @throws IOException when render fails
   */
  void renderMessage(String message) throws IOException;
}
