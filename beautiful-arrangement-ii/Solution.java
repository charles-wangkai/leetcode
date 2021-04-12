class Solution {
  public int[] constructArray(int n, int k) {
    int[] result = new int[n];

    int index = 0;
    for (int i = 1; i <= n - k; ++i) {
      result[index] = i;
      ++index;
    }

    int value = n - k;
    int sign = 1;
    for (int step = k; step >= 1; --step) {
      value += step * sign;
      result[index] = value;
      ++index;

      sign *= -1;
    }

    return result;
  }
}
