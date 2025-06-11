import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * class for testing simple thermostat.
 */
public class SimpleThermostatTest {
  SimpleThermostat thermostat1 = new SimpleThermostat("1234", 35);
  SimpleThermostat thermostat2 = new SimpleThermostat("1235", 40);
  SimpleThermostat thermostat3 = new SimpleThermostat("1236", 50);

  /**
   * test constructor.
   */
  @Test
  public void SimpleThermostat() {
    try {
      SimpleThermostat ts = new SimpleThermostat("", 35);
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "cannot have a blank ID");
    }
    try {
      SimpleThermostat ts = new SimpleThermostat("12345", 51);
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "temperature cannot be set greater than 50");
    }
    assertEquals(thermostat2.getID(), "1235");
  }

  /**
   * test for getting the ID of thermostat.
   */
  @Test
  public void getID() {
    assertEquals(thermostat1.getID(), "1234");
    assertEquals(thermostat2.getID(), "1235");
    assertEquals(thermostat3.getID(), "1236");
  }

  /**
   * test for getting the temperature of the thermostat.
   */
  @Test
  public void getSetTemperature() {
    assertEquals(thermostat1.getSetTemperature(), 308.15, 0.01);
    assertEquals(thermostat2.getSetTemperature(), 313.15, 0.01);
    assertEquals(thermostat3.getSetTemperature(), 323.15, 0.01);
  }

  /**
   * test increasing the temperature of the thermostat.
   */
  @Test
  public void increaseSetTemperature() {
    thermostat1.increaseSetTemperature();
    thermostat2.increaseSetTemperature();
    assertEquals(thermostat1.getSetTemperature(), 308.25, 0.01);
    assertEquals(thermostat2.getSetTemperature(), 313.25, 0.01);
    try {
      thermostat3.increaseSetTemperature();
    }
    catch (IllegalArgumentException error) {
      assertEquals(error.getMessage(), "temp cannot be greater than 50");
    }
  }

  /**
   * test decreasing the temperature of the thermostat.
   */
  @Test
  public void decreaseSetTemperature() {
    thermostat1.decreaseSetTemperature();
    thermostat2.decreaseSetTemperature();
    thermostat3.decreaseSetTemperature();
    assertEquals(thermostat1.getSetTemperature(), 308.05, 0.01);
    assertEquals(thermostat2.getSetTemperature(), 313.05, 0.01);
    assertEquals(thermostat3.getSetTemperature(), 323.05, 0.01);
  }

  /**
   * test equality of two thermostat.
   */
  @Test
  public void testEquals() {
    SimpleThermostat thermostat4 = new SimpleThermostat("1234", 35.01);
    SimpleThermostat thermostat5 = new SimpleThermostat("1234", 35.02);
    SimpleThermostat thermostat6 = new SimpleThermostat("1234", 35.20);
    assertTrue(thermostat1.equals(thermostat4));
    assertTrue(thermostat1.equals(thermostat5));
    assertTrue(thermostat1.equals(thermostat6));
  }

  /*
  @Test
  public void testEquals() {
    SimpleThermostat thermostat4 = new SimpleThermostat("1234", 35);
    SimpleThermostat thermostat5 = new SimpleThermostat("1235", 35);
    SimpleThermostat thermostat6 = new SimpleThermostat("1236", 50);
    assertTrue(thermostat1.equals(thermostat4));
    assertFalse(thermostat1.equals(thermostat5));
    assertTrue(thermostat3.equals(thermostat6));
  }
  */

  /**
   * test for hashcode after overriding the equal method.
   */
  @Test
  public void testHashCode() {
    assertEquals(thermostat1.hashCode(), 1081294848);
    assertEquals(thermostat2.hashCode(), 1081315328);
    assertEquals(thermostat3.hashCode(), 1081356288);
  }

  /*
  @Test
  public void testHashCode() {
    assertEquals(thermostat1.hashCode(), 1078034432);
    assertEquals(thermostat2.hashCode(), 1078198272);
    assertEquals(thermostat3.hashCode(), 1078525952);
  }
  */
}