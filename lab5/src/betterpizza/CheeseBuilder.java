package betterpizza;

import java.util.HashMap;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * builder pattern class for cheese pizza.
 */
public class CheeseBuilder extends PizzaBuilder {
  private Crust crust;
  private Size size;
  private HashMap<ToppingName, ToppingPortion> toppings;

  /**
   * constructor for cheese pizza.
   */
  public CheeseBuilder() {
    this.toppings = new HashMap<ToppingName, ToppingPortion>();
    this.toppings.put(ToppingName.Cheese, ToppingPortion.Full);
    this.toppings.put(ToppingName.Sauce, ToppingPortion.Full);
  }

  /**
   * choice of crust of this pizza.
   *
   * @param crust type of crust
   * @return this cheese pizza builder
   */
  public PizzaBuilder crust(Crust crust) {
    this.crust = crust;
    return this;
  }

  /**
   * choice of size of this pizza.
   *
   * @param size size of pizza
   * @return this cheese pizza builder
   */
  public PizzaBuilder size(Size size) {
    this.size = size;
    return this;
  }

  /**
   * delete cheese for cheese pizza.
   * @return this cheese builder.
   */
  public PizzaBuilder noCheese() {
    this.toppings.remove(ToppingName.Cheese);
    return this;
  }

  /**
   * add cheese on left side of pizza.
   * @return this cheese builder.
   */
  public PizzaBuilder leftHalfCheese() {
    this.toppings.remove(ToppingName.Cheese);
    this.toppings.put(ToppingName.Cheese, ToppingPortion.LeftHalf);
    return this;
  }

  /**
   * add cheese on right side of pizza.
   * @return this cheese builder.
   */
  public PizzaBuilder rightHalfCheese() {
    this.toppings.remove(ToppingName.Cheese);
    this.toppings.put(ToppingName.Cheese, ToppingPortion.RightHalf);
    return this;
  }

  public PizzaBuilder addTopping(ToppingName name, ToppingPortion portion) {
    this.toppings.put(name, portion);
    return this;
  }

  /**
   * build this cheese pizza.
   * @return the pizza built by previous actions
   */
  public ObservablePizza build() {
    return new CheesePizza(size, crust, toppings);
  }


}
