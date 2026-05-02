import static java.util.Map.entry;

import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  static final Map<Integer, Integer> DIGIT_TO_ROTATED =
      Map.ofEntries(
          entry(0, 0),
          entry(1, 1),
          entry(8, 8),
          entry(2, 5),
          entry(5, 2),
          entry(6, 9),
          entry(9, 6));

  public int rotatedDigits(int N) {
    return (int) IntStream.rangeClosed(1, N).filter(this::isGood).count();
  }

  boolean isGood(int n) {
    StringBuilder rotated = new StringBuilder();
    for (char c : String.valueOf(n).toCharArray()) {
      int digit = c - '0';
      if (!DIGIT_TO_ROTATED.containsKey(digit)) {
        return false;
      }

      rotated.append(DIGIT_TO_ROTATED.get(digit));
    }

    return Integer.parseInt(rotated.toString()) != n;
  }
}
