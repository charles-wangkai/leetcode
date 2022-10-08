import java.util.Arrays;

class Solution {
  public int threeSumClosest(int[] nums, int target) {
    Arrays.sort(nums);

    Integer result = null;
    for (int i = 0; i < nums.length; ++i) {
      for (int j = i + 1, k = nums.length - 1; j < k; ) {
        int sum = nums[i] + nums[j] + nums[k];
        if (result == null || Math.abs(sum - target) < Math.abs(result - target)) {
          result = sum;
        }

        if (sum <= target) {
          ++j;
        } else {
          --k;
        }
      }
    }

    return result;
  }
}
