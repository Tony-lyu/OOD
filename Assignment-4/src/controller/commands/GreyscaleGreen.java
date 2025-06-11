package controller.commands;

import model.image.Image;

/**
 * Command for changing the image to greyscale based on green value of each pixel in the image.
 */
public class GreyscaleGreen implements ImageCommand {

  @Override
  public void runCommand(Image image) {
    image.convertGreyscaleGreen();
  }
}