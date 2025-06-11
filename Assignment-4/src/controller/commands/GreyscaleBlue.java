package controller.commands;

import model.image.Image;

/**
 * Command for changing the image to greyscale based on blue value of each pixel in the image.
 */
public class GreyscaleBlue implements ImageCommand {

  @Override
  public void runCommand(Image image) {
    image.convertGreyscaleBlue();
  }
}