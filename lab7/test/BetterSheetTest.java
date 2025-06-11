import org.junit.Assert;
import org.junit.Test;


import spreadsheet.BetterSheet;

/**
 * test class for better Sheet.
 */
public class BetterSheetTest {

  /**
   * test for valid set all at.
   */
  @Test
  public void validSetAllAt() {
    BetterSheet b = new BetterSheet();
    b.setAllAt(0,0,2,2, 3.0);
    Assert.assertEquals(b.get(0,0), 3.0, 0.01);
    Assert.assertEquals(b.get(1,0), 3.0, 0.01);
    Assert.assertEquals(b.get(1,1), 3.0, 0.01);
    Assert.assertEquals(b.get(0,1), 3.0, 0.01);
  }

  /**
   * test for invalid set all at.
   */
  @Test(expected = IllegalArgumentException.class)
  public void InvalidX1SetAllAt() {
    BetterSheet b = new BetterSheet();
    b.setAllAt(-1,0,2,2, 3.0);
  }

  /**
   * test for invalid set all at.
   */
  @Test(expected = IllegalArgumentException.class)
  public void InvalidX2SetAllAt() {
    BetterSheet b = new BetterSheet();
    b.setAllAt(1,0,-1,2, 3.0);
  }

  /**
   * test for invalid set all at.
   */
  @Test(expected = IllegalArgumentException.class)
  public void InvalidY2SetAllAt() {
    BetterSheet b = new BetterSheet();
    b.setAllAt(1,0,1,-12, 3.0);
  }

  /**
   * test for invalid set all at.
   */
  @Test(expected = IllegalArgumentException.class)
  public void InvalidYSetAllAt() {
    BetterSheet b = new BetterSheet();
    b.setAllAt(1,-10,1,2, 3.0);
  }
}
