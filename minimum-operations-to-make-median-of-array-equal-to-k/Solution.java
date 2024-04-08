import java.util.Arrays;

class Solution {
  public long minOperationsToMakeMedianK(int[] nums, int k) {
    Arrays.sort(nums);

    int middleIndex = nums.length / 2;
    long result = Math.abs(nums[middleIndex] - k);
    nums[middleIndex] = k;
    for (int i = middleIndex - 1; i >= 0; --i) {
      if (nums[i] > nums[i + 1]) {
        result += nums[i] - nums[i + 1];
        nums[i] = nums[i + 1];
      }
    }
    for (int i = middleIndex + 1; i < nums.length; ++i) {
      if (nums[i] < nums[i - 1]) {
        result += nums[i - 1] - nums[i];
        nums[i] = nums[i - 1];
      }
    }

    return result;
  }
}