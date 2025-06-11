import org.junit.Before;
import org.junit.Test;


import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * tests for European Solitaire model.
 */
public class EuropeanSolitaireModelTest {
  EuropeanSolitaireModel game;
  EuropeanSolitaireModel game2;
  EuropeanSolitaireModel game3;
  EuropeanSolitaireModel game4;
  MarbleSolitaireTextView view;
  MarbleSolitaireTextView view2;
  MarbleSolitaireTextView view3;
  MarbleSolitaireTextView view4;

  @Before
  public void set() {
    game = new EuropeanSolitaireModel();
    game2 = new EuropeanSolitaireModel(5);
    game3 = new EuropeanSolitaireModel(3,4);
    game4 = new EuropeanSolitaireModel(5, 8, 4);
    view = new MarbleSolitaireTextView(game);
    view2 = new MarbleSolitaireTextView(game2);
    view3 = new MarbleSolitaireTextView(game3);
    view4 = new MarbleSolitaireTextView(game4);
  }

  /**
   * test for European SolitaireModel default constructor.
   */
  @Test
  public void EuropeanDefaultConstructor() {
    assertEquals(view.toString(), "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O");
  }

  /**
   * test for European SolitaireModel X Y constructor.
   */
  @Test
  public void EuropeanXYConstructor() {
    assertEquals(view3.toString(), "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O _ O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O");
  }

  /**
   * test for European SolitaireModel arm constructor.
   */
  @Test
  public void EuropeanArmConstructor() {
    assertEquals(view2.toString(), "        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O");
  }

  /**
   * test for European SolitaireModel arm x y constructor.
   */
  @Test
  public void EuropeanArmXYConstructor() {
    assertEquals(view4.toString(), "        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O _ O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O");
  }

  /**
   * test for European SolitaireModel invalid arm constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void EuropeanInvalidArmConstructor() {
    try {
      EuropeanSolitaireModel e2 = new EuropeanSolitaireModel(4);
    }
    catch (IllegalArgumentException error) {
      assertEquals(error.getMessage(), "Arm thickness is not an odd positive integer!");
    }
    EuropeanSolitaireModel e = new EuropeanSolitaireModel(-3);
  }

  /**
   * test for European SolitaireModel invalid X Y constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void EuropeanInvalidXYConstructor() {
    try {
      EuropeanSolitaireModel e2 = new EuropeanSolitaireModel(-2, 0);
    }
    catch (IllegalArgumentException error) {
      assertEquals(error.getMessage(), "Invalid empty cell position (-2, 0)");
    }
    EuropeanSolitaireModel e = new EuropeanSolitaireModel(3, -1);
  }

  /**
   * test for European SolitaireModel invalid arm X Y constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void EuropeanInvalidArmXYConstructor() {
    try {
      EuropeanSolitaireModel e2 = new EuropeanSolitaireModel(5,-2, 0);
    }
    catch (IllegalArgumentException error) {
      assertEquals(error.getMessage(), "Invalid empty cell position (-2, 0)");
    }
    EuropeanSolitaireModel e = new EuropeanSolitaireModel(-5, 3, 4);
  }

  /**
   * test for European SolitaireModel valid left move.
   */
  @Test
  public void EuropeanMoveLeft() {
    game3.move(3, 6, 3,4);
    assertEquals(view3.toString(), "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O _ _\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O");
  }

  /**
   * test for European SolitaireModel valid right move.
   */
  @Test
  public void EuropeanMoveRight() {
    game3.move(3, 2, 3,4);
    assertEquals(view3.toString(), "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O _ _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O");
  }

  /**
   * test for European SolitaireModel valid up move.
   */
  @Test
  public void EuropeanMoveUp() {
    game3.move(5, 4, 3,4);
    assertEquals(view3.toString(), "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O _ O O\n" +
            "  O O O _ O\n" +
            "    O O O");
  }

  /**
   * test for European SolitaireModel valid down move.
   */
  @Test
  public void EuropeanMoveDown() {
    game3.move(1, 4, 3,4);
    assertEquals(view3.toString(), "    O O O\n" +
            "  O O O _ O\n" +
            "O O O O _ O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O");
  }

  /**
   * test for European SolitaireModel invalid up move.
   */
  @Test(expected = IllegalArgumentException.class)
  public void EuropeanInvalidMoveUp() {
    game3.move(4, 4, 3,4);
  }

  /**
   * test for European SolitaireModel invalid down move.
   */
  @Test(expected = IllegalArgumentException.class)
  public void EuropeanInvalidMoveDown() {
    game3.move(2, 4, 3,4);
  }

  /**
   * test for European SolitaireModel invalid left move.
   */
  @Test(expected = IllegalArgumentException.class)
  public void EuropeanInvalidMoveLeft() {
    game3.move(3, 5, 3,4);
  }

  /**
   * test for European SolitaireModel invalid right move.
   */
  @Test(expected = IllegalArgumentException.class)
  public void EuropeanInvalidMoveRight() {
    game3.move(3, 3, 3,4);
  }

  /**
   * test for get slot at.
   */
  @Test
  public void EuropeanValidGetSlotAt() {
    assertEquals(game.getSlotAt(0,0), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(game.getSlotAt(0,6), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(game.getSlotAt(1,0), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(game.getSlotAt(1,6), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(game.getSlotAt(3,4), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game.getSlotAt(3,3), MarbleSolitaireModelState.SlotState.Empty);
  }

  /**
   * test for invalid get slot at.
   */
  @Test(expected = IllegalArgumentException.class)
  public void EuropeanInvalidRowGetSlotAt() {
    game.getSlotAt(-1,0);
  }

  /**
   * test for invalid get slot at.
   */
  @Test(expected = IllegalArgumentException.class)
  public void EuropeanInvalidColGetSlotAt() {
    game.getSlotAt(1,-3);
  }

  /**
   * test for determining if the game is over.
   */
  @Test
  public void testIsGameOver() {
    assertFalse(game.isGameOver());
  }

  /**
   * test for getting the size of the game board.
   */
  @Test
  public void testGetBoardSize() {
    assertEquals(game.getBoardSize(), 7);
    assertEquals(game2.getBoardSize(), 13);
    EnglishSolitaireModel m = new EnglishSolitaireModel(7);
    assertEquals(m.getBoardSize(), 19);
  }

  /**
   * test for getting the score of the current English Solitaire game.
   */
  @Test
  public void testGetScore() {
    assertEquals(game.getScore(), 36);
    assertEquals(game2.getScore(), 128);
    game.move(3, 1, 3,3);
    assertEquals(game.getScore(), 35);
  }

}
