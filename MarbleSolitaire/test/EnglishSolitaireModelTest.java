import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * tests for English Solitaire model.
 */
public class EnglishSolitaireModelTest {
  EnglishSolitaireModel game;
  EnglishSolitaireModel game2;
  EnglishSolitaireModel game3;
  MarbleSolitaireTextView view;
  MarbleSolitaireTextView view2;
  MarbleSolitaireTextView view3;

  @Before
  public void set() {
    game = new EnglishSolitaireModel();
    game2 = new EnglishSolitaireModel(5);
    game3 = new EnglishSolitaireModel(3,4);
    view = new MarbleSolitaireTextView(game);
    view2 = new MarbleSolitaireTextView(game2);
    view3 = new MarbleSolitaireTextView(game3);
  }

  /**
   * test for English SolitaireModel constructor.
   */
  @Test
  public void testEnglishSolitaireModel() throws IllegalArgumentException {
    assertEquals(view.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O");
    assertEquals(view2.toString(), "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O");
    assertEquals(view3.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O _ O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O");
    try {
      EnglishSolitaireModel sf = new EnglishSolitaireModel(-2, 5);
    }
    catch (IllegalArgumentException error) {
      assertEquals(error.getMessage(), "Invalid empty cell position (-2, 5)");
    }
    try {
      EnglishSolitaireModel sf = new EnglishSolitaireModel(1);
    }
    catch (IllegalArgumentException error) {
      assertEquals(error.getMessage(), "Arm thickness is not an odd positive integer!");
    }
    try {
      EnglishSolitaireModel sf = new EnglishSolitaireModel(2);
    }
    catch (IllegalArgumentException error) {
      assertEquals(error.getMessage(), "Arm thickness is not an odd positive integer!");
    }
    try {
      EnglishSolitaireModel sf = new EnglishSolitaireModel(-1);
    }
    catch (IllegalArgumentException error) {
      assertEquals(error.getMessage(), "Arm thickness is not an odd positive integer!");
    }
    try {
      EnglishSolitaireModel sf = new EnglishSolitaireModel(3, -1, -2);
    }
    catch (IllegalArgumentException error) {
      assertEquals(error.getMessage(), "Invalid empty cell position (-1, -2)");
    }
    try {
      EnglishSolitaireModel sf = new EnglishSolitaireModel(3, -1, 2);
    }
    catch (IllegalArgumentException error) {
      assertEquals(error.getMessage(), "Invalid empty cell position (-1, 2)");
    }
    try {
      EnglishSolitaireModel sf = new EnglishSolitaireModel(3, 1, -2);
    }
    catch (IllegalArgumentException error) {
      assertEquals(error.getMessage(), "Invalid empty cell position (1, -2)");
    }
    try {
      EnglishSolitaireModel sf = new EnglishSolitaireModel(3, 8, 3);
    }
    catch (IllegalArgumentException error) {
      assertEquals(error.getMessage(), "Invalid empty cell position (8, 3)");
    }
    try {
      EnglishSolitaireModel sf = new EnglishSolitaireModel(3, 3, 10);
    }
    catch (IllegalArgumentException error) {
      assertEquals(error.getMessage(), "Invalid empty cell position (3, 10)");
    }
    try {
      EnglishSolitaireModel sf = new EnglishSolitaireModel(2, 1, 1);
    }
    catch (IllegalArgumentException error) {
      assertEquals(error.getMessage(), "Arm thickness is not an odd positive integer!");
    }
    try {
      EnglishSolitaireModel sf = new EnglishSolitaireModel(4, 1, 1);
    }
    catch (IllegalArgumentException error) {
      assertEquals(error.getMessage(), "Invalid empty cell position (1, 1)");
    }
  }

  /**
   * test for getting index of a marble on the game board.
   */
  @Test
  public void testGetIndex() {
    assertEquals(game.getIndex(0,0), 0);
    assertEquals(game.getIndex(3,3), 24);
    try {
      game.getIndex(-1, 4);
    }
    catch (IllegalArgumentException error) {
      assertEquals(error.getMessage(), "position cannot be negative");
    }
    try {
      game.getIndex(1, -4);
    }
    catch (IllegalArgumentException error) {
      assertEquals(error.getMessage(), "position cannot be negative");
    }
    try {
      game.getIndex(-1, -4);
    }
    catch (IllegalArgumentException error) {
      assertEquals(error.getMessage(), "position cannot be negative");
    }
    try {
      game.getIndex(4, 0);
    }
    catch (IllegalArgumentException error) {
      assertEquals(error.getMessage(), "invalid position");
    }
    try {
      game.getIndex(9, 9);
    }
    catch (IllegalArgumentException error) {
      assertEquals(error.getMessage(), "invalid position");
    }
  }

  /**
   * test for making moves on the game board.
   */
  @Test
  public void testMove() {
    game.move(1,3,3,3);
    assertEquals(view.toString(),"    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O");
    game.move(2,1,2,3);
    assertEquals(view.toString(), "    O O O\n" +
            "    O _ O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O");
    game.move(2,4,2,2);
    game.move(4,4,2,4);
    game.move(6,4,4,4);
    assertEquals(view.toString(), "    O O O\n" +
            "    O _ O\n" +
            "O _ O _ O O O\n" +
            "O O O O _ O O\n" +
            "O O O O O O O\n" +
            "    O O _\n" +
            "    O O _");
    EnglishSolitaireModel sm = new EnglishSolitaireModel(0,2);
    try {
      sm.move(0,0,0,2);
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Invalid \"from\" or \"to\" position!");
    }
    sm.move(0,4,0,2);
    try {
      sm.move(0,2,0,0);
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Invalid \"from\" or \"to\" position!");
    }
    try {
      sm.move(0,4,-1,0);
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Invalid \"from\" or \"to\" position!");
    }
    try {
      sm.move(0,2,0,4);
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "must jump over a non-empty position");
    }
    try {
      sm.move(0,4,0,4);
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Cannot move to the same position");
    }

  }

  /**
   * test for determining if the game is over.
   */
  @Test
  public void testIsGameOver() {
    assertFalse(game.isGameOver());
    assertFalse(game3.isGameOver());
    for (int i = 0; i < game.marbles.size(); i ++) {
      if (game.marbles.get(i).isValid()) {
        game.marbles.get(i).emptyMarble();
      }
    }
    assertTrue(game.isGameOver());
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
   * test for getting the slat state at a given position.
   */
  @Test
  public void testGetSlotAt() {
    assertFalse(game.getSlotAt(2,1)
            == MarbleSolitaireModelState.SlotState.Invalid);
    assertFalse(game.getSlotAt(3,1)
            == MarbleSolitaireModelState.SlotState.Invalid);
    assertTrue(game.getSlotAt(1,1)
            == MarbleSolitaireModelState.SlotState.Invalid);
    assertTrue(game.getSlotAt(3,3)
            == MarbleSolitaireModelState.SlotState.Empty);
    assertTrue(game.getSlotAt(3,4)
            == MarbleSolitaireModelState.SlotState.Marble);
    try {
      game.getSlotAt(-1,0);
    }
    catch (IllegalArgumentException error) {
      assertEquals(error.getMessage(), "invalid slot!");
    }
    try {
      game.getSlotAt(1,-3);
    }
    catch (IllegalArgumentException error) {
      assertEquals(error.getMessage(), "invalid slot!");
    }
    try {
      game.getSlotAt(8,0);
    }
    catch (IllegalArgumentException error) {
      assertEquals(error.getMessage(), "invalid slot!");
    }
    try {
      game.getSlotAt(1,10);
    }
    catch (IllegalArgumentException error) {
      assertEquals(error.getMessage(), "invalid slot!");
    }
    try {
      game.getSlotAt(31,10);
    }
    catch (IllegalArgumentException error) {
      assertEquals(error.getMessage(), "invalid slot!");
    }
  }

  /**
   * test for getting the score of the current English Solitaire game.
   */
  @Test
  public void testGetScore() {
    assertEquals(game.getScore(), 32);
    assertEquals(game2.getScore(), 104);
    for (int i = 0; i < 30; i++) {
      game.marbles.get(i).emptyMarble();
    }
    for (int i = 0; i < 30; i++) {
      game2.marbles.get(i).emptyMarble();
    }
    assertEquals(game.getScore(), 11);
    assertEquals(game2.getScore(), 94);
  }
}