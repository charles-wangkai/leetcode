import static java.util.Map.entry;

import java.util.Map;

class Solution {
  static final Map<Integer, Integer> DIGIT_TO_ROTATED =
      Map.ofEntries(entry(0, 0), entry(1, 1), entry(6, 9), entry(8, 8), entry(9, 6));

  public boolean confusingNumber(int n) {
    int rotated = 0;
    for (int rest = n; rest != 0; rest /= 10) {
      int digit = rest % 10;
      if (!DIGIT_TO_ROTATED.containsKey(digit)) {
        return false;
      }

      rotated = rotated * 10 + DIGIT_TO_ROTATED.get(digit);
    }

    return rotated != n;
  }
}
