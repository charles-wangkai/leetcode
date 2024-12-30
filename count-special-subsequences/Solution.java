import java.util.HashMap;
import java.util.Map;

class Solution {
  public long numberOfSubsequences(int[] nums) {
    long result = 0;
    Map<Rational, Integer> rationalToCount = new HashMap<>();
    for (int r = 2; r < nums.length; ++r) {
      for (int s = r + 2; s < nums.length; ++s) {
        Rational rational = new Rational(nums[s], nums[r]);
        rationalToCount.put(rational, rationalToCount.getOrDefault(rational, 0) + 1);
      }
    }
    for (int q = 0; q < nums.length; ++q) {
      for (int p = 0; p <= q - 2; ++p) {
        result += rationalToCount.getOrDefault(new Rational(nums[p], nums[q]), 0);
      }

      for (int s = q + 4; s < nums.length; ++s) {
        Rational rational = new Rational(nums[s], nums[q + 2]);
        rationalToCount.put(rational, rationalToCount.get(rational) - 1);
      }
    }

    return result;
  }
}

record Rational(int numer, int denom) {
  Rational(int numer, int denom) {
    int g = gcd(numer, denom);

    this.numer = numer / g;
    this.denom = denom / g;
  }

  int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}
