class Solution {
  public int[] reverseSubarrays(int[] nums, int k) {
    int unitLength = nums.length / k;
    for (int beginIndex = 0, endIndex = unitLength - 1;
        beginIndex < nums.length;
        beginIndex += unitLength, endIndex += unitLength) {
      for (int i = beginIndex, j = endIndex; i < j; ++i, --j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
      }
    }

    return nums;
  }
}