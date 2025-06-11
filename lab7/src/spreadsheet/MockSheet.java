package spreadsheet;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Mock class for testing.
 */
public class MockSheet implements SpreadSheet {
  private final Map<CellPosition, Double> sheet;
  private String input;


  public MockSheet() {
    this.sheet = new HashMap<CellPosition,Double>();
    this.input = "";
  }

  @Override
  public double get(int row, int col) throws IllegalArgumentException {
    if ((row < 0) || (col < 0)) {
      throw new IllegalArgumentException("Row or column cannot be negative");
    }
    return this.sheet.getOrDefault(new CellPosition(row,col),0.0);
  }

  @Override
  public void set(int row, int col, double value) throws IllegalArgumentException {
    this.input = input + row + " " + col + " " + value;
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

  @Override
  public String toString() {
    return input;
  }

  private static class CellPosition {
    private final int row;
    private final int column;

    private CellPosition(int row,int column) {
      this.row = row;
      this.column = column;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof CellPosition)) {
        return false;
      }
      CellPosition other = (CellPosition)o;
      return this.row == other.row && this.column == other.column;
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.row,this.column);
    }
  }
}
