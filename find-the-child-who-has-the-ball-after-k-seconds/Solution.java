class Solution {
  public int numberOfChild(int n, int k) {
    int result = 0;
    int delta = 1;
    for (int i = 0; i < k; ++i) {
      result += delta;

      if (result == 0 || result == n - 1) {
        delta *= -1;
      }
    }

    return result;
  }
}