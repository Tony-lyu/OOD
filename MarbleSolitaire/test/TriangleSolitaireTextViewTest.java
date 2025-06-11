import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;
import static org.junit.Assert.assertEquals;

/**
 * Tests for EnglishSolitaireView.
 */
public class TriangleSolitaireTextViewTest {
  TriangleSolitaireModel game = new TriangleSolitaireModel();
  TriangleSolitaireModel game2 = new TriangleSolitaireModel(8);
  TriangleSolitaireModel game3 = new TriangleSolitaireModel(3, 2);
  TriangleSolitaireModel game4 = new TriangleSolitaireModel(6, 3, 2);
  Appendable ap = new StringBuilder();
  TriangleSolitaireTextView view = new TriangleSolitaireTextView(game);
  TriangleSolitaireTextView view2 = new TriangleSolitaireTextView(game2, ap);
  TriangleSolitaireTextView view3 = new TriangleSolitaireTextView(game3, ap);
  TriangleSolitaireTextView view4 = new TriangleSolitaireTextView(game4, ap);

  /**
   * testing default constructor for textview.
   */
  @Test
  public void triangleSolitaireViewDefaultConstructor() {
    assertEquals(view.toString(), "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O");
  }

  /**
   * testing default invalid constructor for textview.
   */
  @Test(expected = IllegalArgumentException.class)
  public void triangleSolitaireViewDefaultInvalidConstructor() {
    try {
      TriangleSolitaireTextView t = new TriangleSolitaireTextView(null);
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "cannot have a null game");
    }
    TriangleSolitaireTextView tx = new TriangleSolitaireTextView(null);
  }

  /**
   * testing constructor for textview.
   */
  @Test
  public void triangleSolitaireViewConstructor() {
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
   * testing invalid constructor for textview.
   */
  @Test(expected = IllegalArgumentException.class)
  public void triangleSolitaireViewInvalidConstructor() {
    try {
      TriangleSolitaireTextView t = new TriangleSolitaireTextView(game, null);
    }
    catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "cannot have a null game or a null destination");
    }
    TriangleSolitaireTextView t = new TriangleSolitaireTextView(null, ap);
  }

  /**
   * test for to string method.
   */
  @Test
  public void triangleSolitaireToString() {
    assertEquals(view.toString(), "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O");
    assertEquals(view2.toString(), "       _\n" +
            "      O O\n" +
            "     O O O\n" +
            "    O O O O\n" +
            "   O O O O O\n" +
            "  O O O O O O\n" +
            " O O O O O O O\n" +
            "O O O O O O O O");
    assertEquals(view3.toString(), "    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O _ O\n" +
            "O O O O O");
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
    game.move(2, 0, 0, 0);
    try {
      view.renderBoard();
    }
    catch (IOException e) {
      throw new IllegalStateException("cannot render board");
    }
    assertEquals(view.toString(), "    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O");
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
