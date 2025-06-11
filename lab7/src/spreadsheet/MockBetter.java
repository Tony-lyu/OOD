package spreadsheet;

/**
 * Mock class for testing.
 */
public class MockBetter implements BetterSpreadSheet {

  @Override
  public void setAllAt(int x, int y, int x2, int y2, double value) throws IllegalArgumentException {
    // does nothing
  }

  @Override
  public double get(int row, int col) throws IllegalArgumentException {
    return 0;
  }

  @Override
  public void set(int row, int col, double value) throws IllegalArgumentException {
    //does nothing
  }

  @Override
  public boolean isEmpty(int row, int col) throws IllegalArgumentException {
    return false;
  }

  @Override
  public int getWidth() {
    return 0;
  }

  @Override
  public int getHeight() {
    return 0;
  }
}
