class Solution {
  public long incremovableSubarrayCount(int[] nums) {
    if (nums.length == 1) {
      return 1;
    }

    int beginIndex = nums.length - 1;
    while (beginIndex != 1 && nums[beginIndex - 1] < nums[beginIndex]) {
      --beginIndex;
    }

    long result = nums.length - beginIndex + 1;
    for (int i = 0; i < nums.length - 1 && (i == 0 || nums[i] > nums[i - 1]); ++i) {
      while (beginIndex != nums.length && (beginIndex <= i + 1 || nums[beginIndex] <= nums[i])) {
        ++beginIndex;
      }

      result += nums.length - beginIndex + 1;
    }

    return result;
  }
}