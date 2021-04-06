class Solution {
  public int minOperations(int n) {
    if (n % 2 == 0) {
      return n * n / 4;
    } else {
      return n / 2 * (n / 2 + 1);
    }
  }
}
