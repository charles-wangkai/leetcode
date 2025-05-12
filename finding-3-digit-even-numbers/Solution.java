import java.util.ArrayList;
import java.util.List;

class Solution {
  public int[] findEvenNumbers(int[] digits) {
    List<Integer> values = new ArrayList<>();
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

    return values.stream().distinct().sorted().mapToInt(Integer::intValue).toArray();
  }
}