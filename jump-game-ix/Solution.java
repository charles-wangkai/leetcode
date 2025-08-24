class Solution {
  public int[] maxValue(int[] nums) {
    int[] leftMaxs = new int[nums.length];
    for (int i = 0; i < leftMaxs.length; ++i) {
      leftMaxs[i] = Math.max((i == 0) ? Integer.MIN_VALUE : leftMaxs[i - 1], nums[i]);
    }

    int[] rightMins = new int[nums.length];
    for (int i = rightMins.length - 1; i >= 0; --i) {
      rightMins[i] =
          Math.min((i == rightMins.length - 1) ? Integer.MAX_VALUE : rightMins[i + 1], nums[i]);
    }

    int[] result = new int[nums.length];
    int beginIndex = 0;
    while (beginIndex != result.length) {
      int endIndex = beginIndex;
      while (endIndex != nums.length - 1 && leftMaxs[endIndex] > rightMins[endIndex + 1]) {
        ++endIndex;
      }

      for (int i = beginIndex; i <= endIndex; ++i) {
        result[i] = leftMaxs[endIndex];
      }

      beginIndex = endIndex + 1;
    }

    return result;
  }
}