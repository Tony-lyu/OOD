package spreadsheet;

/**
 * A class that does the command BulkAssign.
 */
public class BulkAssignMacro implements Commands {
  private int x1;
  private int x2;
  private int y1;
  private int y2;
  private double value;

  /**
   * Constructor for this macro, takes in a range of cells and a value to assign to.
   * @param x1 lower x value of cell range
   * @param y1 lower y value of cell range
   * @param x2 larger x value of cell range
   * @param y2 larger y value of cell range
   * @param value value to assign
   * @throws IllegalArgumentException when inputs are invalid
   */
  public BulkAssignMacro(int x1, int y1, int x2, int y2, double value)
          throws IllegalArgumentException {
    if (x1 < 0 || y1 < 0 || x2 < 0 || y2 < 0 || x1 > x2 || y1 > y2) {
      throw new IllegalArgumentException("range cannot be smaller than zero");
    }
    this.x1 = x1;
    this.x2 = x2;
    this.y1 = y1;
    this.y2 = y2;
    this.value = value;
  }

  @Override
  public void macro(SpreadSheet s) {
    for (int i = x1; i < x2; i++) {
      for (int j = y1; j < y2; j++) {
        s.set(i, j, value);
      }
    }
  }
}
