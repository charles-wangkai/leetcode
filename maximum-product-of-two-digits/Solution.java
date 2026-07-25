class Solution {
  public int maxProduct(int n) {
    int[] digits = String.valueOf(n).chars().map(c -> c - '0').toArray();

    int result = -1;
    for (int i = 0; i < digits.length; ++i) {
      for (int j = i + 1; j < digits.length; ++j) {
        result = Math.max(result, digits[i] * digits[j]);
      }
    }

    return result;
  }
}