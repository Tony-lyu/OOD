package cs3500.marblesolitaire.view;

import java.io.IOException;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * This class is in charge of visually representing a game of triangle marble solitaire as text,
 * specifically a MarbleSolitaireModelState. Specific characters are used to represent each
 * slot state--more information on that in the toString Javadoc.
 */
public class TriangleSolitaireTextView implements MarbleSolitaireView {
  private MarbleSolitaireModelState game;
  private Appendable destination;

  /**
   * Text view constructor, takes in only a marble solitaire game.
   *
   * @param game the game to display
   * @throws IllegalArgumentException error when the entered game is null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState game) throws IllegalArgumentException {
    if (game == null) {
      throw new IllegalArgumentException("cannot have a null game");
    }
    this.game = game;
    this.destination = new StringBuilder();
  }

  /**
   * Constructor of this View for controller.
   *
   * @param game the game to be played
   * @param destination to record game play
   * @throws IllegalArgumentException when any parameter is null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState game, Appendable destination)
          throws IllegalArgumentException {
    if (game == null || destination == null) {
      throw new IllegalArgumentException("cannot have a null game or a null destination");
    }
    this.game = game;
    this.destination = destination;
  }

  @Override
  public String toString() {
    int arm = this.game.getBoardSize();
    StringBuilder result = new StringBuilder();
    for (int x = 0; x < arm; x ++) {
      for (int y = 0; y < x + 1; y ++) {
        if (y == 0) {
          if (x != 0) {
            result.append("\n");
          }
          int space = (arm - 1 - x);
          for (int i = 0; i < space; i++) {
            result.append(" ");
          }
          result.append(printAt(x, y));
        }
        else {
          result.append(" ").append(printAt(x, y));
        }
      }
    }
    return result.toString();
  }

  @Override
  public void renderBoard() throws IOException {
    this.destination.append(this.toString());
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.destination.append(message);
  }

  /**
   * To print the String representation of the marble on the given location.
   *
   * @param x x coordinate of the marble
   * @param y y coordinate of the marble
   * @return String representation of the given marble
   */
  private String printAt(int x, int y) {
    String result;
    if (this.game.getSlotAt(x, y) == MarbleSolitaireModelState.SlotState.Empty) {
      result = "_";
    }
    else {
      result = "O";
    }
    return result;
  }

}
