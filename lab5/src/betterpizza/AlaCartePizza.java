package betterpizza;

import java.util.HashMap;
import java.util.Map;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * class representing alacarte pizza.
 */
public class AlaCartePizza implements ObservablePizza {
  protected Crust crust;
  protected Size size;
  protected Map<ToppingName, ToppingPortion> toppings;

  /**
   * Create a pizza given its crust type, size and toppings.
   */
  public AlaCartePizza(Size size, Crust crust) {
    this.crust = crust;
    this.size = size;
    this.toppings = new HashMap<ToppingName, ToppingPortion>();
  }

  /**
   * constructor for builder pattern.
   *
   * @param size size of the pizza
   * @param crust crust type of the pizza
   * @param toppings list of toppings of the pizza
   * @throws IllegalStateException if any given field is null
   */
  public AlaCartePizza(Size size, Crust crust, HashMap<ToppingName, ToppingPortion> toppings)
          throws IllegalStateException {
    if (size == null || crust == null || toppings == null) {
      throw new IllegalStateException("fields cannot be null!");
    }
    this.crust = crust;
    this.size = size;
    this.toppings = toppings;
  }

  /**
   * builder pattern for pizza.
   */
  public static class AlaCartePizzaBuilder extends PizzaBuilder {
    public AlaCartePizzaBuilder returnBuilder() {
      return this;
    }
  }

  @Override
  public ToppingPortion hasTopping(ToppingName name) {
    return this.toppings.getOrDefault(name, null);
  }

  @Override
  public double cost() {
    double cost = 0.0;
    for (Map.Entry<ToppingName, ToppingPortion> item : this.toppings.entrySet()) {
      cost += item.getKey().getCost() * item.getValue().getCostMultiplier();
    }
    return cost + this.size.getBaseCost();
  }

}
