class Solution {
  public boolean canMeasureWater(int x, int y, int target) {
    return target <= x + y && target % gcd(x, y) == 0;
  }

  int gcd(int a, int b) {
    return (b == 0) ? a : gcd(b, a % b);
  }
}
