package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * This class is in charge of visually representing a game of marble solitaire as text,
 * specifically a MarbleSolitaireModelState. Specific characters are used to represent each
 * slot state--more information on that in the toString Javadoc.
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {
  private MarbleSolitaireModelState game;
  private Appendable destination;

  /**
   * Text view constructor, takes in only a marble solitaire game.
   *
   * @param game the game to display
   * @throws IllegalArgumentException error when the entered game is null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState game) throws IllegalArgumentException {
    if (game == null) {
      throw new IllegalArgumentException("cannot have a null game!");
    }
    this.game = game;
    this.destination = System.out;
  }

  /**
   * Constructor of this View for controller.
   *
   * @param game the game to be played
   * @param destination to record game play
   * @throws IllegalArgumentException when any parameter is null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState game, Appendable destination)
          throws IllegalArgumentException {
    if (game == null || destination == null) {
      throw new IllegalArgumentException("cannot have a null game!");
    }
    this.game = game;
    this.destination = destination;
  }

  @Override
  public String toString() {
    int arm = (this.game.getBoardSize() + 2) / 3;
    StringBuilder result = new StringBuilder();
    // loop to print rows
    for (int y = 0; y < this.game.getBoardSize(); y++) {
      // loop to print columns
      for (int x = 0; x < this.game.getBoardSize(); x++) {
        // starts a new line for each row
        if (x == 0 && y > 0) {
          result.append("\n");
        }
        int i = arm - 1 - y;
        if (y == 0 && x > 2 * arm - 2) {
          break;
        }
        // printing the last marble at top and bottom part
        if ((y < arm - 1 || y > 2 * arm - 2) && x > i + (y + 1) * arm - 1) {
          result.append(" ").append(printAt(y, x));
          break;
        }
        // printing marbles
        else {
          // first marble of each row
          if (x == 0) {
            result.append(printAt(y, x));
          }
          else {
            if ((game.getSlotAt(y, x) == MarbleSolitaireModelState.SlotState.Invalid)
                    && x < arm) {
              result.append(" ").append(printAt(y, x));
            }
            else if ((game.getSlotAt(y, x) == MarbleSolitaireModelState.SlotState.Invalid)
                    && x > arm) {
              break;
            }
            else {
              result.append(" ").append(printAt(y, x));
            }
          }
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
    if (this.game.getSlotAt(x, y) == MarbleSolitaireModelState.SlotState.Invalid) {
      result = " ";
    }
    else if (this.game.getSlotAt(x, y) == MarbleSolitaireModelState.SlotState.Empty) {
      result = "_";
    }
    else {
      result = "O";
    }
    return result;
  }
}
