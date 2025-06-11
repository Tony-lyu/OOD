import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * to test SimpleFraction methods and constructor.
 */
public class FractionsTests {

  SimpleFraction sf = new SimpleFraction(3, 5);
  SimpleFraction sf2 = new SimpleFraction(-2, -5);

  @Test
  public void SimpleFraction() {
    assertEquals(new SimpleFraction(3, 5).getDecimalValue(2),
            sf.getDecimalValue(2), 0.01);
    assertEquals(new SimpleFraction(-2, -5).getDecimalValue(2),
            sf2.getDecimalValue(2), 0.01);
    try {
      SimpleFraction sf = new SimpleFraction(-2, 5);
    }
    catch (IllegalArgumentException error) {
      assertEquals(error.getMessage(), "Fraction is negative!");
    }
    try {
      SimpleFraction sf = new SimpleFraction(2, -5);
    }
    catch (IllegalArgumentException error) {
      assertEquals(error.getMessage(), "Fraction is negative!");
    }
    try {
      SimpleFraction sf = new SimpleFraction(2, 0);
    }
    catch (IllegalArgumentException error) {
      assertEquals(error.getMessage(), "Denominator cannot be zero!");
    }
  }

  @org.junit.Test
  public void add() {
    SimpleFraction sf = new SimpleFraction(0, 5);
    SimpleFraction sf2 = new SimpleFraction(-3, -5);
    SimpleFraction sf3 = new SimpleFraction(4, 5);
    SimpleFraction sf4 = new SimpleFraction(1, 2);
    assertEquals(this.sf.add(this.sf2).getDecimalValue(2) , 1, 0.001);
    assertEquals(sf3.add(sf4).getDecimalValue(2) , 1.3, 0.001);
    assertEquals(sf3.add(new SimpleFraction(0, 5)).getDecimalValue(2),
            0.8, 0.01);
    assertEquals(sf.add(sf2).getDecimalValue(1), 0.6, 0.01);
  }

  @org.junit.Test
  public void testAdd() {
    assertEquals(this.sf.add(2,5).getDecimalValue(4),
            1, 0.01);
    assertEquals(this.sf.add(4,5).getDecimalValue(4),
            1.4, 0.01);
    try {
      this.sf.add(2, 0);
    }
    catch (IllegalArgumentException error) {
      assertEquals(error.getMessage(), "Denominator cannot be zero!");
    }

    assertEquals(this.sf.add(0,5).getDecimalValue(4),
            0.6, 0.01);
  }

  @org.junit.Test
  public void getDecimalValue() {
    assertEquals(this.sf.getDecimalValue(1), 0.6, 0.01);
    assertEquals(this.sf.getDecimalValue(0), 1, 0.01);
    assertEquals(this.sf.getDecimalValue(2), 0.60, 0.01);
    assertEquals(this.sf2.getDecimalValue(1), 0.4, 0.01);
  }

  @org.junit.Test
  public void testToString() {
    assertEquals(this.sf.toString(), "3/5");
    assertEquals(this.sf2.toString(), "2/5");
  }
}