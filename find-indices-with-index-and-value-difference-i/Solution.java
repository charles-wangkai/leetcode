class Solution {
  public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
    for (int i = 0; i < nums.length; ++i) {
      for (int j = i + indexDifference; j < nums.length; ++j) {
        if (Math.abs(nums[i] - nums[j]) >= valueDifference) {
          return new int[] {i, j};
        }
      }
    }

    return new int[] {-1, -1};
  }
}
