class Solution {
  public boolean judgeSquareSum(int c) {
    for (int i = 0; 2L * i * i <= c; ++i) {
      if (isSquare(c - i * i)) {
        return true;
      }
    }

    return false;
  }

  boolean isSquare(int n) {
    int root = (int) Math.round(Math.sqrt(n));

    return root * root == n;
  }
}
