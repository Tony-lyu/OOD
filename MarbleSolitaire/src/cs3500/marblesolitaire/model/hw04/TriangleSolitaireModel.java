package cs3500.marblesolitaire.model.hw04;


import java.util.ArrayList;
import java.util.List;
import cs3500.marblesolitaire.model.hw02.Marble;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * This class is a representation of the game Triangle Solitaire, a variation of marble solitaire.
 * It creates a triangular board of marbles with an odd arm thickness 5 or greater and a single
 * empty slot to begin. It allows the user to get information about the current
 * game state (slots, game status, score, width), as well as to move pieces.
 */
public class TriangleSolitaireModel implements MarbleSolitaireModel {
  private int arm;
  private List<Marble> marbles;

  /**
   * Default constructor that creates a game board where the arm is 5 and the empty
   * cell is at the top of the game board.
   */
  public TriangleSolitaireModel() {
    this.arm = 5;
    this.marbles = makeMarbles();
    marbles.get(0).emptyMarble();
  }

  /**
   * Constructor that takes in an arm and create the game board representation where the
   * empty cell is at the top of game board.
   *
   * @param arm positive odd integer representing arm thickness of board
   */
  public TriangleSolitaireModel(int arm) throws IllegalArgumentException {
    if (arm < 1) {
      throw new IllegalArgumentException("arm must be positive");
    }
    this.arm = arm;
    this.marbles = makeMarbles();
    marbles.get(0).emptyMarble();
  }

  /**
   * Constructor that takes in x and y value of empty cell and create tha gam board representation
   * where the arm is 5.
   *
   * @param x x position of empty cell
   * @param y y position of empty cell
   */
  public TriangleSolitaireModel(int x, int y) throws IllegalArgumentException {
    this.arm = 5;
    this.marbles = makeMarbles();
    if (!validPosition(x, y)) {
      throw new IllegalArgumentException("invalid empty slot!");
    }
    marbles.get(getIndex(x, y)).emptyMarble();
  }

  /**
   * Constructor that takes in an arm, x and y value of center cell to create the game
   * board representation.
   *
   * @param arm positive odd integer representing arm thickness of board.
   * @param x   x position of empty cell
   * @param y   y position of empty cell
   */
  public TriangleSolitaireModel(int arm, int x, int y) throws IllegalArgumentException {
    if (arm < 1) {
      throw new IllegalArgumentException("arm must be positive!");
    }
    this.arm = arm;
    this.marbles = makeMarbles();
    if (!validPosition(x, y)) {
      throw new IllegalArgumentException("invalid empty slot!");
    }
    marbles.get(getIndex(x, y)).emptyMarble();
  }

  private List<Marble> makeMarbles() {
    List<Marble> marbles = new ArrayList<>();
    for (int x = 0; x < this.arm; x ++) {
      for (int y = 0; y < x + 1; y ++) {
        marbles.add(new Marble(x, y));
      }
    }
    return marbles;
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    // coordinates cannot be negative
    if (fromRow < 0 || fromCol < 0 || toRow < 0 || toCol < 0) {
      throw new IllegalArgumentException("invalid move!");
    }
    // cannot move to same position
    if (fromRow == toRow && fromCol == toCol) {
      throw new IllegalArgumentException("cannot move to the same position!");
    }
    // coordinates cannot be larger than arm
    if (fromRow > this.arm || fromCol > this.arm || toRow > this.arm || toCol > this.arm) {
      throw new IllegalArgumentException("coordinates cannot be larger than arm of this game!");
    }
    // cannot move to a non-empty spot
    if (getSlotAt(toRow, toCol) != SlotState.Empty) {
      throw new IllegalArgumentException("to position has to be empty!");
    }
    // moving left
    if (fromCol > toCol || (fromCol == toCol && fromRow < toRow)) {
      moveLeft(fromRow, fromCol, toRow, toCol);
    }
    // moving right
    else {
      moveRight(fromRow, fromCol, toRow, toCol);
    }
  }

  private void moveLeft(int fromRow, int fromCol, int toRow, int toCol) {
    // horizontal move
    if (fromRow == toRow) {
      if (getSlotAt(fromRow, fromCol - 1) != SlotState.Marble) {
        throw new IllegalArgumentException("cannot move over a empty marble!");
      }
      if (fromCol -  toCol != 2) {
        throw new IllegalArgumentException("cannot make moves longer than two units");
      }
      else {
        marbles.get(getIndex(fromRow, fromCol)).emptyMarble();
        marbles.get(getIndex(fromRow, fromCol - 1)).emptyMarble();
        marbles.get(getIndex(toRow, toCol)).addMarble();
      }
    }
    // moving up left
    if (fromRow > toRow) {
      if (fromRow - toRow != 2 || fromCol - toCol != 2) {
        throw new IllegalArgumentException("cannot make moves longer than two units");
      }
      if (getSlotAt(fromRow - 1, fromCol - 1) != SlotState.Marble) {
        throw new IllegalArgumentException("cannot move over a empty marble!");
      }
      else {
        marbles.get(getIndex(fromRow, fromCol)).emptyMarble();
        marbles.get(getIndex(fromRow - 1, fromCol - 1)).emptyMarble();
        marbles.get(getIndex(toRow, toCol)).addMarble();
      }
    }
    // moving down left
    if (fromRow < toRow) {
      if (toRow - fromRow != 2) {
        throw new IllegalArgumentException("cannot make moves longer than two units");
      }
      if (getSlotAt(fromRow + 1, fromCol) != SlotState.Marble) {
        throw new IllegalArgumentException("cannot move over a empty marble");
      }
      else {
        marbles.get(getIndex(fromRow, fromCol)).emptyMarble();
        marbles.get(getIndex(fromRow + 1, fromCol)).emptyMarble();
        marbles.get(getIndex(toRow, toCol)).addMarble();
      }
    }
  }

  private void moveRight(int fromRow, int fromCol, int toRow, int toCol) {
    // horizontal move
    if (fromRow == toRow) {
      if (getSlotAt(fromRow, fromCol + 1) != SlotState.Marble) {
        throw new IllegalArgumentException("cannot move over a empty marble!");
      }
      if (fromCol -  toCol != -2) {
        throw new IllegalArgumentException("cannot make moves longer than two units");
      }
      else {
        marbles.get(getIndex(fromRow, fromCol)).emptyMarble();
        marbles.get(getIndex(fromRow, fromCol + 1)).emptyMarble();
        marbles.get(getIndex(toRow, toCol)).addMarble();
      }
    }
    // moving up right
    if (fromRow > toRow) {
      if (fromRow - toRow != 2 || fromCol != toCol) {
        throw new IllegalArgumentException("cannot make moves longer than two units");
      }
      if (getSlotAt(fromRow - 1, fromCol) != SlotState.Marble) {
        throw new IllegalArgumentException("cannot move over a empty marble!");
      }
      else {
        marbles.get(getIndex(fromRow, fromCol)).emptyMarble();
        marbles.get(getIndex(fromRow - 1, fromCol)).emptyMarble();
        marbles.get(getIndex(toRow, toCol)).addMarble();
      }
    }
    // moving down right
    if (fromRow < toRow) {
      if (toRow - fromRow != 2 || toCol - fromCol != 2) {
        throw new IllegalArgumentException("cannot make moves longer than two units");
      }
      if (getSlotAt(fromRow + 1, fromCol + 1) != SlotState.Marble) {
        throw new IllegalArgumentException("cannot move over a empty marble");
      }
      else {
        marbles.get(getIndex(fromRow, fromCol)).emptyMarble();
        marbles.get(getIndex(fromRow + 1, fromCol + 1)).emptyMarble();
        marbles.get(getIndex(toRow, toCol)).addMarble();
      }
    }
  }

  private boolean validPosition(int x, int y) {
    if (x < 0 || y < 0) {
      return false;
    }
    if (x > this.arm - 1 || y > this.arm - 1) {
      return false;
    }
    if (y > x) {
      return false;
    }
    return y <= this.arm;
  }

  private boolean hasMove(int x, int y) {
    if (!validPosition(x, y)) {
      return false;
    }
    // cannot move from an empty slot
    if (getSlotAt(x, y) == SlotState.Empty) {
      return false;
    }
    // check for left horizontal
    if (validPosition(x, y - 2)) {
      if (getSlotAt(x, y - 1) == SlotState.Marble
              && getSlotAt(x, y - 2) == SlotState.Empty) {
        return true;
      }
    }
    // check for right horizontal
    if (validPosition(x, y + 2)) {
      if (getSlotAt(x, y + 1) == SlotState.Marble
              && getSlotAt(x, y + 2) == SlotState.Empty) {
        return true;
      }
    }
    // check for left up
    if (validPosition(x - 2, y - 2)) {
      if (getSlotAt(x - 1, y - 1) == SlotState.Marble
              && getSlotAt(x - 2, y - 2) == SlotState.Empty) {
        return true;
      }
    }
    // check for left down
    if (validPosition(x + 2, y) && validPosition(x + 1, y)) {
      if (getSlotAt(x + 1, y) == SlotState.Marble
              && getSlotAt(x + 2, y) == SlotState.Empty) {
        return true;
      }
    }
    // check for right up
    if (validPosition(x - 2, y)) {
      if (getSlotAt(x - 1, y) == SlotState.Marble
              && getSlotAt(x - 2, y) == SlotState.Empty) {
        return true;
      }
    }
    // check for right down
    if (validPosition(x + 2, y + 2)) {
      return getSlotAt(x + 1, y + 1) == SlotState.Marble
              && getSlotAt(x + 2, y + 2) == SlotState.Empty;
    }
    return false;
  }

  @Override
  public boolean isGameOver() {
    for (int x = 0; x < this.arm; x++) {
      for (int y = 0; y < x + 1; y ++) {
        if (hasMove(x, y)) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public int getBoardSize() {
    return arm;
  }

  private int getIndex(int x, int y) throws IllegalArgumentException {
    if (x < 0 || x > this.arm - 1 || y < 0 || y > this.arm - 1) {
      throw new IllegalArgumentException("out of range!");
    }
    if (y > x) {
      throw new IllegalArgumentException("out of range!");
    }
    return y + ((1 + x) * x) / 2;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row < 0 || col < 0 || row > this.arm - 1 || col > this.arm - 1) {
      throw new IllegalArgumentException("invalid slot!");
    }
    if (col > row) {
      return SlotState.Invalid;
    }
    if (!marbles.get(getIndex(row, col)).empty) {
      return SlotState.Marble;
    }
    else if (marbles.get(getIndex(row, col)).empty) {
      return SlotState.Empty;
    }
    else {
      return SlotState.Invalid;
    }
  }

  @Override
  public int getScore() {
    int result = 0;
    for (int x = 0; x < this.arm; x ++) {
      for (int y = 0; y < x + 1; y ++) {
        if (getSlotAt(x, y) == SlotState.Marble) {
          result++;
        }
      }
    }
    return result;
  }
}
