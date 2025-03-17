import java.util.HashSet;
import java.util.Set;

class Solution {
  public int totalNumbers(int[] digits) {
    Set<Integer> values = new HashSet<>();
    for (int i = 0; i < digits.length; ++i) {
      if (digits[i] != 0) {
        for (int j = 0; j < digits.length; ++j) {
          if (j != i) {
            for (int k = 0; k < digits.length; ++k) {
              if (k != i && k != j && digits[k] % 2 == 0) {
                values.add(digits[i] * 100 + digits[j] * 10 + digits[k]);
              }
            }
          }
        }
      }
    }

    return values.size();
  }
}