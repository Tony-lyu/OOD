import java.util.ArrayList;
import java.util.List;

/**
 * The Pill Decorator that checks why the program runs so slow.
 */
public class LoggingPillDecorator extends PillDecorator {
  private List<String> logCount = new ArrayList<>();

  /**
   * Default constructor of this pillCounter.
   *
   * @param pillCounter the pillCounter to be operated
   */
  public LoggingPillDecorator(PillCounter pillCounter) {
    super(pillCounter);
  }

  @Override
  public void addPill(int count) {
    this.logCount.add("Added" + count + "/n");
    pillCounter.addPill(count);
  }

  @Override
  public void removePill() {
    pillCounter.removePill();
  }

  @Override
  public void reset() {
    pillCounter.reset();
    this.logCount = new ArrayList<>();
  }

  @Override
  public int getPillCount() {
    return pillCounter.getPillCount();
  }

  /**
   * Prints the output messages recorded by add Pill for testing purposes.
   *
   * @return list of string output
   */
  public String printCount() {
    String result = "";
    for (int i = 0; i < logCount.size(); i++) {
      result += logCount.get(i);
    }
    return result;
  }

}
