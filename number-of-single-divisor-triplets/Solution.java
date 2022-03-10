class Solution {
  static final int LIMIT = 100;

  public long singleDivisorTriplet(int[] nums) {
    int[] counts = new int[LIMIT + 1];
    for (int num : nums) {
      ++counts[num];
    }

    long result = 0;
    for (int i = 1; i <= LIMIT; ++i) {
      for (int j = i; j <= LIMIT; ++j) {
        for (int k = j; k <= LIMIT; ++k) {
          if (check(i, j, k)) {
            if (i == k) {
              result += counts[i] * (counts[i] - 1L) * (counts[i] - 2);
            } else if (j == i) {
              result += 3L * counts[j] * (counts[j] - 1) * counts[k];
            } else if (j == k) {
              result += 3L * counts[j] * (counts[j] - 1) * counts[i];
            } else {
              result += 6L * counts[i] * counts[j] * counts[k];
            }
          }
        }
      }
    }

    return result;
  }

  boolean check(int a, int b, int c) {
    return (((a + b + c) % a == 0) ? 1 : 0)
            + (((a + b + c) % b == 0) ? 1 : 0)
            + (((a + b + c) % c == 0) ? 1 : 0)
        == 1;
  }
}