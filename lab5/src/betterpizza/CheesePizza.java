package betterpizza;


import java.util.HashMap;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * This class represents a cheese pizza.
 */

public class CheesePizza extends AlaCartePizza {

  /**
   * Create a cheese pizza of the specified size with the specified crust.
   * @param size the size of this pizza
   * @param crust the crust of this pizza
   */
  public CheesePizza(Size size, Crust crust, HashMap<ToppingName, ToppingPortion> toppings) {
    super(size, crust, toppings);
  }

  /**
   * builder pattern for cheese pizza.
   */
  public static class CheesePizzaBuilder extends CheeseBuilder {
    public CheeseBuilder returnBuilder() {
      return this;
    }
  }
}
