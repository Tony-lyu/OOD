package betterpizza;

import java.util.HashMap;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * abstract pizza builder class for builder pattern.
 * @param <T> generic type T for pizza
 */
public abstract class PizzaBuilder<T extends PizzaBuilder<T>> {
  private Crust crust;
  private Size size;
  private HashMap<ToppingName, ToppingPortion> toppings;

  public PizzaBuilder() {
    this.toppings = new HashMap<ToppingName, ToppingPortion>();
  }

  public PizzaBuilder<T> crust(Crust crust) {
    this.crust = crust;
    return this;
  }

  public PizzaBuilder<T> size(Size size) {
    this.size = size;
    return this;
  }

  public PizzaBuilder<T> addTopping(ToppingName name, ToppingPortion portion) {
    this.toppings.put(name, portion);
    return this;
  }

  public PizzaBuilder<T> leftHalfCheese() {
    this.toppings.put(ToppingName.Cheese, ToppingPortion.LeftHalf);
    return this;
  }

  public PizzaBuilder<T> rightHalfCheese() {
    this.toppings.put(ToppingName.Cheese, ToppingPortion.RightHalf);
    return this;
  }

  public PizzaBuilder<T> noCheese() {
    this.toppings.remove(ToppingName.Cheese);
    return this;
  }

  public PizzaBuilder noSauce() {
    this.toppings.remove(ToppingName.Sauce);
    return this;
  }

  public PizzaBuilder noAlfredo() {
    this.toppings.remove(ToppingName.Alfredo);
    return this;
  }

  public PizzaBuilder noGreenPepper() {
    this.toppings.remove(ToppingName.GreenPepper);
    return this;
  }

  public PizzaBuilder noTomato() {
    this.toppings.remove(ToppingName.Tomato);
    return this;
  }

  public PizzaBuilder noOnion() {
    this.toppings.remove(ToppingName.Onion);
    return this;
  }

  public PizzaBuilder noJalapeno() {
    this.toppings.remove(ToppingName.Jalapeno);
    return this;
  }

  public PizzaBuilder noBlackOlive() {
    this.toppings.remove(ToppingName.BlackOlive);
    return this;
  }

  public PizzaBuilder noHam() {
    this.toppings.remove(ToppingName.Ham);
    return this;
  }

  public PizzaBuilder noPepperoni() {
    this.toppings.remove(ToppingName.Pepperoni);
    return this;
  }

  public PizzaBuilder noSteak() {
    this.toppings.remove(ToppingName.Steak);
    return this;
  }

  public PizzaBuilder noChicken() {
    this.toppings.remove(ToppingName.Chicken);
    return this;
  }



  public ObservablePizza build() {
    return new AlaCartePizza(size, crust, toppings);
  }

  public PizzaBuilder<T> returnBuilder() {
    return this;
  }
}
