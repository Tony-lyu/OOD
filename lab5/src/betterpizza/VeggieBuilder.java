package betterpizza;

import java.util.HashMap;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * builder pattern for veggie pizza.
 */
public class VeggieBuilder extends PizzaBuilder {
  private Crust crust;
  private Size size;
  private HashMap<ToppingName, ToppingPortion> toppings;

  /**
   * to add set toppings on a veggie pizza.
   */
  public VeggieBuilder() {
    this.toppings = new HashMap<ToppingName, ToppingPortion>();
    this.toppings.put(ToppingName.Cheese, ToppingPortion.Full);
    this.toppings.put(ToppingName.Sauce, ToppingPortion.Full);
    this.toppings.put(ToppingName.BlackOlive,ToppingPortion.Full);
    this.toppings.put(ToppingName.GreenPepper,ToppingPortion.Full);
    this.toppings.put(ToppingName.Onion,ToppingPortion.Full);
    this.toppings.put(ToppingName.Jalapeno,ToppingPortion.Full);
    this.toppings.put(ToppingName.Tomato,ToppingPortion.Full);
  }

  public PizzaBuilder addTopping(ToppingName name, ToppingPortion portion) {
    this.toppings.put(name, portion);
    return this;
  }

  /**
   * choice of crust of this pizza.
   *
   * @param crust type of crust
   * @return this cheese pizza builder
   */
  public VeggieBuilder crust(Crust crust) {
    this.crust = crust;
    return this;
  }

  /**
   * choice of size of this pizza.
   *
   * @param size size of pizza
   * @return this cheese pizza builder
   */
  public VeggieBuilder size(Size size) {
    this.size = size;
    return this;
  }

  /**
   * build this veggie pizza.
   * @return the pizza built by previous actions
   */
  public ObservablePizza build() {
    return new VeggiePizza(size, crust, toppings);
  }

  public PizzaBuilder noCheese() {
    toppings.remove(ToppingName.Cheese);
    return this;
  }

  public PizzaBuilder noSauce() {
    toppings.remove(ToppingName.Sauce);
    return this;
  }

  public PizzaBuilder noGreenPepper() {
    toppings.remove(ToppingName.GreenPepper);
    return this;
  }

  public PizzaBuilder noOnion() {
    toppings.remove(ToppingName.Onion);
    return this;
  }

  public PizzaBuilder noJalapeno() {
    toppings.remove(ToppingName.Jalapeno);
    return this;
  }

  public PizzaBuilder noTomato() {
    toppings.remove(ToppingName.Tomato);
    return this;
  }

  public PizzaBuilder noBlackOlive() {
    toppings.remove(ToppingName.BlackOlive);
    return this;
  }
}
