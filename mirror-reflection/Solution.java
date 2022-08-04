class Solution {
  public int mirrorReflection(int p, int q) {
    int l = lcm(p, q);
    if (l / p % 2 == 0) {
      return 0;
    }
    if (l / q % 2 == 0) {
      return 2;
    }

    return 1;
  }

  int lcm(int x, int y) {
    return x / gcd(x, y) * y;
  }

  int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}
