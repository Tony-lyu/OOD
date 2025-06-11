package cs3500.marblesolitaire.model.hw02;

/**
 * Represents a Marble in a English Solitaire game.
 */
public class Marble {
  public int x;
  public int y;
  public boolean empty;
  public boolean valid;

  /**
   * Default marble constructor.
   * @param x x coordinate of the marble
   * @param y y coordinate of the marble
   */
  public Marble(int x, int y) {
    this.x = x;
    this.y = y;
    this.empty = false;
    this.valid = true;
  }

  /**
   * Marble constructor that takes in a boolean to represent if the given marble is removed.
   *
   * @param x x coordinate of the marble
   * @param y y coordinate of the marble
   * @param empty represent if the marble is removed
   */
  public Marble(int x, int y, boolean empty) {
    this.x = x;
    this.y = y;
    this.empty = empty;
    this.valid = true;
  }

  /**
   * Marble constructor that takes in two booleans to represent if the given marble is removed and
   * if the marble is valid.
   *
   * @param x x coordinate of the marble
   * @param y y coordinate of the marble
   * @param empty represent if the marble is removed
   * @param valid represent if the marble is valid
   */
  public Marble(int x, int y, boolean empty, boolean valid) {
    this.x = x;
    this.y = y;
    this.empty = empty;
    this.valid = valid;
  }

  /**
   * Removes the given marble on the game board.
   */
  public void emptyMarble() {
    this.empty = true;
  }

  /**
   * moves the given marble on the game board.
   */
  public void addMarble() {
    this.empty = false;
  }

  /**
   * check if this marble is empty.
   *
   * @return state of this marble
   */
  public boolean isEmpty() {
    return this.empty;
  }

  /**
   * check if this marble is valid.
   *
   * @return state of this marble
   */
  public boolean isValid() {
    return this.valid;
  }

  /**
   * checks if this marble is at given position.
   *
   * @param x x coordinate of the given position
   * @param y y coordinate of the given position
   * @return
   */
  public boolean samePosition(int x, int y) {
    return x == this.x && y == this.y;
  }

  public String toString() {
    return x + ", " + y;
  }
}
