class Solution {
  public String getSmallestString(int n, int k) {
    StringBuilder result = new StringBuilder();
    while (n != 0) {
      int value = Math.max(1, k - 26 * (n - 1));
      result.append((char) (value + 'a' - 1));

      --n;
      k -= value;
    }

    return result.toString();
  }
}
