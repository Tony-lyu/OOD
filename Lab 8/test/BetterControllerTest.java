import java.io.InputStreamReader;

import spreadsheet.BetterController;
import spreadsheet.SparseSpreadSheet;
import spreadsheet.SpreadSheet;

/**
 * Test for better controller.
 */
public class BetterControllerTest {
  SpreadSheet s = new SparseSpreadSheet();
  Readable read = new InputStreamReader(System.in);
  Appendable ap = new StringBuilder();
  BetterController bc = new BetterController(s, read, ap);


}
