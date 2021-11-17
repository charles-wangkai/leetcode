class Solution {
  public int uniquePaths(int m, int n) {
    return C(m + n - 2, m - 1);
  }

  int C(int x, int r) {
    if (x - r < r) {
      return C(x, x - r);
    }

    int result = 1;
    for (int i = 0; i < r; ++i) {
      result = (int) ((long) result * (x - i) / (i + 1));
    }

    return result;
  }
}
