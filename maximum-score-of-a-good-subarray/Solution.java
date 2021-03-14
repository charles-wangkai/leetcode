class Solution {
  public int maximumScore(int[] nums, int k) {
    int result = nums[k];
    int minValue = nums[k];
    int leftIndex = k;
    int rightIndex = k;
    while (leftIndex != 0 || rightIndex != nums.length - 1) {
      if (rightIndex == nums.length - 1
          || (leftIndex != 0 && nums[leftIndex - 1] >= nums[rightIndex + 1])) {
        --leftIndex;
        minValue = Math.min(minValue, nums[leftIndex]);
      } else {
        ++rightIndex;
        minValue = Math.min(minValue, nums[rightIndex]);
      }

      result = Math.max(result, minValue * (rightIndex - leftIndex + 1));
    }

    return result;
  }
}
