import java.util.Arrays;

class Solution {
  public int threeSumClosest(int[] nums, int target) {
    Arrays.sort(nums);

    Integer result = null;
    for (int i = 0; i < nums.length; ++i) {
      int leftIndex = i + 1;
      int rightIndex = nums.length - 1;
      while (leftIndex < rightIndex) {
        int sum = nums[i] + nums[leftIndex] + nums[rightIndex];
        if (result == null || Math.abs(sum - target) < Math.abs(result - target)) {
          result = sum;
        }

        if (sum <= target) {
          ++leftIndex;
        } else {
          --rightIndex;
        }
      }
    }

    return result;
  }
}
