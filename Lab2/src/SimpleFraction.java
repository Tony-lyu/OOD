/**
 * to represent A SimpleFraction with an Integer numerator and an Integer denominator.
 */
public class SimpleFraction implements Fraction {
  int numerator;
  int denominator;

  /**
   * SimpleFraction constructor, takes in an int numerator and an int denominator.
   * @param numerator int to represent numerator of a SimpleFraction.
   * @param denominator int to represent denominator of a SimpleFraction.
   */
  public SimpleFraction(int numerator, int denominator) {
    if (numerator * denominator < 0) {
      throw new IllegalArgumentException("Fraction is negative!");
    }
    if (denominator == 0) {
      throw new IllegalArgumentException("Denominator cannot be zero!");
    }
    this.numerator = numerator;
    this.denominator = denominator;

  }

  /**
   * adds two Fraction objects.
   * @param other a Fraction object to be added.
   * @return returns a new Fraction
   */
  public Fraction add(Fraction other) {
    return other.add(this.numerator, this.denominator);
  }

  /**
   * adds a fraction with another given as numerator and denominator.
   * @param numerator int
   * @param denominator int
   * @return Fraction
   */
  public Fraction add(int numerator, int denominator) {
    if (numerator * denominator < 0) {
      throw new IllegalArgumentException("Fraction is negative!");
    }
    else if (denominator == 0) {
      throw new IllegalArgumentException("Denominator cannot be zero!");
    }
    else if (denominator == this.denominator) {
      return new SimpleFraction(numerator + this.numerator , denominator);
    }
    else {
      return new SimpleFraction((Math.abs(numerator * this.denominator)
              + Math.abs(this.numerator * denominator)), Math.abs(denominator * this.denominator));
    }
  }

  /**
   * returns the decimal value of a fraction, rounded to the given number of places.
   * @param places int
   * @return double Double
   */
  public double getDecimalValue(int places) {
    return Math.round(((double) this.numerator / (double) this.denominator) * Math.pow(10, places))
            / Math.pow(10, places);
  }


  /**
   * outputs Fraction in a String.
   * @return String
   */
  public String toString() {
    return (Math.abs(this.numerator)) + "/"
            + (Math.abs(this.denominator));
  }
}
