package spreadsheet;

/**
 * Interface for a better implementation of Spread Sheet.
 */
public interface BetterSpreadSheet extends SpreadSheet {

  /**
   * takes a rectangular region in the spreadsheet and a value, and sets all cells in
   * that region to the specified value.
   *
   * @param x x for first position
   * @param y y for first position
   * @param x2 x for second position
   * @param y2 y for second position
   * @param value value to be added
   */
  void setAllAt(int x, int y, int x2, int y2, double value) throws IllegalArgumentException;
}
