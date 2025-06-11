import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * test for temperature monitor.
 */
public class NaiveTemperatureMonitorTest {
  NaiveTemperatureMonitor ntm = new NaiveTemperatureMonitor();
  SimpleThermostat st = new SimpleThermostat("0000", 25);
  SimpleThermostat st2 = new SimpleThermostat("0001", 50);
  SimpleThermostat st3 = new SimpleThermostat("0002", 30);

  /**
   * test for add.
   */
  @Test
  public void add() {
    assertEquals(ntm.getNumberOfThermostats(), 0);
    ntm.add(st);
    assertEquals(ntm.getNumberOfThermostats(), 1);
    ntm.add(st2);
    assertEquals(ntm.getNumberOfThermostats(), 2);
    ntm.add(st3);
    assertEquals(ntm.getNumberOfThermostats(), 3);
  }

  /**
   * test for remove.
   */
  @Test
  public void remove() {
    ntm.add(st);
    ntm.add(st);
    ntm.add(st);
    ntm.add(st);
    assertEquals(ntm.getNumberOfThermostats(), 4);
    ntm.remove(st);
    assertEquals(ntm.getNumberOfThermostats(), 3);
    ntm.remove(st);
    assertEquals(ntm.getNumberOfThermostats(), 2);
    ntm.remove(st);
    assertEquals(ntm.getNumberOfThermostats(), 1);
    ntm.remove(st);
    assertEquals(ntm.getNumberOfThermostats(), 0);
  }

  /**
   * test for too much heating.
   */
  @Test
  public void tooMuchHeating() {
    ntm.add(st);
    ntm.add(st);
    ntm.add(st);
    ntm.add(st);
    st.increaseSetTemperature();
    assertFalse(ntm.tooMuchHeating());
    st.increaseSetTemperature();
    assertFalse(ntm.tooMuchHeating());
    ntm.add(st2);
    assertTrue(ntm.tooMuchHeating());
  }
}