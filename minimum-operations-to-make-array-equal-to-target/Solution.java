class Solution {
  public long minimumOperations(int[] nums, int[] target) {
    long result = 0;
    for (int i = 0; i < nums.length; ++i) {
      if (nums[i] < target[i]) {
        int gap = target[i] - nums[i];
        result += gap;

        int index = i;
        while (gap != 0 && index != nums.length - 1) {
          int nextGap = Math.min(gap, Math.max(0, target[index + 1] - nums[index + 1]));
          nums[index + 1] += nextGap;

          gap = nextGap;
          ++index;
        }
      } else if (nums[i] > target[i]) {
        int gap = nums[i] - target[i];
        result += gap;

        int index = i;
        while (gap != 0 && index != nums.length - 1) {
          int nextGap = Math.min(gap, Math.max(0, nums[index + 1] - target[index + 1]));
          nums[index + 1] -= nextGap;

          gap = nextGap;
          ++index;
        }
      }
    }

    return result;
  }
}