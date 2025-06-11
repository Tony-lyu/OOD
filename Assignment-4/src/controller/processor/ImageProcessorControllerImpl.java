package controller.processor;

import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import controller.commands.Brighten;
import controller.commands.Darken;
import controller.commands.FlipHorizontal;
import controller.commands.FlipVertical;
import controller.commands.GreyscaleBlue;
import controller.commands.GreyscaleGreen;
import controller.commands.GreyscaleIntensity;
import controller.commands.GreyscaleLuma;
import controller.commands.GreyscaleRed;
import controller.commands.GreyscaleValue;
import controller.commands.ImageCommand;
import controller.commands.Save;
import model.image.Image;
import view.ImageProcessorView;

import static model.image.factory.ImageFactory.createImage;

/**
 * Implementation of the controller for the image processor. It runs the processor and perform
 * action according to user input.
 */
public class ImageProcessorControllerImpl implements ImageProcessorController {
  private final Map<String, Image> images;
  private final ImageProcessorView view;
  private final Readable reader;


  /**
   * Constructor for this controller that takes in a view object and a readable object.
   *
   * @param view   view object for this processor
   * @param reader to take in user input
   */
  public ImageProcessorControllerImpl(ImageProcessorView view, Readable reader)
          throws IllegalArgumentException {
    if (view == null || reader == null) {
      throw new IllegalArgumentException("View or reader cannot be null!");
    }
    this.images = new HashMap<>();
    this.view = view;
    this.reader = reader;
  }

  @Override
  public void runProcessor() throws IllegalStateException {
    Scanner scanner = new Scanner(this.reader);

    // Welcome message for the program.
    String helpMessage = "\nhelp : shows the commands you can do with this program."
            + "\nshow : shows the images currently loaded."
            + "\nload <FILE-PATH> <NAME> :"
            + " loads the image at FILE-PATH and gives NAME to it for reference."
            + "\nhorizontal-flip <NAME> <NEW-NAME> :"
            + " flips the image horizontally with name NAME and loads it as NEW-NAME."
            + "\nvertical-flip <NAME> <NEW-NAME> :"
            + " flips the image vertically with name NAME and loads it as NEW-NAME."
            + "\ndarken <AMOUNT> <NAME> <NEW-NAME> :"
            + " darkens the image referenced by NAME by AMOUNT and loads it as NEW-NAME."
            + "\nbrighten <AMOUNT> <NAME> <NEW-NAME> :"
            + " brightens the image referenced by NAME by AMOUNT and loads it as NEW-NAME."
            + "\ngreyscale-<COMPONENT> <NAME> <NEW-NAME> :"
            + " creates the greyscale using the COMPONENT component referenced by NAME and "
            + "loads it as NEW-NAME."
            + "\nsave <FILE-PATH> <NAME> :"
            + " saves the image called NAME at FILE-PATH."
            + "\nquit : quits the image processor.\n\n";
    try {
      this.view.renderMessage("Welcome to our Image Processor!\n"
              + "Possible actions: " + helpMessage);
    } catch (IOException e) {
      throw new IllegalStateException("Message failed to render.");
    }

    while (scanner.hasNext()) {
      String in = scanner.next();
      ImageCommand cmd = null;
      String name = null;
      String newName = null;
      String path;
      int amount;
      String message = null;

      switch (in) {
        // Shows help for different commands
        case "help":
          this.outputMessage(helpMessage);
          break;
        // Shows all the loaded images
        case "show":
          this.outputMessage("\nLoaded files:\n");
          for (Map.Entry<String, Image> entry : this.images.entrySet()) {
            this.outputMessage(entry.getKey() + "\n");
          }
          break;
        // Loads given image
        case "load":
          path = scanner.next();
          name = scanner.next();
          try {
            this.images.put(name, createImage(path));
            this.outputMessage("Loaded image\n");
          } catch (IllegalArgumentException e) {
            this.outputMessage("Cannot load file\n");
          }
          break;
        // Flips image horizontally
        case "horizontal-flip":
          name = scanner.next();
          newName = scanner.next();
          cmd = new FlipHorizontal();
          message = "Horizontal flip\n";
          break;
        // Flips image vertically
        case "vertical-flip":
          name = scanner.next();
          newName = scanner.next();
          cmd = new FlipVertical();
          message = "Vertical flip\n";
          break;
        // Darkens image by amount
        case "darken":
          //deal with incorrect inputs
          try {
            amount = scanner.nextInt();
          } catch (InputMismatchException e) {
            this.outputMessage("Incorrect Input for AMOUNT.\n");
            break;
          }
          name = scanner.next();
          newName = scanner.next();
          cmd = new Darken(amount);
          message = "Darkened\n";
          break;
        // Brighten image by amount
        case "brighten":
          //deal with incorrect inputs
          try {
            amount = scanner.nextInt();
          } catch (InputMismatchException e) {
            this.outputMessage("Incorrect Input for AMOUNT.\n");
            break;
          }
          name = scanner.next();
          newName = scanner.next();
          cmd = new Brighten(amount);
          message = "Brightened\n";
          break;
        // Makes an image greyscale using red component
        case "greyscale-red":
          //TODO deal with incorrect inputs
          name = scanner.next();
          newName = scanner.next();
          cmd = new GreyscaleRed();
          message = "Red Greyscale\n";
          break;
        case "greyscale-green":
          //TODO deal with incorrect inputs
          name = scanner.next();
          newName = scanner.next();
          cmd = new GreyscaleGreen();
          message = "Green Greyscale\n";
          break;
        case "greyscale-blue":
          //TODO deal with incorrect inputs
          name = scanner.next();
          newName = scanner.next();
          cmd = new GreyscaleBlue();
          message = "Blue Greyscale\n";
          break;
        case "greyscale-value":
          //TODO deal with incorrect inputs
          name = scanner.next();
          newName = scanner.next();
          cmd = new GreyscaleValue();
          message = "Value Greyscale\n";
          break;
        case "greyscale-intensity":
          //TODO deal with incorrect inputs
          name = scanner.next();
          newName = scanner.next();
          cmd = new GreyscaleIntensity();
          message = "Intensity Greyscale\n";
          break;
        case "greyscale-luma":
          //TODO deal with incorrect inputs
          name = scanner.next();
          newName = scanner.next();
          cmd = new GreyscaleLuma();
          message = "Luma Greyscale\n";
          break;
        case "save":
          //TODO
          path = scanner.next();
          name = scanner.next();
          newName = name;
          cmd = new Save(path);
          message = "Saved image\n";
          break;
        // Quits the processor
        case "quit":
          return;
        default:
          break;
      }
      if (cmd != null && name != null && newName != null && this.images.get(name) != null) {
        Image newImage = this.images.get(name);
        try {
          cmd.runCommand(newImage);
          this.images.put(newName, newImage);
          this.outputMessage(message);
        } catch (IllegalArgumentException e) {
          this.outputMessage("Incorrect value for one of the arguments.\n");
        }
      } else if (this.images.get(name) == null && !in.equals("show") && !in.equals("help")) {
        this.outputMessage("Invalid command.\n");
      }
    }
    scanner.close();
  }

  /**
   * Outputs a message to the view.
   *
   * @param message the message to be sent to the view.
   * @throws IllegalStateException if the message fails to render.
   */
  protected void outputMessage(String message) throws IllegalStateException {
    try {
      this.view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException("Message failed to render");
    }
  }
}
