package controller.commands;

import model.image.Image;

/**
 * Command for Darkening an image.
 */
public class Darken implements ImageCommand {
  private final int amount;

  /**
   * Darkens an image by a certain value.
   * @param amount the amount to darken
   */
  public Darken(int amount) {
    this.amount = amount;
  }

  @Override
  public void runCommand(Image image) {
    image.darkenColor(this.amount);
  }
}
