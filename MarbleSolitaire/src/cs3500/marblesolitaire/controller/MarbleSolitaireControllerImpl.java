package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.Scanner;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * This is an implementation of Marble Solitaire controller for English Solitaire game. It
 * takes in input from users to allow users to play the English Solitaire game. It analyzes the
 * result of each action users make and transmits the according results.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private MarbleSolitaireModel model;
  private MarbleSolitaireView view;
  private Readable read;
  private int num1 = 0;
  private int num2 = 0;
  private int num3 = 0;
  private int num4 = 0;
  private char c;


  /**
   * Constructor for this controller.
   *
   * @param model the game to be played
   * @param view the view inorder to make the game visible
   * @param read in order to read user input
   * @throws IllegalArgumentException if any parameter is null
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view,
                                       Readable read) throws IllegalArgumentException {
    if (model == null || view == null || read == null) {
      throw new IllegalArgumentException("field of controller cannot be null");
    }
    this.model = model;
    this.view = view;
    this.read = read;
  }

  @Override
  public void playGame() throws IllegalStateException {
    Scanner scan = new Scanner(this.read);
    while (!model.isGameOver()) {
      //System.out.println(view);
      try {
        view.renderBoard();
      }
      catch (IOException e) {
        throw new IllegalStateException("cannot render board!");
      }
      //System.out.println("\nScore: " + model.getScore());
      try {
        view.renderMessage("\nScore: " + model.getScore());
      }
      catch (IOException e) {
        throw new IllegalStateException("cannot render message!");
      }
      while (num1 == 0) {
        if (scan.hasNext()) {
          String s = scan.next();
          int i = handleInput(s);
          if (i == -1) {
            c = 'q';
            scan.close();
            break;
          }
          else if (i == 1) {
            num1 = Integer.parseInt(s);
          }
        }
        else {
          throw new IllegalStateException("out of inputs");
        }
      }
      if (c == 'q') {
        break;
      }
      while (num2 == 0) {
        if (scan.hasNext()) {
          String s = scan.next();
          int i = handleInput(s);
          if (i == -1) {
            scan.close();
            break;
          }
          else if (i == 1) {
            num2 = Integer.parseInt(s);
          }
        }
        else {
          throw new IllegalStateException("out of inputs");
        }
      }
      if (c == 'q') {
        break;
      }
      while (num3 == 0) {
        if (scan.hasNext()) {
          String s = scan.next();
          int i = handleInput(s);
          if (i == -1) {
            scan.close();
            break;
          }
          else if (i == 1) {
            num3 = Integer.parseInt(s);
          }
        }
        else {
          throw new IllegalStateException("out of inputs");
        }
      }
      if (c == 'q') {
        break;
      }
      while (num4 == 0) {
        if (scan.hasNext()) {
          String s = scan.next();
          int i = handleInput(s);
          if (i == -1) {
            scan.close();
            break;
          }
          else if (i == 1) {
            num4 = Integer.parseInt(s);
          }
        }
        else {
          throw new IllegalStateException("out of inputs");
        }
      }
      if (c == 'q') {
        break;
      }
      try {
        this.model.move(num1 - 1, num2 - 1, num3 - 1, num4 - 1);
      }
      catch (IllegalArgumentException e) {
        //System.out.println("Invalid move. Play again.");
        try {
          view.renderMessage("Invalid move. Play again.");
        }
        catch (IOException error) {
          throw new IllegalStateException("cannot render message!");
        }
      }
      num1 = 0;
      num2 = 0;
      num3 = 0;
      num4 = 0;
    }
    if (model.isGameOver()) {
      System.out.println(view);
      try {
        view.renderBoard();
      }
      catch (IOException e) {
        throw new IllegalStateException("cannot render board!");
      }
      //System.out.println("Game quit!");
      //System.out.println("State of game when quit:\n" + view);
      //System.out.println("Score: " + model.getScore());
      try {
        view.renderMessage("\nGame over!\n");
        view.renderBoard();
        view.renderMessage("\nScore: " + model.getScore());
      }
      catch (IOException e) {
        throw new IllegalStateException("cannot render message!");
      }
    }
  }

  /**
   * Helper to determine if the input is an int.
   *
   * @param c char to be analyzed
   * @return if the char represents an int input
   */
  private boolean isNum(char c) {
    for (int i = 0; i < 10; i++) {
      if (i == Character.getNumericValue(c)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Helper method to parse user input and make actions accordingly.
   *
   * @param s user input to be analyzed
   * @return -1 for game quit, 1 for valid input and 0 for invalid input
   */
  private int handleInput(String s) {
    char c = s.charAt(0);
    if (isNum(c)) {
      if (Character.getNumericValue(c) < 1) {
        //System.out.println("invalid input: enter a positive integer!");
        try {
          view.renderMessage("\ninvalid input: enter a positive integer!");
        }
        catch (IOException e) {
          throw new IllegalStateException("cannot render message!");
        }
      }
      return 1;
    }
    if (c == 'q' || c == 'Q') {
      //System.out.println("Game quit!");
      //System.out.println("State of game when quit:\n" + view);
      //System.out.println("Score: " + model.getScore());
      try {
        view.renderMessage("\nGame quit!");
        view.renderMessage("\nState of game when quit:\n" + view);
        view.renderMessage("\nScore: " + model.getScore());
      }
      catch (IOException e) {
        throw new IllegalStateException("cannot render massage!");
      }
      this.c = 'q';
      return -1;
    }
    else {
      //System.out.println("Invalid input, try again!");
      try {
        view.renderMessage("\nInvalid input, try again!");
      }
      catch (IOException e) {
        throw new IllegalStateException("cannot render message!");
      }
    }
    return 0;
  }
}


