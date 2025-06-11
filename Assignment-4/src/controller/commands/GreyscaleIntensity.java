package controller.commands;

import model.image.Image;

/**
 * Command for changing the image to greyscale based on intensity of the image.
 */
public class GreyscaleIntensity implements ImageCommand {

  @Override
  public void runCommand(Image image) {
    image.convertGreyscaleIntensity();
  }
}