import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import spreadsheet.MockSheet;
import spreadsheet.SparseSpreadSheet;
import spreadsheet.SpreadSheet;
import spreadsheet.SpreadSheetController;

/**
 * test class for Spread Sheet Controller.
 */
public class SpreadSheetControllerTest {

  /**
   * test to confirm the program produce the right welcome message.
   */
  @Test
  public void controllerWelcomeMessage() {
    SpreadSheet model = new SparseSpreadSheet();
    InputStream in = new ByteArrayInputStream("q".getBytes());
    Readable rd = new InputStreamReader(in);
    Appendable ap = new StringBuilder();
    SpreadSheetController controller = new SpreadSheetController(model,rd,ap);
    controller.programGo();
    Assert.assertTrue(ap.toString().contains("Welcome to the spreadsheet program!\n" +
            "Supported user instructions are: \n" +
            "assign-value row-num col-num value (set a cell to a value)\n" +
            "print-value row-num col-num (print the value at a given cell)\n" +
            "menu (Print supported instruction list)\n" +
            "q or quit (quit the program)"));
  }

  /**
   * test to confirm the program produce the right farewell message.
   */
  @Test
  public void controllerFarewellMessage() {
    SpreadSheet model = new SparseSpreadSheet();
    InputStream in = new ByteArrayInputStream("q".getBytes());
    Readable rd = new InputStreamReader(in);
    Appendable ap = new StringBuilder();
    SpreadSheetController controller = new SpreadSheetController(model,rd,ap);
    controller.programGo();
    Assert.assertTrue(ap.toString().contains("Thank you for using this program!"));
  }

  @Test
  public void controllerGet() {
    MockSheet model = new MockSheet();
    InputStream in = new ByteArrayInputStream("assign-value a 2 1.0 q".getBytes());
    Readable rd = new InputStreamReader(in);
    Appendable ap = new StringBuilder();
    SpreadSheetController controller = new SpreadSheetController(model,rd,ap);
    controller.programGo();
    Assert.assertEquals(model.toString(), "0 1 1.0");
  }

  @Test
  public void controllerBulkAssign() {
    MockSheet model = new MockSheet();
    InputStream in = new ByteArrayInputStream("bulk-assign a 1 c 3 1.0 print-value q".getBytes());
    Readable rd = new InputStreamReader(in);
    Appendable ap = new StringBuilder();
    SpreadSheetController controller = new SpreadSheetController(model,rd,ap);
    controller.programGo();
    Assert.assertEquals(model.toString(), "0 1 1.0");
  }
}
