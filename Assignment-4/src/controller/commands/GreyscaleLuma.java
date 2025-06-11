package controller.commands;

import model.image.Image;

/**
 * Command for changing the image to greyscale based on luma of the image.
 */
public class GreyscaleLuma implements ImageCommand {

  @Override
  public void runCommand(Image image) {
    image.convertGreyscaleLuma();
  }
}