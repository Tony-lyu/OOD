package spreadsheet;

import java.io.InputStreamReader;

/**
 * Program that contains main to run the Spread Sheet.
 */
public class SpreadSheetProgram {

  /**
   * Main method to run the program.
   *
   * @param args user input
   */
  public static void main(String []args) {
    BetterSheet model = new BetterSheet();
    Readable rd = new InputStreamReader(System.in);
    Appendable ap = System.out;
    SpreadSheetController controller = new SpreadSheetController(model,rd,ap);
    controller.programGo();
  }
}
