/**
 * interface to represent different kinds of thermostats.
 */
public interface Thermostat {

  /**
   * retrieve its unique ID.
   * @return the ID of the thermostat
   */
  String getID();


  /**
   * gets the temperature in degrees Kelvin that the thermostat is set to.
   * @return the temperature of the thermostat
   */
  double getSetTemperature();


  /**
   * increases the set temperature for the thermostat.
   */
  void increaseSetTemperature();

  /**
   * decreases the set temperature for the thermostat.
   */
  void decreaseSetTemperature();

}
