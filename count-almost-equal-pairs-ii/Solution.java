import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
  static final int DIGIT_NUM = 7;

  public int countPairs(int[] nums) {
    int result = 0;
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int num : nums) {
      int[] digits = toDigits(num);
      Set<Integer> almostEquals = new HashSet<>();
      for (int i = 0; i < digits.length; ++i) {
        for (int j = 0; j < digits.length; ++j) {
          for (int p = 0; p < digits.length; ++p) {
            for (int q = 0; q < digits.length; ++q) {
              swap(digits, i, j);
              swap(digits, p, q);
              almostEquals.add(toValue(digits));
              swap(digits, p, q);
              swap(digits, i, j);
            }
          }
        }
      }

      for (int almostEqual : almostEquals) {
        result += valueToCount.getOrDefault(almostEqual, 0);
      }

      valueToCount.put(num, valueToCount.getOrDefault(num, 0) + 1);
    }

    return result;
  }

  int[] toDigits(int value) {
    int[] result = new int[DIGIT_NUM];
    for (int i = result.length - 1; i >= 0; --i) {
      result[i] = value % 10;
      value /= 10;
    }

    return result;
  }

  int toValue(int[] digits) {
    int result = 0;
    for (int digit : digits) {
      result = result * 10 + digit;
    }

    return result;
  }

  void swap(int[] digits, int index1, int index2) {
    int temp = digits[index1];
    digits[index1] = digits[index2];
    digits[index2] = temp;
  }
}