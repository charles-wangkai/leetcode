import java.util.Arrays;

class Solution {
  public long gcdSum(int[] nums) {
    int max = -1;
    int[] prefixGcds = new int[nums.length];
    for (int i = 0; i < prefixGcds.length; ++i) {
      max = Math.max(max, nums[i]);
      prefixGcds[i] = gcd(nums[i], max);
    }

    Arrays.sort(prefixGcds);

    long result = 0;
    for (int i = 0, j = prefixGcds.length - 1; i < j; ++i, --j) {
      result += gcd(prefixGcds[i], prefixGcds[j]);
    }

    return result;
  }

  int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}