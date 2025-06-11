/**
 * interface to represent Fraction.
 */
public interface Fraction {
  //adds two fraction objects
  Fraction add(Fraction other);

  //adds a fraction with another given as numerator and
  //denominator, should throw an exception if the fraction is negative
  Fraction add(int numerator, int denominator);

  //returns the decimal value of a fraction,
  // rounded to the given number of places:
  double getDecimalValue(int places);
}
