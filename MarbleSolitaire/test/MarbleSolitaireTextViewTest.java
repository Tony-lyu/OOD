import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for EnglishSolitaireView.
 */
public class MarbleSolitaireTextViewTest {
  EnglishSolitaireModel game = new EnglishSolitaireModel();
  EnglishSolitaireModel game2 = new EnglishSolitaireModel(5);
  EnglishSolitaireModel game3 = new EnglishSolitaireModel(3, 4);
  MarbleSolitaireTextView view = new MarbleSolitaireTextView(game);
  MarbleSolitaireTextView view2 = new MarbleSolitaireTextView(game2);
  MarbleSolitaireTextView view3 = new MarbleSolitaireTextView(game3);

  /**
   * testing constructor for textview.
   */
  @Test
  public void testEnglishSolitaireView() {
    assertEquals(game.arm, 3);
    assertEquals(game2.arm, 5);
    assertTrue(game.getSlotAt(3, 5).equals(MarbleSolitaireModelState.SlotState.Marble));
    assertFalse(game.getSlotAt(4, 4).equals(MarbleSolitaireModelState.SlotState.Empty));
    try {
      MarbleSolitaireTextView tv = new MarbleSolitaireTextView(null);
    }
    catch (IllegalArgumentException error) {
      assertEquals(error.getMessage(), "cannot have a null game!");
    }
  }

  /**
   * Test the string representation of the game board.
   */
  @Test
  public void testToString() {
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
  }

  /**
   * Testing render board with illegal moves.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRenderBoardWithIllegalMoves() {
    Readable read;
    read = new InputStreamReader(System.in);
    MarbleSolitaireControllerImpl mc = new MarbleSolitaireControllerImpl(game, view, read);
    game.move(0, 1, 3, 3);
    try {
      view.renderBoard();
    }
    catch (IOException e) {
      throw new IllegalStateException("cannot render board");
    }
    assertEquals(view.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O");
    game.move(0, -1, 3, 3);
    try {
      view.renderBoard();
    }
    catch (IOException e) {
      throw new IllegalStateException("cannot render board");
    }
    assertEquals(view.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O");
    game.move(0, 0, -3, -3);
    try {
      view.renderBoard();
    }
    catch (IOException e) {
      throw new IllegalStateException("cannot render board");
    }
    assertEquals(view.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O");
  }

  /**
   * Testing render board with IOException.
   */
  @Test(expected = IOException.class)
  public void testRenderBoardIOException() throws IOException {
    Appendable ap = new MockAppendable();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(game, ap);
    view.renderBoard();
  }

  /**
   * Testing render board default functionality.
   */
  @Test
  public void testRenderBoard() {
    Readable read;
    read = new InputStreamReader(System.in);
    MarbleSolitaireControllerImpl mc = new MarbleSolitaireControllerImpl(game, view, read);
    game.move(3, 1, 3, 3);
    try {
      view.renderBoard();
    }
    catch (IOException e) {
      throw new IllegalStateException("cannot render board");
    }
    assertEquals(view.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O");
    game.move(1, 2, 3, 2);
    try {
      view.renderBoard();
    }
    catch (IOException e) {
      throw new IllegalStateException("cannot render board");
    }
    assertEquals(view.toString(), "    O O O\n" +
            "    _ O O\n" +
            "O O _ O O O O\n" +
            "O _ O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O");
  }

  /**
   * Testing render message default functionality.
   */
  @Test
  public void testRenderMessage() throws IOException {
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(game, ap);
    view.renderMessage("some message");
    assertEquals(ap.toString(), "some message");
    view.renderMessage("some message");
    assertEquals(ap.toString(), "some messagesome message");
    view.renderMessage("\nsome message");
    assertEquals(ap.toString(), "some messagesome message\nsome message");
  }

  /**
   * Testing render message with IOException.
   */
  @Test(expected = IOException.class)
  public void testTenderMessageIOException() throws IOException {
    Appendable ap = new MockAppendable();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(game, ap);
    view.renderMessage("message");
    view.renderMessage("a string message");
    view.renderMessage("10934");
  }
}