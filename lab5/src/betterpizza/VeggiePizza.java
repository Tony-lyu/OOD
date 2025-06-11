package betterpizza;

import java.util.HashMap;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * represent a enhanced veggie pizza.
 */
public class VeggiePizza extends AlaCartePizza {
  /**
   * Create a veggie pizza with all vegetarian toppings, of the specified
   * size with the specified crust.
   * @param size the size of this pizza
   * @param crust the crust of this pizza
   */
  public VeggiePizza(Size size, Crust crust, HashMap<ToppingName, ToppingPortion> toppings) {
    super(size, crust, toppings);
  }

  /**
   * builder pattern for veggie pizza.
   */
  public static class VeggiePizzaBuilder extends VeggieBuilder {
    public VeggieBuilder returnBuilder() {
      return this;
    }
  }
}
