import java.util.HashMap;
import java.util.Map;

class Solution {
  public String fractionToDecimal(int numerator, int denominator) {
    StringBuilder result = new StringBuilder();

    if (Integer.signum(numerator) * Integer.signum(denominator) == -1) {
      result.append("-");
    }

    long numer = Math.abs((long) numerator);
    long denom = Math.abs((long) denominator);
    result.append(numer / denom);
    numer %= denom;

    if (numer != 0) {
      Map<Long, Integer> remainderToIndex = new HashMap<>();
      StringBuilder fraction = new StringBuilder();
      for (int i = 0; ; ++i) {
        if (numer == 0) {
          break;
        }

        if (remainderToIndex.containsKey(numer)) {
          fraction.insert(remainderToIndex.get(numer), "(").append(")");

          break;
        }

        remainderToIndex.put(numer, i);

        numer *= 10;
        fraction.append(numer / denom);
        numer %= denom;
      }

      result.append(".").append(fraction);
    }

    return result.toString();
  }
}
