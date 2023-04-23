import java.util.Arrays;

class Solution {
  public int minOperations(int[] nums) {
    if (Arrays.stream(nums).anyMatch(x -> x == 1)) {
      return (int) Arrays.stream(nums).filter(x -> x != 1).count();
    }

    int minOperationToOne = Integer.MAX_VALUE;
    for (int i = 0; i < nums.length; ++i) {
      int g = nums[i];
      for (int j = i + 1; j < nums.length; ++j) {
        g = gcd(g, nums[j]);
        if (g == 1) {
          minOperationToOne = Math.min(minOperationToOne, j - i);

          break;
        }
      }
    }

    return (minOperationToOne == Integer.MAX_VALUE) ? -1 : (minOperationToOne + nums.length - 1);
  }

  int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}
