class Solution {
  public String getSmallestString(int n, int k) {
    StringBuilder result = new StringBuilder();
    for (int i = n; i >= 1; --i) {
      int value = Math.max(1, k - 26 * (i - 1));
      result.append((char) (value + 'a' - 1));

      k -= value;
    }

    return result.toString();
  }
}
