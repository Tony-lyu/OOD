package cs3500.marblesolitaire;

import java.io.InputStreamReader;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

/**
 * Class to run Marble Solitaire game with command line arguments. It parses the command line
 * argument that the user choose to use and make a game of Marble Solitaire according to the
 * command.
 */
public final class MarbleSolitaire {
  /**
   * Main method to parse command line arguments and start the game. 
   * @param args command line arguments and user input
   */
  public static void main(String[] args) {
    // FILL IN HERE
    MarbleSolitaireModel e;
    MarbleSolitaireTextView v;
    TriangleSolitaireTextView tv;
    MarbleSolitaireControllerImpl c = null;
    Readable r = new InputStreamReader(System.in);
    if (args[0].equals("English")) {
      e = new EnglishSolitaireModel();
      v = new MarbleSolitaireTextView(e);
      c = new MarbleSolitaireControllerImpl(e, v, r);
    }
    if (args[0].equals("European")) {
      e = new EuropeanSolitaireModel();
      v = new MarbleSolitaireTextView(e);
      c = new MarbleSolitaireControllerImpl(e, v, r);
    }
    if (args[0].equals("Triangle")) {
      e = new TriangleSolitaireModel();
      tv = new TriangleSolitaireTextView(e);
      c = new MarbleSolitaireControllerImpl(e, tv, r);
    }
    c.playGame();
  }
}
