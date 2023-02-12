import java.util.Arrays;

class Solution {
  public long countFairPairs(int[] nums, int lower, int upper) {
    Arrays.sort(nums);

    long result = 0;
    for (int num : nums) {
      result +=
          findUpperIndex(nums, upper - num)
              - findLowerIndex(nums, lower - num)
              + 1
              - ((num * 2 >= lower && num * 2 <= upper) ? 1 : 0);
    }
    result /= 2;

    return result;
  }

  int findLowerIndex(int[] nums, int target) {
    int result = nums.length;
    int lowerIndex = 0;
    int upperIndex = nums.length - 1;
    while (lowerIndex <= upperIndex) {
      int middleIndex = (lowerIndex + upperIndex) / 2;
      if (nums[middleIndex] >= target) {
        result = middleIndex;
        upperIndex = middleIndex - 1;
      } else {
        lowerIndex = middleIndex + 1;
      }
    }

    return result;
  }

  int findUpperIndex(int[] nums, int target) {
    int result = -1;
    int lowerIndex = 0;
    int upperIndex = nums.length - 1;
    while (lowerIndex <= upperIndex) {
      int middleIndex = (lowerIndex + upperIndex) / 2;
      if (nums[middleIndex] <= target) {
        result = middleIndex;
        lowerIndex = middleIndex + 1;
      } else {
        upperIndex = middleIndex - 1;
      }
    }

    return result;
  }
}
