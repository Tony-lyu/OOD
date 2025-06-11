package spreadsheet;

import java.util.Scanner;

/**
 * hi.
 */
public class BetterController extends SpreadSheetController {
  /**
   * Create a controller to work with the specified sheet (model),
   * readable (to take inputs) and appendable (to transmit output).
   *
   * @param sheet      the sheet to work with (the model)
   * @param readable   the Readable object for inputs
   * @param appendable the Appendable objects to transmit any output
   */
  public BetterController(SpreadSheet sheet, Readable readable, Appendable appendable) {
    super(sheet, readable, appendable);
  }

  @Override
  protected void processCommand(String userInstruction, Scanner sc, SpreadSheet sheet) {
    if (userInstruction.equals("bulk-assign-value")) {
      int x1 = sc.nextInt();
      int y1 = sc.nextInt() - 1;
      int x2 = sc.nextInt();
      int y2 = sc.nextInt() - 1;
      double value = sc.nextDouble();
      Commands bulk = new BulkAssignMacro(x1, y1, x2, y2, value);
      bulk.macro(sheet);
    }
    else {
      super.processCommand(userInstruction, sc, sheet);
    }
  }

  @Override
  protected void printMenu() throws IllegalStateException {
    writeMessage("Supported user instructions are: " + System.lineSeparator());
    writeMessage("assign-value row-num col-num value (set a cell to a value)"
            + System.lineSeparator());
    writeMessage("print-value row-num col-num (print the value at a given cell)"
            + System.lineSeparator());
    writeMessage("menu (Print supported instruction list)" + System.lineSeparator());
    writeMessage("bulk-assign-value row1-num col1-num row2-num col2-num value "
            + System.lineSeparator());
    writeMessage("q or quit (quit the program) " + System.lineSeparator());
  }

}
