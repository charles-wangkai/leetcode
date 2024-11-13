import java.util.Arrays;

class Solution {
  public long countFairPairs(int[] nums, int lower, int upper) {
    Arrays.sort(nums);

    long result = 0;
    int beginIndex = nums.length;
    int endIndex = nums.length - 1;
    for (int i = nums.length - 1; i >= 0; --i) {
      while (beginIndex != nums.length && nums[beginIndex] < lower - nums[i]) {
        ++beginIndex;
      }
      while (endIndex + 1 != nums.length && nums[endIndex + 1] <= upper - nums[i]) {
        ++endIndex;
      }

      result += endIndex - beginIndex + 1;

      --beginIndex;
      --endIndex;
    }

    return result;
  }
}
