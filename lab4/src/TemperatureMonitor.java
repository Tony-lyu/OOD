/**
 * interface to represent temperatureMonitors.
 */
public interface TemperatureMonitor {

  /**
   * adding a thermostat into temperature monitor.
   * @param t thermostat being added
   */
  void add(Thermostat t);

  /**
   * removing thermostat in temperature monitor.
   * @param t thermostat being removed
   */
  void remove(Thermostat t);

  /**
   * determines if this monitor has too much heating.
   * @return boolean representing if there's too much heat
   */
  boolean tooMuchHeating();

}
