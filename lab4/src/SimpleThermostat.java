/**
 * Class to represent simple thermostat.
 */
public class SimpleThermostat implements Thermostat {
  String id;
  double temp;

  /**
   * Simple thermostat constructor, takes in a string id and a double temperature.
   *
   * @param id   String represents the ID of this thermostat
   * @param temp double represents temperature in celsius
   * @throws IllegalArgumentException ID cannot be blank and temp cannot be greater than 50
   */
  public SimpleThermostat(String id, double temp) throws IllegalArgumentException {
    if (id.equals("")) {
      throw new IllegalArgumentException("cannot have a blank ID");
    }
    if (temp > 50) {
      throw new IllegalArgumentException("temperature cannot be set greater than 50");
    }
    else {
      this.id = id;
      this.temp = temp;
    }
  }

  /**
   * retrieve its unique ID.
   *
   * @return the ID of the thermostat
   */
  public String getID() {
    return id;
  }

  /**
   * gets the temperature in degrees Kelvin that the thermostat is set to.
   * @return the temperature of the thermostat
   */
  public double getSetTemperature() {
    return this.temp + 273.15;
  }

  /**
   * increases the set temperature for the thermostat.
   */
  public void increaseSetTemperature() throws IllegalArgumentException {
    temp += 0.1;
    if (temp > 50) {
      throw new IllegalArgumentException("temp cannot be greater than 50");
    }
  }

  /**
   * decreases the set temperature for the thermostat.
   */
  public void decreaseSetTemperature() {
    temp -= 0.1;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Thermostat) {
      return this.id.equals(((Thermostat) other).getID())
              && Math.round(this.getSetTemperature() * 100) / 100
              == Math.round(((Thermostat) other).getSetTemperature() * 100) / 100;
    }
    return false;
  }

  @Override
  public int hashCode() {

    return Double.hashCode(Math.round(this.getSetTemperature() * 100) / 100);
  }

  /*
  @Override
  public boolean equals(Object other) {
    if (other instanceof Thermostat) {
      if (this.ID.equals(((Thermostat) other).getID())
              && Math.abs(this.temp - (((Thermostat) other).getSetTemperature() - 273.15)) < 0.01) {
        return true;
      }
      else {
        return false;
      }
    }
    else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Double.hashCode(temp);
  }
  */

}
