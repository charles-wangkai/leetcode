class Solution {
  public String fractionAddition(String expression) {
    Rational result = new Rational(0, 1);

    int beginIndex = 0;
    for (int i = 1; i <= expression.length(); ++i) {
      if (i == expression.length() || expression.charAt(i) == '+' || expression.charAt(i) == '-') {
        String term = expression.substring(beginIndex, i);
        int divisionIndex = term.indexOf('/');
        int numerator = Integer.parseInt(term.substring(0, divisionIndex));
        int denominator = Integer.parseInt(term.substring(divisionIndex + 1));

        result = result.add(new Rational(numerator, denominator));

        beginIndex = i;
      }
    }

    return "%d/%d".formatted(result.numerator, result.denominator);
  }
}

class Rational {
  int numerator;
  int denominator;

  Rational(int numerator, int denominator) {
    int g = gcd(Math.abs(numerator), denominator);
    this.numerator = numerator / g;
    this.denominator = denominator / g;
  }

  int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }

  Rational add(Rational other) {
    return new Rational(
        numerator * other.denominator + other.numerator * denominator,
        denominator * other.denominator);
  }
}