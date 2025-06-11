/**
 * Decorator for the PillCounter, it's an default general class for other decorators to add
 * features to the PillCounter class.
 */
public class PillDecorator implements PillCounter {
  protected PillCounter pillCounter;


  /**
   * Default constructor for PillDecorator class.
   * @param pillCounter the pill counter to be operated
   */
  public PillDecorator(PillCounter pillCounter) {
    this.pillCounter = pillCounter;
  }

  @Override
  public void addPill(int count) {
    pillCounter.addPill(count);
  }

  @Override
  public void removePill() {
    pillCounter.removePill();
  }

  @Override
  public void reset() {
    pillCounter.reset();
  }

  @Override
  public int getPillCount() {
    return pillCounter.getPillCount();
  }
}
