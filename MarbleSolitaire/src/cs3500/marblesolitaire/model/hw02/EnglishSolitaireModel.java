package cs3500.marblesolitaire.model.hw02;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a representation of the game English Solitaire, a variation of marble solitaire.
 * It creates a plus-shaped board of marbles with an odd arm thickness 3 or greater and a single
 * empty slot to begin. It allows the user to get information about the current
 * game state (slots, game status, score, width), as well as to move pieces.
 */
public class EnglishSolitaireModel implements MarbleSolitaireModel {
  public int arm;
  public List<Marble> marbles;

  /**
   * Default constructor that creates a game board where the arm thickness is 3 and the empty
   * cell is in the center of the game board.
   */
  public EnglishSolitaireModel() {
    this.arm = 3;
    this.marbles = this.makeMarbles();
    this.marbles.get(getIndex(3, 3)).emptyMarble();
  }

  /**
   * Constructor that takes in x and y value of empty cell and create tha gam board representation
   * where the arm thickness is 3.
   *
   * @param x x position of empty cell
   * @param y y position of empty cell
   */
  public EnglishSolitaireModel(int x, int y) throws IllegalArgumentException {
    this.arm = 3;
    this.marbles = makeMarbles();
    if (validPosition(x, y)) {
      this.marbles.get(getIndex(x, y)).emptyMarble();
    }
    else {
      throw new IllegalArgumentException("Invalid empty cell position (" + x
              + ", " + y + ")");
    }
  }

  /**
   * Constructor that takes in an arm thickness and create the game board representation where the
   * empty cell is in the center of game board.
   *
   * @param arm positive odd integer representing arm thickness of board
   */
  public EnglishSolitaireModel(int arm) throws IllegalArgumentException {
    if (arm < 0 || arm % 2 == 0 || arm == 1) {
      throw new IllegalArgumentException("Arm thickness is not an odd positive integer!");
    }
    else {
      this.arm = arm;
      this.marbles = makeMarbles();
      this.marbles.get(getIndex((3 * arm - 3) / 2, (3 * arm - 3) / 2)).emptyMarble();
    }
  }

  /**
   * Constructor that takes in an arm thickness, x and y value of center cell to create the game
   * board representation.
   *
   * @param arm positive odd integer representing arm thickness of board.
   * @param x   x position of empty cell
   * @param y   y position of empty cell
   */
  public EnglishSolitaireModel(int arm, int x, int y) throws IllegalArgumentException {
    this.arm = arm;
    this.marbles = makeMarbles();
    if (!validPosition(x, y)) {
      throw new IllegalArgumentException("Invalid empty cell position (" + x
              + ", " + y + ")");
    }
    if (arm < 0 || arm % 2 == 0) {
      throw new IllegalArgumentException("Arm thickness is not an odd positive integer!");
    } else {
      this.marbles.get(getIndex(x, y)).emptyMarble();
    }
  }


  /**
   * Fills the game board with marbles according to the size of game board.
   *
   * @return the created list of marbles
   */
  private List<Marble> makeMarbles() {
    List<Marble> marbles = new ArrayList<>();
    // creating y rows
    for (int x = 0; x < this.arm * 3 - 2; x++) {
      for (int y = 0; y < this.arm * 3 - 2; y++) {
        if (x < this.arm - 1 && y < this.arm - 1 || x < this.arm - 1 && y > this.arm * 2 - 2) {
          marbles.add(new Marble(x, y, true, false));
        }
        else if (x > this.arm * 2 - 2 && y < this.arm - 1
                || x > this.arm * 2 - 2 && y > this.arm * 2 - 2) {
          marbles.add(new Marble(x, y, true, false));
        }
        else {
          marbles.add(new Marble(x, y));
        }
      }
    }
    return marbles;
  }

  /**
   * Checks if the entered position is a valid position on the game board.
   *
   * @param x the x coordinate of the position
   * @param y the y coordinate of the position
   * @return a boolean value represent the validity of the entered position
   */
  public boolean validPosition(int x, int y) {
    // coordinates cannot be negative or greater than the board size
    // bottom part invalid marbles
    if (x < 0 || y < 0 || x > this.arm * 3 - 3 || y > this.arm * 3 - 3) {
      return false;
    }
    // top part invalid marbles
    else {
      return getSlotAt(x, y) != SlotState.Invalid;
    }
  }

  /**
   * Gets the index of marble at x, y.
   *
   * @param x x coordinate of intended marble
   * @param y y coordinate of intended marble
   * @return index of that marble
   */
  public int getIndex(int x, int y) throws IllegalArgumentException {
    return y + x * this.getBoardSize();
  }


  /**
   * To make moves on the board following the English Solitaire game rules.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0) x coordinate of "from" position
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0) y coordinate of "from" position
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0) x coordinate of "to" position
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0) y coordinate of "to" position
   * @throws IllegalArgumentException when the move is invalid
   */
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    boolean from;
    boolean to;
    // checks for valid from position
    from = validPosition(fromRow, fromCol);
    // checks for valid to position
    to = validPosition(toRow, toCol);
    // to position has to be empty
    //throw error when from position is not on board
    if (!from || ! to) {
      throw new IllegalArgumentException("Invalid \"from\" or \"to\" position!");
    }

    if (fromRow < 0 || fromCol < 0 || toRow < 0 || toCol < 0) {
      throw new IllegalArgumentException("coordinates of from and to position cannot be negative");
    }
    if (getSlotAt(toRow, toCol) != SlotState.Empty) {
      throw new IllegalArgumentException("\"to\" position has to be empty!");
    }

    // throw error when from position is the same as to position
    if (fromCol == toCol && fromRow == toRow) {
      throw new IllegalArgumentException("Cannot move to the same position");
    }
    // when to position is empty
    else {
      // moving horizontally
      if (fromCol == toCol) {
        moveHorizontal(fromRow, fromCol, toRow, toCol);
      }
      // moving vertically
      else if (fromRow == toRow) {
        moveVertical(fromRow, fromCol, toRow, toCol);
      }
      else {
        throw new IllegalArgumentException("Invalid move");
      }
    }
  }

  /**
   * helper for move to make vertical moves.
   *
   * @param fromRow x coordinate for from position
   * @param fromCol y coordinate for from position
   * @param toRow x coordinate for to position
   * @param toCol y coordinate for to position
   * @throws IllegalArgumentException when error as shown in comments below
   */
  private void moveVertical(int fromRow, int fromCol, int toRow, int toCol)
          throws IllegalArgumentException {
    // moving up
    if (fromCol > toCol) {
      // checks for valid move length
      if (fromCol - toCol != 2) {
        throw new IllegalArgumentException("cannot make moves more than two units away");
      }
      // checks for middle marble of the move
      else if (marbles.get(getIndex(fromRow, fromCol - 1)).empty) {
        throw new IllegalArgumentException("must jump over a non-empty position");
      }
      // move up and modify the game board
      else {
        marbles.get(getIndex(fromRow, fromCol)).emptyMarble();
        marbles.get(getIndex(fromRow, fromCol - 1)).emptyMarble();
        marbles.get(getIndex(toRow, toCol)).addMarble();

      }
    }
    //moving down
    else {
      //checks for valid move length
      if (toCol - fromCol != 2) {
        throw new IllegalArgumentException("cannot make moves more than two units away");
      }
      // checks for middle marble of the move
      else if (marbles.get(getIndex(fromRow, fromCol + 1)).empty) {
        throw new IllegalArgumentException("must jump over a non-empty position");
      }
      // move down and modify the board
      else {
        marbles.get(getIndex(fromRow, fromCol)).emptyMarble();
        marbles.get(getIndex(fromRow, fromCol + 1)).emptyMarble();
        marbles.get(getIndex(toRow, toCol)).addMarble();
      }
    }
  }

  /**
   * helper for move to make horizontal moves.
   *
   * @param fromRow x coordinate for from position
   * @param fromCol y coordinate for from position
   * @param toRow x coordinate for to position
   * @param toCol y coordinate for to position
   */
  private void moveHorizontal(int fromRow, int fromCol, int toRow, int toCol) {
    // horizontal moves have to be in the same row
    if (fromCol != toCol) {
      throw new IllegalArgumentException("Invalid horizontal move!");
    }
    // can only move 2 units
    if (Math.abs(fromRow - toRow) != 2) {
      throw new IllegalArgumentException("cannot move further than two units");
    }
    //moving left
    if (fromRow > toRow) {
      if (this.marbles.get(getIndex(fromRow - 1, fromCol)).empty) {
        throw new IllegalArgumentException("middle marble cannot be empty!");
      }
      else {
        this.marbles.get(getIndex(fromRow, fromCol)).emptyMarble();
        this.marbles.get(getIndex(fromRow - 1, fromCol)).emptyMarble();
        this.marbles.get(getIndex(toRow, toCol)).addMarble();
      }
    }
    //moving right
    else {
      if (this.marbles.get(getIndex(fromRow + 1, fromCol)).empty) {
        throw new IllegalArgumentException("middle marble cannot be empty!");
      }
      else {
        this.marbles.get(getIndex(fromRow, fromCol)).emptyMarble();
        this.marbles.get(getIndex(fromRow + 1, fromCol)).emptyMarble();
        this.marbles.get(getIndex(toRow, toCol)).addMarble();
      }
    }
  }

  /**
   * To check if the game is over.
   *
   * @return true if the game is over and false otherwise
   */
  public boolean isGameOver() {
    for (int x = 0; x < getBoardSize() - 1; x++) {
      for (int y = 0; y < getBoardSize() - 1; y++) {
        if (validPosition(x, y)) {
          if (hasMove(x,y)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  /**
   * checks if there is a valid move for that marble.
   *
   * @param x x coordinate of that marble
   * @param y y coordinate of that marble
   * @return
   */
  private boolean hasMove(int x, int y) {
    // cannot move from an empty slot
    if (getSlotAt(x, y) == SlotState.Empty) {
      return false;
    }
    // check possible left move
    if (validPosition(x - 2, y) && getSlotAt(x - 2, y) == SlotState.Empty) {
      if (validPosition(x - 1, y) && getSlotAt(x - 1, y) == SlotState.Marble) {
        return true;
      }
    }
    // check possible right move
    if (validPosition(x + 2, y) && getSlotAt(x + 2, y) == SlotState.Empty) {
      if (validPosition(x + 1, y) && getSlotAt(x + 1, y) == SlotState.Marble) {
        return true;
      }
    }
    // check possible up move
    if (validPosition(x, y - 2) && getSlotAt(x, y - 2) == SlotState.Empty) {
      if (validPosition(x, y - 1) && getSlotAt(x, y - 1) == SlotState.Marble) {
        return true;
      }
    }
    // check possible down move
    if (validPosition(x, y + 2) && getSlotAt(x, y + 2) == SlotState.Empty) {
      return validPosition(x, y + 1) && getSlotAt(x, y + 1) == SlotState.Marble;
    }
    return false;
  }


  @Override
  public int getBoardSize() {
    return 3 * this.arm - 2;
  }

  /**
   * to check if the move is valid.
   *
   * @return if the move is valid
   */
  private boolean validMove(int fromRow, int fromCol, int toRow, int toCol) {
    if (getSlotAt(fromRow, fromCol).equals(SlotState.Empty)
            || getSlotAt(toRow, toCol).equals(SlotState.Marble)) {
      return false;
    }
    if (fromRow == toRow) {
      if (fromCol > toCol || fromCol - toCol == 2) {
        return !marbles.get(getIndex(fromRow, fromCol - 1)).empty;
      }
      if (fromCol < toCol || fromCol - toCol == -2) {
        return !marbles.get(getIndex(fromRow, fromCol + 1)).empty;
      }
    }
    if (fromCol == toCol) {
      if (fromRow > toRow || fromRow - toRow == 2) {
        return !marbles.get(getIndex(fromRow - 1, fromCol)).empty;
      }
      if (fromRow < toRow || fromRow - toRow == -2) {
        return !marbles.get(getIndex(fromRow + 1, fromCol)).empty;
      }
    }
    return false;
  }


  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row < 0 || col < 0 || row > getBoardSize() - 1 || col > getBoardSize() - 1) {
      throw new IllegalArgumentException("invalid slot!");
    }
    if (!marbles.get(getIndex(row, col)).valid) {
      return SlotState.Invalid;
    }
    else if (marbles.get(getIndex(row, col)).empty) {
      return SlotState.Empty;
    }
    else {
      return SlotState.Marble;
    }
  }


  @Override
  public int getScore() {
    int result = 0;
    for (int x = 0; x < this.getBoardSize(); x++) {
      for (int y = 0; y < this.getBoardSize(); y++) {
        if (getSlotAt(x, y).equals(SlotState.Marble)) {
          result++;
        }
      }
    }
    return result;
  }
}
