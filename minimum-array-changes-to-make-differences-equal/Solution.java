class Solution {
  public int minChanges(int[] nums, int k) {
    int[] diffCounts = new int[k + 1];
    int[] maxDiffCounts = new int[k + 1];
    for (int i = 0, j = nums.length - 1; i < j; ++i, --j) {
      ++diffCounts[Math.abs(nums[i] - nums[j])];
      ++maxDiffCounts[
          Math.max(
              Math.max(nums[i], nums[j]), Math.max(Math.abs(nums[i] - k), Math.abs(nums[j] - k)))];
    }

    int result = Integer.MAX_VALUE;
    int doubleReplaceCount = 0;
    for (int diff = 0; diff <= k; ++diff) {
      result = Math.min(result, (nums.length - diffCounts[diff] * 2) / 2 + doubleReplaceCount);
      doubleReplaceCount += maxDiffCounts[diff];
    }

    return result;
  }
}