class Solution {
  public boolean isReachable(int targetX, int targetY) {
    return Integer.bitCount(gcd(targetX, targetY)) == 1;
  }

  int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}
