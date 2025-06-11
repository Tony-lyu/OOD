package controller.commands;

import model.image.Image;

/**
 * Command for saving the image.
 */
public class Save implements ImageCommand {
  private final String filepath;

  /**
   * Save this image to a given filepath.
   * @param filepath file path to save to
   */
  public Save(String filepath) {
    this.filepath = filepath;
  }

  @Override
  public void runCommand(Image image) {
    image.toFile(this.filepath);
  }
}
