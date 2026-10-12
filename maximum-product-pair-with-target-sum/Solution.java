class Solution {
  public int[] maxProductPair(int[] nums, int target) {
    int bestI = -1;
    int bestJ = -1;
    for (int i = 0; i < nums.length; ++i) {
      for (int j = 0; j < nums.length; ++j) {
        if (nums[i] + nums[j] == target
            && nums[i] > nums[j]
            && (bestI == -1 || nums[i] * nums[j] > nums[bestI] * nums[bestJ])) {
          bestI = i;
          bestJ = j;
        }
      }
    }

    return new int[] {bestI, bestJ};
  }
}