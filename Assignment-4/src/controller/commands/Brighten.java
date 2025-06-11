package controller.commands;

import model.image.Image;

/**
 * Command for Brightening an image.
 */
public class Brighten implements ImageCommand {
  private final int amount;

  /**
   * Brightens an image by a certain value.
   * @param amount the amount to brighten.
   */
  public Brighten(int amount) {
    this.amount = amount;
  }

  @Override
  public void runCommand(Image image) {
    image.brightenColor(this.amount);
  }
}
