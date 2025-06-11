import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;

/**
 * This is a test class for Marble Solitaire Controller Implementation.
 */
public class MarbleSolitaireControllerImplTest {

  /**
   * test for valid default constructor for controller.
   */
  @Test
  public void ControllerConstructor() {
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    EuropeanSolitaireModel m = new EuropeanSolitaireModel();
    TriangleSolitaireModel tm = new TriangleSolitaireModel();
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, ap);
    MarbleSolitaireTextView v = new MarbleSolitaireTextView(m, ap);
    TriangleSolitaireTextView tv = new TriangleSolitaireTextView(tm, ap);
    Readable read = new InputStreamReader(System.in);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, read);
    MarbleSolitaireControllerImpl c2 = new MarbleSolitaireControllerImpl(m, v, read);
    MarbleSolitaireControllerImpl c3 = new MarbleSolitaireControllerImpl(tm, tv, read);
    assertEquals(model.getScore(), 32);
    try {
      view.renderMessage("s");
    }
    catch (IOException e) {
      throw new IllegalStateException("");
    }
    assertEquals(ap.toString(), "s");
  }

  /**
   * test for controller error where model is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void ControllerConstructorNullEnglishModel() {
    EnglishSolitaireModel model = null;
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model);
    Readable read = new InputStreamReader(System.in);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, read);
  }

  /**
   * test for controller error where model is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void ControllerConstructorNullEuropeanModel() {
    EuropeanSolitaireModel model = null;
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model);
    Readable read = new InputStreamReader(System.in);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, read);
  }

  /**
   * test for controller error where model is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void ControllerConstructorNullTriangleModel() {
    TriangleSolitaireModel model = null;
    TriangleSolitaireTextView view = new TriangleSolitaireTextView(model);
    Readable read = new InputStreamReader(System.in);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, read);
  }

  /**
   * test for controller error where view is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void ControllerConstructorNullMarbleView() {
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(null);
    Readable read = new InputStreamReader(System.in);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, read);
  }

  /**
   * test for controller error where view is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void ControllerConstructorNullTriangleView() {
    TriangleSolitaireModel model = new TriangleSolitaireModel();
    TriangleSolitaireTextView view = new TriangleSolitaireTextView(null);
    Readable read = new InputStreamReader(System.in);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, read);
  }

  /**
   * test for controller error where readable object is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void ControllerConstructorNullRead() {
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model);
    Readable read = null;
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, read);
  }

  /**
   * test for play game after one valid move.
   */
  @Test
  public void playEnglishGameQuitOnValidMove() {
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, ap);
    InputStream in = new ByteArrayInputStream("4 2 4 4 q".getBytes());
    Readable read = new InputStreamReader(in);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, read);
    controller.playGame();
    assertEquals(ap.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31");
  }

  /**
   * test for play game after one valid move.
   */
  @Test
  public void playEuropeanGameQuitOnValidMove() {
    EuropeanSolitaireModel model = new EuropeanSolitaireModel();
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, ap);
    InputStream in = new ByteArrayInputStream("4 2 4 4 q".getBytes());
    Readable read = new InputStreamReader(in);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, read);
    controller.playGame();
    assertEquals(ap.toString(), "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35");
  }

  /**
   * test for play game after one valid move.
   */
  @Test
  public void playTriangleGameQuitOnValidMove() {
    TriangleSolitaireModel model = new TriangleSolitaireModel();
    Appendable ap = new StringBuilder();
    TriangleSolitaireTextView view = new TriangleSolitaireTextView(model, ap);
    InputStream in = new ByteArrayInputStream("3 1 1 1 q".getBytes());
    Readable read = new InputStreamReader(in);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, read);
    controller.playGame();
    assertEquals(ap.toString(), "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13");
  }

  /**
   * test for play game after one invalid move.
   */
  @Test
  public void playEnglishGameQuitOnInvalidMove() {
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, ap);
    InputStream in = new ByteArrayInputStream("4 2 4 3 q".getBytes());
    Readable read = new InputStreamReader(in);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, read);
    controller.playGame();
    assertEquals(ap.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32Invalid move. Play again.    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32");
  }

  /**
   * test for play game after one invalid move.
   */
  @Test
  public void playEuropeanGameQuitOnInvalidMove() {
    EuropeanSolitaireModel model = new EuropeanSolitaireModel();
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, ap);
    InputStream in = new ByteArrayInputStream("4 2 4 3 q".getBytes());
    Readable read = new InputStreamReader(in);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, read);
    controller.playGame();
    assertEquals(ap.toString(), "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36Invalid move. Play again.    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36");
  }

  /**
   * test for play game after one invalid move.
   */
  @Test
  public void playTriangleGameQuitOnInvalidMove() {
    TriangleSolitaireModel model = new TriangleSolitaireModel();
    Appendable ap = new StringBuilder();
    TriangleSolitaireTextView view = new TriangleSolitaireTextView(model, ap);
    InputStream in = new ByteArrayInputStream("4 2 4 3 q".getBytes());
    Readable read = new InputStreamReader(in);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, read);
    controller.playGame();
    assertEquals(ap.toString(), "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14Invalid move. Play again.    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14");
  }

  /**
   * test for play game for invalid input.
   */
  @Test
  public void playGameInvalidSpecialCharInput() {
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, ap);
    InputStream in = new ByteArrayInputStream("= @ # ! q".getBytes());
    Readable read = new InputStreamReader(in);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, read);
    controller.playGame();
    assertEquals(ap.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid input, try again!\n" +
            "Invalid input, try again!\n" +
            "Invalid input, try again!\n" +
            "Invalid input, try again!\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32");
  }

  /**
   * test for play game for invalid input.
   */
  @Test
  public void playGameInvalidStringInput() {
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, ap);
    InputStream in = new ByteArrayInputStream("t q".getBytes());
    Readable read = new InputStreamReader(in);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, read);
    controller.playGame();
    assertEquals(ap.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid input, try again!\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32");
  }

  /**
   * test for play game for invalid input.
   */
  @Test
  public void playGameInputOutOfRange() {
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, ap);
    InputStream in = new ByteArrayInputStream("75 2 4 4 q".getBytes());
    Readable read = new InputStreamReader(in);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, read);
    controller.playGame();
    assertEquals(ap.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32Invalid move. Play again.    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32");
  }

  /**
   * test for play game for invalid input.
   */
  @Test
  public void playGameMultipleInvalidInput() {
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, ap);
    InputStream in = new ByteArrayInputStream("3 -1 2 q".getBytes());
    Readable read = new InputStreamReader(in);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, read);
    controller.playGame();
    assertEquals(ap.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid input, try again!\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32");
  }

  /**
   * test for play game for invalid input.
   */
  @Test
  public void playEnglishGameInvalidInput() {
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, ap);
    InputStream in = new ByteArrayInputStream("-1 q".getBytes());
    Readable read = new InputStreamReader(in);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, read);
    controller.playGame();
    assertEquals(ap.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid input, try again!\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32");
  }

  /**
   * test for play game for invalid input.
   */
  @Test
  public void playEuropeanGameInvalidInput() {
    EuropeanSolitaireModel model = new EuropeanSolitaireModel();
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, ap);
    InputStream in = new ByteArrayInputStream("-1 q".getBytes());
    Readable read = new InputStreamReader(in);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, read);
    controller.playGame();
    assertEquals(ap.toString(), "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Invalid input, try again!\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36");
  }

  /**
   * test for play game for invalid input.
   */
  @Test
  public void playTriangleGameInvalidInput() {
    TriangleSolitaireModel model = new TriangleSolitaireModel();
    Appendable ap = new StringBuilder();
    TriangleSolitaireTextView view = new TriangleSolitaireTextView(model, ap);
    InputStream in = new ByteArrayInputStream("-1 q".getBytes());
    Readable read = new InputStreamReader(in);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, read);
    controller.playGame();
    assertEquals(ap.toString(), "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Invalid input, try again!\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14");
  }

  /**
   * test for play game for valid move after invalid input.
   */
  @Test
  public void playGameQuitOnThreeInput() {
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, ap);
    InputStream in = new ByteArrayInputStream("4 2 4 q".getBytes());
    Readable read = new InputStreamReader(in);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, read);
    controller.playGame();
    assertEquals(ap.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32");
  }

  /**
   * test for play game for valid move after invalid input.
   */
  @Test
  public void playEuropeanGameQuitOnThreeInput() {
    EuropeanSolitaireModel model = new EuropeanSolitaireModel();
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, ap);
    InputStream in = new ByteArrayInputStream("4 2 4 q".getBytes());
    Readable read = new InputStreamReader(in);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, read);
    controller.playGame();
    assertEquals(ap.toString(), "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36");
  }

  /**
   * test for play game for valid move after invalid input.
   */
  @Test
  public void playTriangleEuropeanGameQuitOnThreeInput() {
    TriangleSolitaireModel model = new TriangleSolitaireModel();
    Appendable ap = new StringBuilder();
    TriangleSolitaireTextView view = new TriangleSolitaireTextView(model, ap);
    InputStream in = new ByteArrayInputStream("4 2 4 q".getBytes());
    Readable read = new InputStreamReader(in);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, read);
    controller.playGame();
    assertEquals(ap.toString(), "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14");
  }

  /**
   * test for play game for valid move after invalid input.
   */
  @Test
  public void playGameQuitOnTwoInput() {
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, ap);
    InputStream in = new ByteArrayInputStream("4 2 q".getBytes());
    Readable read = new InputStreamReader(in);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, read);
    controller.playGame();
    assertEquals(ap.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32");
  }

  /**
   * test for play game for valid move after invalid input.
   */
  @Test
  public void playEuropeanGameQuitOnTwoInput() {
    EuropeanSolitaireModel model = new EuropeanSolitaireModel();
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, ap);
    InputStream in = new ByteArrayInputStream("4 2 q".getBytes());
    Readable read = new InputStreamReader(in);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, read);
    controller.playGame();
    assertEquals(ap.toString(), "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36");
  }

  /**
   * test for play game for valid move after invalid input.
   */
  @Test
  public void playTriangleGameQuitOnTwoInput() {
    TriangleSolitaireModel model = new TriangleSolitaireModel();
    Appendable ap = new StringBuilder();
    TriangleSolitaireTextView view = new TriangleSolitaireTextView(model, ap);
    InputStream in = new ByteArrayInputStream("4 2 q".getBytes());
    Readable read = new InputStreamReader(in);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, read);
    controller.playGame();
    assertEquals(ap.toString(), "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14");
  }

  /**
   * test for play game for valid move after invalid input.
   */
  @Test
  public void playGameQuitOnOneInput() {
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, ap);
    InputStream in = new ByteArrayInputStream("4 q".getBytes());
    Readable read = new InputStreamReader(in);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, read);
    controller.playGame();
    assertEquals(ap.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32");
  }

  /**
   * test for play game for valid move after invalid input.
   */
  @Test
  public void playTriangleGameQuitOnOneInput() {
    TriangleSolitaireModel model = new TriangleSolitaireModel();
    Appendable ap = new StringBuilder();
    TriangleSolitaireTextView view = new TriangleSolitaireTextView(model, ap);
    InputStream in = new ByteArrayInputStream("4 q".getBytes());
    Readable read = new InputStreamReader(in);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, read);
    controller.playGame();
    assertEquals(ap.toString(), "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14");
  }

  /**
   * test for play game for valid move after invalid input.
   */
  @Test
  public void playGameValidMoveAftInvalidInput() {
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, ap);
    InputStream in = new ByteArrayInputStream("-1 4 2 4 4 q".getBytes());
    Readable read = new InputStreamReader(in);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, read);
    controller.playGame();
    assertEquals(ap.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid input, try again!    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31");
  }

  /**
   * test for play game for default quitting scene.
   */
  @Test
  public void playGameQuitOnMultipleMoves() {
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, ap);
    InputStream in = new ByteArrayInputStream("4 2 4 4 6 3 4 3 q".getBytes());
    Readable read = new InputStreamReader(in);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, read);
    controller.playGame();
    assertEquals(ap.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "O O _ O O O O\n" +
            "    _ O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "O O _ O O O O\n" +
            "    _ O O\n" +
            "    O O O\n" +
            "Score: 30");
  }

  /**
   * test for play game for default quitting scene.
   */
  @Test
  public void playEnglishGameDefaultQuit() {
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, ap);
    InputStream in = new ByteArrayInputStream("q".getBytes());
    Readable read = new InputStreamReader(in);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, read);
    controller.playGame();
    assertEquals(ap.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32");
  }

  /**
   * test for play game for default quitting scene.
   */
  @Test
  public void playEuropeanGameDefaultQuit() {
    EuropeanSolitaireModel model = new EuropeanSolitaireModel();
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, ap);
    InputStream in = new ByteArrayInputStream("q".getBytes());
    Readable read = new InputStreamReader(in);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, read);
    controller.playGame();
    assertEquals(ap.toString(), "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36");
  }

  /**
   * test for play game for default quitting scene.
   */
  @Test
  public void playTriangleGameDefaultQuit() {
    TriangleSolitaireModel model = new TriangleSolitaireModel();
    Appendable ap = new StringBuilder();
    TriangleSolitaireTextView view = new TriangleSolitaireTextView(model, ap);
    InputStream in = new ByteArrayInputStream("q".getBytes());
    Readable read = new InputStreamReader(in);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, read);
    controller.playGame();
    assertEquals(ap.toString(), "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14");
  }

  /**
   * test for play game for correct from and incorrect to move.
   */
  @Test
  public void playGameValidFromInvalidTo() {
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, ap);
    InputStream in = new ByteArrayInputStream("4 2 3 3 4 2 4 4 q".getBytes());
    Readable read = new InputStreamReader(in);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, read);
    controller.playGame();
    assertEquals(ap.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32Invalid move. Play again.    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31");
  }

  /**
   * test for play game for default quitting scene.
   */
  @Test
  public void playEnglishGameScoreCheck() {
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, ap);
    InputStream in = new ByteArrayInputStream(("4 2 4 4 6 3 4 3 5 1 5 3 5 4 5 2 5 6 5 4 7 5 " +
            "5 5 4 5 6 5 7 3 7 5 7 5 5 5 3 3 5 3 1 3 3 3 2 5 4 5 4 5 6 5 6 5 6 3 6 3 4 " +
            "3 4 3 2 3 3 1 5 1 5 1 5 3 5 3 5 5 3 7 3 5 3 4 3 6 5 7 3 7 3 7 3 5 1 5 1 3 " +
            "1 3 3 3 3 2 3 4 3 4 3 6 3 6 5 6 5 6 5 4 5 4 3 4 2 4 4 4").getBytes());
    Readable read = new InputStreamReader(in);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, read);
    controller.playGame();
    assertEquals(model.getScore(), 1);
  }

  /**
   * test for play game for default quitting scene.
   */
  @Test
  public void playEuropeanGameScoreCheck() {
    EuropeanSolitaireModel model = new EuropeanSolitaireModel();
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, ap);
    InputStream in = new ByteArrayInputStream(("4 2 4 4 6 3 4 3 5 1 5 3 5 4 5 2 5 6 5 4 7 5 " +
            "5 5 4 5 6 5 7 3 7 5 7 5 5 5 3 3 5 3 1 3 3 3 2 5 4 5 4 5 6 5 6 5 6 3 6 3 4 " +
            "3 4 3 2 3 3 1 5 1 5 1 5 3 5 3 5 5 3 7 3 5 3 4 3 6 5 7 3 7 3 7 3 5 1 5 1 3 " +
            "1 3 3 3 3 2 3 4 3 4 3 6 3 6 5 6 5 6 5 4 5 4 3 4 2 4 4 4").getBytes());
    Readable read = new InputStreamReader(in);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, read);
    controller.playGame();
    assertEquals(model.getScore(), 5);
  }

  /**
   * test for play game for default quitting scene.
   */
  @Test
  public void playGameEndGame() {
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, ap);
    InputStream in = new ByteArrayInputStream(("4 2 4 4 6 3 4 3 5 1 5 3 5 4 5 2 5 6 5 4 7 5 " +
            "5 5 4 5 6 5 7 3 7 5 7 5 5 5 3 3 5 3 1 3 3 3 2 5 4 5 4 5 6 5 6 5 6 3 6 3 4 " +
            "3 4 3 2 3 3 1 5 1 5 1 5 3 5 3 5 5 3 7 3 5 3 4 3 6 5 7 3 7 3 7 3 5 1 5 1 3 " +
            "1 3 3 3 3 2 3 4 3 4 3 6 3 6 5 6 5 6 5 4 5 4 3 4 2 4 4 4").getBytes());
    Readable read = new InputStreamReader(in);
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, read);
    controller.playGame();
    assertEquals(ap.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "O O _ O O O O\n" +
            "    _ O O\n" +
            "    O O O\n" +
            "Score: 30    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "_ _ O O O O O\n" +
            "    _ O O\n" +
            "    O O O\n" +
            "Score: 29    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "_ O _ _ O O O\n" +
            "    _ O O\n" +
            "    O O O\n" +
            "Score: 28    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "_ O _ O _ _ O\n" +
            "    _ O O\n" +
            "    O O O\n" +
            "Score: 27    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "_ O _ O O _ O\n" +
            "    _ O _\n" +
            "    O O _\n" +
            "Score: 26    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O O _ O O\n" +
            "_ O _ O _ _ O\n" +
            "    _ O O\n" +
            "    O O _\n" +
            "Score: 25    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O O _ O O\n" +
            "_ O _ O _ _ O\n" +
            "    _ O O\n" +
            "    _ _ O\n" +
            "Score: 24    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O O _ O O\n" +
            "_ O _ O O _ O\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "Score: 23    O O O\n" +
            "    O O O\n" +
            "O O _ O O O O\n" +
            "O _ _ O _ O O\n" +
            "_ O O O O _ O\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "Score: 22    _ O O\n" +
            "    _ O O\n" +
            "O O O O O O O\n" +
            "O _ _ O _ O O\n" +
            "_ O O O O _ O\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "Score: 21    _ O O\n" +
            "    _ O _\n" +
            "O O O O _ O O\n" +
            "O _ _ O O O O\n" +
            "_ O O O O _ O\n" +
            "    _ O _\n" +
            "    _ _ _\n" +
            "Score: 20    _ O O\n" +
            "    _ O _\n" +
            "O O O O _ O O\n" +
            "O _ _ O _ O O\n" +
            "_ O O O _ _ O\n" +
            "    _ O O\n" +
            "    _ _ _\n" +
            "Score: 19    _ O O\n" +
            "    _ O _\n" +
            "O O O O _ O O\n" +
            "O _ _ O _ O O\n" +
            "_ O O O _ _ O\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "Score: 18    _ O O\n" +
            "    _ O _\n" +
            "O O O O _ O O\n" +
            "O _ O O _ O O\n" +
            "_ O _ O _ _ O\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 17    _ O O\n" +
            "    O O _\n" +
            "O O _ O _ O O\n" +
            "O _ _ O _ O O\n" +
            "_ O _ O _ _ O\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 16    _ O O\n" +
            "    O O _\n" +
            "_ O _ O _ O O\n" +
            "_ _ _ O _ O O\n" +
            "O O _ O _ _ O\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 15    _ O O\n" +
            "    O O _\n" +
            "_ O _ O _ O O\n" +
            "_ _ _ O _ O O\n" +
            "_ _ O O _ _ O\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 14    _ O O\n" +
            "    O O _\n" +
            "_ O _ O _ O O\n" +
            "_ _ _ O _ O O\n" +
            "_ _ _ _ O _ O\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 13    _ O O\n" +
            "    O O _\n" +
            "_ O _ O O _ _\n" +
            "_ _ _ O _ O O\n" +
            "_ _ _ _ O _ O\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 12    _ O O\n" +
            "    O O _\n" +
            "_ O _ _ _ O _\n" +
            "_ _ _ O _ O O\n" +
            "_ _ _ _ O _ O\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 11    _ O O\n" +
            "    O O _\n" +
            "_ O _ _ _ O O\n" +
            "_ _ _ O _ O _\n" +
            "_ _ _ _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 10    _ O O\n" +
            "    O O _\n" +
            "_ O _ _ O _ _\n" +
            "_ _ _ O _ O _\n" +
            "_ _ _ _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 9    O _ _\n" +
            "    O O _\n" +
            "_ O _ _ O _ _\n" +
            "_ _ _ O _ O _\n" +
            "_ _ _ _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 8    _ _ _\n" +
            "    _ O _\n" +
            "_ O O _ O _ _\n" +
            "_ _ _ O _ O _\n" +
            "_ _ _ _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 7    _ _ _\n" +
            "    _ O _\n" +
            "_ _ _ O O _ _\n" +
            "_ _ _ O _ O _\n" +
            "_ _ _ _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 6    _ _ _\n" +
            "    _ O _\n" +
            "_ _ _ _ _ O _\n" +
            "_ _ _ O _ O _\n" +
            "_ _ _ _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 5    _ _ _\n" +
            "    _ O _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ O _ _ _\n" +
            "_ _ _ _ O O _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 4    _ _ _\n" +
            "    _ O _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ O _ _ _\n" +
            "_ _ _ O _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 3    _ _ _\n" +
            "    _ O _\n" +
            "_ _ _ O _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 2    _ _ _\n" +
            "    _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ O _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Game over!\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ O _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ _\n" +
            "Score: 1");
  }
}