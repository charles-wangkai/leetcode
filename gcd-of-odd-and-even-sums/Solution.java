class Solution {
  public int gcdOfOddEvenSums(int n) {
    return gcd(computeArithmeticProgressionSum(1, 2, n), computeArithmeticProgressionSum(2, 2, n));
  }

  int computeArithmeticProgressionSum(int a1, int d, int n) {
    return n * a1 + n * (n - 1) * d / 2;
  }

  int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}