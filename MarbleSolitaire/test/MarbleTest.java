import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.Marble;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * tests for marble class.
 */
public class MarbleTest {

  /**
   * test for marble constructors.
   */
  @Test
  public void Marble() {
    Marble m = new Marble(0,0);
    assertFalse(m.isEmpty());
    assertTrue(new Marble(0, 0, true).isEmpty());
    Marble m2 = new Marble(0, 0, true);
    assertTrue(m2.isEmpty());
    Marble m3 = new Marble(3, 4, false, true);
    assertFalse(m3.isEmpty());
    assertFalse(m3.isEmpty());
  }

  /**
   * test for empty marble.
   */
  @Test
  public void emptyMarble() {
    Marble m = new Marble(0,0);
    m.emptyMarble();
    assertTrue(m.isEmpty());
    Marble m2 = new Marble(10,30, false);
    m2.emptyMarble();
    assertTrue(m.isEmpty());
    Marble m3 = new Marble(0,0, false, false);
    m3.emptyMarble();
    assertTrue(m3.isEmpty());
  }

  /**
   * test for same position.
   */
  @Test
  public void samePosition() {
    Marble m = new Marble(3, 5);
    assertTrue(m.samePosition(3,5));
    assertFalse(m.samePosition(4,3));
  }
}