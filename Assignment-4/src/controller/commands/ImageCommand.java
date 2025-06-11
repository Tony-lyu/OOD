package controller.commands;

import model.image.Image;

/**
 * Commands that can be done to an image by an image processor.
 */
public interface ImageCommand {

  /**
   * runs the command on the given image.
   * @param image the image to perform commands on.
   */
  void runCommand(Image image);
}
