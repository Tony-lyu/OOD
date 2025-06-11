import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a temperature monitor. It tracks several thermostats, and specifically
 * monitors how many of them have been set to too hot.
 */
public class NaiveTemperatureMonitor implements TemperatureMonitor {
  private final List<Thermostat> thermostatList;

  /**
   * NaiveTemperatureMonitor constructor, doesn't take in anything, objects can added into this
   * monitor to be monitored.
   */
  public NaiveTemperatureMonitor() {
    this.thermostatList = new ArrayList<Thermostat>();
  }

  /**
   * adding a new thermostat into this monitor.
   * @param t a thermostat to be added
   */
  public void add(Thermostat t) {
    thermostatList.add(t);
  }


  /**
   * for testing add and remove method.
   *
   * @return size of the list of thermostat in this Temperature Monitor
   */
  public int getNumberOfThermostats() {
    return thermostatList.size();
  }

  /**
   * removing a thermostat into this monitor.
   *
   * @param t a thermostat to be removed
   */
  public void remove(Thermostat t) {
    thermostatList.remove(t);
  }

  /**
   * checks how many thermostat in this monitor is overheating.
   * @return if there are too much heating
   */
  public boolean tooMuchHeating() {
    Thermostat st = new SimpleThermostat("0", -2345678);
    int count = 0;
    for (Thermostat t: thermostatList) {
      if (t.getSetTemperature() > 23 + 273) {
        if (!t.equals(st)) {
          st = t;
          count += 1;
        }
      }
    }
    return count > 1;
  }
}
