class Solution {
  public long maxPairStrength(int[] nums) {
    long result = -1;
    for (int i = 0; i < nums.length; ++i) {
      for (int j = i + 1; j < nums.length; ++j) {
        int g = gcd(nums[i], nums[j]);

        result = Math.max(result, (long) (nums[i] / g) * (nums[j] / g));
      }
    }

    return result;
  }

  int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}