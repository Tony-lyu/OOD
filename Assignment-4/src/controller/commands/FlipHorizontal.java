package controller.commands;

import model.image.Image;

/**
 * Command for flipping the image horizontally.
 */
public class FlipHorizontal implements ImageCommand {

  @Override
  public void runCommand(Image image) {
    image.flipHorizontal();
  }
}
