import org.junit.Test;

import betterpizza.AlaCartePizza;
import betterpizza.CheesePizza;
import betterpizza.ObservablePizza;
import betterpizza.VeggiePizza;
import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;
import static org.junit.Assert.assertEquals;

/**
 * tests for better pizza.
 */
public class BetterPizzaTest {

  @Test
  public void testBetterPizza() {
    ObservablePizza alacarte = new AlaCartePizza.AlaCartePizzaBuilder()
            .crust(Crust.Classic)
            .size(Size.Medium)
            .addTopping(ToppingName.Cheese, ToppingPortion.Full)
            .addTopping(ToppingName.Sauce, ToppingPortion.Full)
            .addTopping(ToppingName.GreenPepper, ToppingPortion.Full)
            .addTopping(ToppingName.Onion, ToppingPortion.Full)
            .addTopping(ToppingName.Jalapeno, ToppingPortion.LeftHalf)
            .build();

    assertEquals(alacarte.hasTopping(ToppingName.Cheese), ToppingPortion.Full);

  }

  @Test
  public void testCheesePizza() {
    ObservablePizza cheese = new AlaCartePizza.AlaCartePizzaBuilder()
            .crust(Crust.Classic)
            .size(Size.Medium)
            .addTopping(ToppingName.Cheese, ToppingPortion.Full)
            .addTopping(ToppingName.Sauce, ToppingPortion.Full)
            .addTopping(ToppingName.GreenPepper, ToppingPortion.Full)
            .addTopping(ToppingName.Onion, ToppingPortion.Full)
            .addTopping(ToppingName.Jalapeno, ToppingPortion.LeftHalf)
            .build();

    assertEquals(cheese.hasTopping(ToppingName.Cheese), ToppingPortion.Full);


  }

  @Test
  public void testCost() {
    ObservablePizza cheese = new CheesePizza.CheesePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Large)
            .build();
    assertEquals(cheese.cost(), 9, 0.01);
    assertEquals(cheese.hasTopping(ToppingName.Cheese), ToppingPortion.Full);

    ObservablePizza veggie = new VeggiePizza.VeggiePizzaBuilder()
            .crust(Crust.Classic)
            .size(Size.Large)
            .noBlackOlive()
            .build();
    assertEquals(veggie.cost(), 11.0, 0.01);
    assertEquals(veggie.hasTopping(ToppingName.Cheese), ToppingPortion.Full);
  }

}
