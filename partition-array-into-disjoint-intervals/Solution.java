class Solution {
  public int partitionDisjoint(int[] nums) {
    int leftMax = Integer.MIN_VALUE;
    int[] leftMaxs = new int[nums.length];
    for (int i = 0; i < leftMaxs.length; ++i) {
      leftMax = Math.max(leftMax, nums[i]);
      leftMaxs[i] = leftMax;
    }

    int rightMin = Integer.MAX_VALUE;
    int[] rightMins = new int[nums.length];
    for (int i = rightMins.length - 1; i >= 0; --i) {
      rightMin = Math.min(rightMin, nums[i]);
      rightMins[i] = rightMin;
    }

    for (int i = 0; ; ++i) {
      if (leftMaxs[i] <= rightMins[i + 1]) {
        return i + 1;
      }
    }
  }
}
