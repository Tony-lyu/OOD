package controller.commands;

import model.image.Image;

/**
 * Command for changing the image to greyscale based on red value of each pixel in the image.
 */
public class GreyscaleRed implements ImageCommand {

  @Override
  public void runCommand(Image image) {
    image.convertGreyscaleRed();
  }
}
