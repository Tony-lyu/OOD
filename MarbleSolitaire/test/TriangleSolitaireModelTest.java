import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * tests for Triangle Solitaire model.
 */
public class TriangleSolitaireModelTest {
  TriangleSolitaireModel game;
  TriangleSolitaireModel game2;
  TriangleSolitaireModel game3;
  TriangleSolitaireModel game4;
  TriangleSolitaireTextView view;
  TriangleSolitaireTextView view2;
  TriangleSolitaireTextView view3;
  TriangleSolitaireTextView view4;

  @Before
  public void set() {
    game = new TriangleSolitaireModel();
    game2 = new TriangleSolitaireModel(8);
    game3 = new TriangleSolitaireModel(3,3);
    game4 = new TriangleSolitaireModel(6, 3, 2);
    view = new TriangleSolitaireTextView(game);
    view2 = new TriangleSolitaireTextView(game2);
    view3 = new TriangleSolitaireTextView(game3);
    view4 = new TriangleSolitaireTextView(game4);
  }

  /**
   * test for Triangle SolitaireModel default constructor.
   */
  @Test
  public void triangleDefaultConstructor() {
    assertEquals(view.toString(), "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O");
  }

  /**
   * test for Triangle SolitaireModel arm constructor.
   */
  @Test
  public void triangleArmConstructor() {
    assertEquals(view2.toString(), "       _\n" +
            "      O O\n" +
            "     O O O\n" +
            "    O O O O\n" +
            "   O O O O O\n" +
            "  O O O O O O\n" +
            " O O O O O O O\n" +
            "O O O O O O O O");
  }

  /**
   * test for Triangle SolitaireModel x y constructor.
   */
  @Test
  public void triangleXYConstructor() {
    assertEquals(view3.toString(), "    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O _\n" +
            "O O O O O");
  }

  /**
   * test for Triangle SolitaireModel arm x y constructor.
   */
  @Test
  public void triangleArmXYConstructor() {
    assertEquals(view4.toString(), "     O\n" +
            "    O O\n" +
            "   O O O\n" +
            "  O O _ O\n" +
            " O O O O O\n" +
            "O O O O O O");
  }

  /**
   * test for Triangle SolitaireModel invalid arm constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void triangleInvalidArmConstructor() {
    try {
      TriangleSolitaireModel t = new TriangleSolitaireModel(-3);
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "arm must be positive");
    }
    TriangleSolitaireModel t2 = new TriangleSolitaireModel(0);
  }

  /**
   * test for Triangle SolitaireModel invalid x y constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void triangleInvalidXYConstructor() {
    try {
      TriangleSolitaireModel t = new TriangleSolitaireModel(-3, 1);
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "invalid empty slot!");
    }
    TriangleSolitaireModel t2 = new TriangleSolitaireModel(3, -1);
  }

  /**
   * test for Triangle SolitaireModel invalid arm x y constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void triangleInvalidArmXYConstructor() {
    try {
      TriangleSolitaireModel t = new TriangleSolitaireModel(-1, -3, 1);
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "arm must be positive!");
    }
    try {
      TriangleSolitaireModel t = new TriangleSolitaireModel(3, -3, 1);
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "invalid empty slot!");
    }
    TriangleSolitaireModel t2 = new TriangleSolitaireModel(5, 3, -1);
  }

  /**
   * test for board size.
   */
  @Test
  public void testGetBoardSize() {
    assertEquals(game.getBoardSize(), 5);
    assertEquals(game2.getBoardSize(), 8);
    assertEquals(game3.getBoardSize(), 5);
    assertEquals(game4.getBoardSize(), 6);
  }

  /**
   * test for game over.
   */
  @Test
  public void testGameOver() {
    assertFalse(game.isGameOver());
    assertFalse(game2.isGameOver());
    assertFalse(game3.isGameOver());
    assertFalse(game4.isGameOver());
    TriangleSolitaireModel t = new TriangleSolitaireModel(3);
    t.move(2, 0, 0,0);
    t.move(2, 2, 2, 0);
    t.move(0,0,2,2);
    assertTrue(t.isGameOver());
  }

  /**
   * test for valid get slot at.
   */
  @Test
  public void testValidGetSlotAt() {
    assertEquals(game.getSlotAt(0,0), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(game.getSlotAt(1,0), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game.getSlotAt(2,2), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(game.getSlotAt(3,3), MarbleSolitaireModelState.SlotState.Marble);
  }

  /**
   * test for invalid get slot at.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetSlotAt() {
    try {
      game.getSlotAt(0,-1);
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "invalid slot!");
    }
    assertEquals(game.getSlotAt(1, 2), MarbleSolitaireModelState.SlotState.Invalid);
    game.getSlotAt(-1, 0);
  }

  /**
   * test for get score.
   */
  @Test
  public void testGetScore() {
    assertEquals(game.getScore(), 14);
    assertEquals(game2.getScore(), 35);
    assertEquals(game3.getScore(), 14);
    assertEquals(game4.getScore(), 20);
    game.move(2,0,0,0);
    assertEquals(game.getScore(), 13);
  }

  /**
   * test for valid left up move.
   */
  @Test
  public void triangleMoveLeftUp() {
    TriangleSolitaireModel t = new TriangleSolitaireModel(7, 4, 2);
    TriangleSolitaireTextView tv = new TriangleSolitaireTextView(t);
    t.move(6,4,4,2);
    assertEquals(tv.toString(), "      O\n" +
            "     O O\n" +
            "    O O O\n" +
            "   O O O O\n" +
            "  O O O O O\n" +
            " O O O _ O O\n" +
            "O O O O _ O O");
  }

  /**
   * test for valid left down move.
   */
  @Test
  public void triangleMoveLeftDown() {
    TriangleSolitaireModel t = new TriangleSolitaireModel(7, 4, 2);
    TriangleSolitaireTextView tv = new TriangleSolitaireTextView(t);
    t.move(2,2,4,2);
    assertEquals(tv.toString(), "      O\n" +
            "     O O\n" +
            "    O O _\n" +
            "   O O _ O\n" +
            "  O O O O O\n" +
            " O O O O O O\n" +
            "O O O O O O O");
  }

  /**
   * test for valid left move.
   */
  @Test
  public void triangleMoveLeft() {
    TriangleSolitaireModel t = new TriangleSolitaireModel(7, 4, 2);
    TriangleSolitaireTextView tv = new TriangleSolitaireTextView(t);
    t.move(4,4,4,2);
    assertEquals(tv.toString(), "      O\n" +
            "     O O\n" +
            "    O O O\n" +
            "   O O O O\n" +
            "  O O O _ _\n" +
            " O O O O O O\n" +
            "O O O O O O O");
  }

  /**
   * test for valid right up move.
   */
  @Test
  public void triangleMoveRightUp() {
    TriangleSolitaireModel t = new TriangleSolitaireModel(7, 4, 2);
    TriangleSolitaireTextView tv = new TriangleSolitaireTextView(t);
    t.move(6,2,4,2);
    assertEquals(tv.toString(), "      O\n" +
            "     O O\n" +
            "    O O O\n" +
            "   O O O O\n" +
            "  O O O O O\n" +
            " O O _ O O O\n" +
            "O O _ O O O O");
  }

  /**
   * test for valid right down move.
   */
  @Test
  public void triangleMoveRightDown() {
    TriangleSolitaireModel t = new TriangleSolitaireModel(7, 4, 2);
    TriangleSolitaireTextView tv = new TriangleSolitaireTextView(t);
    t.move(2,0,4,2);
    assertEquals(tv.toString(), "      O\n" +
            "     O O\n" +
            "    _ O O\n" +
            "   O _ O O\n" +
            "  O O O O O\n" +
            " O O O O O O\n" +
            "O O O O O O O");
  }

  /**
   * test for valid right move.
   */
  @Test
  public void triangleMoveRight() {
    TriangleSolitaireModel t = new TriangleSolitaireModel(7, 4, 2);
    TriangleSolitaireTextView tv = new TriangleSolitaireTextView(t);
    t.move(4,0,4,2);
    assertEquals(tv.toString(), "      O\n" +
            "     O O\n" +
            "    O O O\n" +
            "   O O O O\n" +
            "  _ _ O O O\n" +
            " O O O O O O\n" +
            "O O O O O O O");
  }

  /**
   * test for invalid left up move.
   */
  @Test(expected = IllegalArgumentException.class)
  public void triangleInvalidMoveLeftUp() {
    TriangleSolitaireModel t = new TriangleSolitaireModel(7, 4, 2);
    TriangleSolitaireTextView tv = new TriangleSolitaireTextView(t);
    try {
      t.move(6,3, 4,2);
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "cannot make moves longer than two units");
    }
    t.move(5,4,4,2);
  }

  /**
   * test for invalid left down move.
   */
  @Test(expected = IllegalArgumentException.class)
  public void triangleInvalidMoveLeftDown() {
    TriangleSolitaireModel t = new TriangleSolitaireModel(7, 4, 2);
    TriangleSolitaireTextView tv = new TriangleSolitaireTextView(t);
    try {
      t.move(1,1, 4,2);
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "cannot make moves longer than two units");
    }
    t.move(2,3,4,2);
  }

  /**
   * test for invalid left move.
   */
  @Test(expected = IllegalArgumentException.class)
  public void triangleInvalidMoveLeft() {
    TriangleSolitaireModel t = new TriangleSolitaireModel(7, 4, 2);
    TriangleSolitaireTextView tv = new TriangleSolitaireTextView(t);
    try {
      t.move(4,3, 4,2);
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "cannot move over a empty marble!");
    }
    t.move(4,3,4,2);
  }

  /**
   * test for invalid right up move.
   */
  @Test(expected = IllegalArgumentException.class)
  public void triangleInvalidMoveRightUp() {
    TriangleSolitaireModel t = new TriangleSolitaireModel(7, 4, 2);
    TriangleSolitaireTextView tv = new TriangleSolitaireTextView(t);
    try {
      t.move(6,3, 4,2);
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "cannot make moves longer than two units");
    }
    t.move(6,1,4,2);
  }

  /**
   * test for invalid right down move.
   */
  @Test(expected = IllegalArgumentException.class)
  public void triangleInvalidMoveRightDown() {
    TriangleSolitaireModel t = new TriangleSolitaireModel(7, 4, 2);
    TriangleSolitaireTextView tv = new TriangleSolitaireTextView(t);
    try {
      t.move(2,1, 4,2);
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "cannot make moves longer than two units");
    }
    t.move(1,1,4,2);
  }

  /**
   * test for invalid right move.
   */
  @Test(expected = IllegalArgumentException.class)
  public void triangleInvalidMoveRight() {
    TriangleSolitaireModel t = new TriangleSolitaireModel(7, 4, 2);
    TriangleSolitaireTextView tv = new TriangleSolitaireTextView(t);
    try {
      t.move(4,1, 4,2);
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "cannot move over a empty marble!");
    }
    t.move(4,1,4,2);
  }
}
