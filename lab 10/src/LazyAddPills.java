/**
 * The solution class that resolves the slow-adding issue.
 */
public class LazyAddPills extends PillDecorator {
  private int num;

  /**
   * default constructor of this decorator.
   *
   * @param pillCounter the pillCounter to be operated
   */
  public LazyAddPills(PillCounter pillCounter) {
    super(pillCounter);
  }

  @Override
  public void addPill(int count) {
    num += count;
  }

  @Override
  public void removePill() {
    num--;
  }

  @Override
  public void reset() {
    pillCounter.addPill(num);
    num = 0;
    pillCounter.reset();
  }

  @Override
  public int getPillCount() {
    pillCounter.addPill(num);
    num = 0;
    return pillCounter.getPillCount();
  }
}
