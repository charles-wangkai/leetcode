import java.util.Arrays;

class Solution {
  public int findGCD(int[] nums) {
    return gcd(Arrays.stream(nums).min().getAsInt(), Arrays.stream(nums).max().getAsInt());
  }

  int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}
