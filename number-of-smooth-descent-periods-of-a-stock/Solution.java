class Solution {
  public long getDescentPeriods(int[] prices) {
    long result = 0;
    int length = 0;
    for (int i = 0; i <= prices.length; ++i) {
      if (i != prices.length && i != 0 && prices[i] == prices[i - 1] - 1) {
        ++length;
      } else {
        result += length * (length + 1L) / 2;
        length = 1;
      }
    }

    return result;
  }
}