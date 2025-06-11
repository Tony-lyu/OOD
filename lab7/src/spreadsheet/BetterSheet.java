package spreadsheet;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A better implementation of SpreadSheet.
 */
public class BetterSheet implements BetterSpreadSheet {
  private final Map<CellPosition, Double> sheet;
  private int width;
  private int height;

  /**
   * Better sheet constructor.
   */
  public BetterSheet() {
    this.sheet = new HashMap<CellPosition, Double>();
    this.width = 0;
    this.height = 0;
  }

  @Override
  public double get(int row, int col) throws IllegalArgumentException {
    if (row < 0 || col < 0) {
      throw new IllegalArgumentException("Row or column cannot be negative");
    }
    return this.sheet.getOrDefault(new CellPosition(row,col),0.0);
  }

  @Override
  public void set(int row, int col,double value) throws IllegalArgumentException {
    if (row < 0 || col < 0) {
      throw new IllegalArgumentException("Row or column cannot be negative");
    }
    sheet.put(new CellPosition(row,col),value);
    if (row + 1 > height) {
      height = row + 1;
    }

    if (col + 1 > width) {
      width = col + 1;
    }
  }

  @Override
  public boolean isEmpty(int row, int col) throws IllegalArgumentException {
    if (row < 0 || col < 0) {
      throw new IllegalArgumentException("Row or column cannot be negative");
    }
    return !this.sheet.containsKey(new CellPosition(row,col));
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public void setAllAt(int x1, int y1, int x2, int y2, double value)
          throws IllegalArgumentException {
    if (x1 < 0 || x2 < 0 || y1 < 0 || y2 < 0) {
      throw new IllegalArgumentException("Row or column cannot be negative");
    }

    for (int i = x1; i < x2; i++) {
      for (int k = y1; k < y2; k++) {
        sheet.put(new CellPosition(i, k), value);
      }
      if ((x2 + 1) > height) {
        height = x2 + 1;
      }

      if ((y2 + 1) > width) {
        width = y2 + 1;
      }
    }
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
