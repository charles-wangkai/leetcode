class Solution {
  public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
    int indexWithMinValue = nums.length - 1;
    int indexWithMaxValue = nums.length - 1;
    for (int i = nums.length - 1 - indexDifference; i >= 0; --i) {
      if (Math.abs(nums[i] - nums[indexWithMinValue]) >= valueDifference) {
        return new int[] {i, indexWithMinValue};
      }
      if (Math.abs(nums[i] - nums[indexWithMaxValue]) >= valueDifference) {
        return new int[] {i, indexWithMaxValue};
      }

      if (i + indexDifference != 0) {
        if (nums[i + indexDifference - 1] < nums[indexWithMinValue]) {
          indexWithMinValue = i + indexDifference - 1;
        }
        if (nums[i + indexDifference - 1] > nums[indexWithMaxValue]) {
          indexWithMaxValue = i + indexDifference - 1;
        }
      }
    }

    return new int[] {-1, -1};
  }
}
