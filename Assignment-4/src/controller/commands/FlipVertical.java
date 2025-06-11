package controller.commands;

import model.image.Image;

/**
 * Command for flipping the image vertically.
 */
public class FlipVertical implements ImageCommand {

  @Override
  public void runCommand(Image image) {
    image.flipVertical();
  }
}
