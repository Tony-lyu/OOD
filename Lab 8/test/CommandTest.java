import org.junit.Assert;
import org.junit.Test;

import spreadsheet.BulkAssignMacro;
import spreadsheet.Commands;

/**
 * Test class for Commands.
 */
public class CommandTest {

  /**
   * Test for bulk constructor.
   */
  @Test
  public void bulkConstructor() {
    Commands c = new BulkAssignMacro(2, 4, 4, 6, 3.0);
    try {
      Commands c2 = new BulkAssignMacro(2, 4, -1, -6, 3.0);
    } catch (IllegalArgumentException e) {
      Assert.assertEquals(e.getMessage(), "range cannot be smaller than zero");
    }
  }
}
