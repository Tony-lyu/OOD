package spreadsheet;

/**
 * Command for average.
 */
public class Average implements Commands {

  private int x1;
  private int x2;
  private int y1;
  private int y2;
  private int x3;
  private int y3;

  /**
   * Constructor for average.
   *
   * @param x1 x1 value
   * @param y1 y1 value
   * @param x2 x2 value
   * @param y2 y2 value
   * @param x3 x3 value
   * @param y3 y3 value
   */
  public Average(int x1, int y1, int x2, int y2, int x3, int y3) {
    this.x1 = x1;
    this.x2 = x2;
    this.y1 = y1;
    this.y2 = y2;
    this.x3 = x3;
    this.y3 = y3;
  }

  @Override
  public void macro(SpreadSheet s) {
    int sum = 0;
    int num = 0;
    for (int i = x1; i <= x2; i++) {
      for (int j = y1; j <= y2; j++) {
        sum += s.get(i, j);
        num++;
      }
    }
    s.set(x3, y3, sum / num);
  }
}
