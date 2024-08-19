class Solution {
  public int[] resultsArray(int[] nums, int k) {
    int n = nums.length;

    boolean[] consecutives = new boolean[n];
    for (int i = 0; i < consecutives.length; ++i) {
      consecutives[i] = i == 0 || nums[i] - nums[i - 1] == 1;
    }

    int[] result = new int[n - k + 1];
    int nonConsecutiveCount = 0;
    for (int i = 0; i < k - 1; ++i) {
      if (!consecutives[i]) {
        ++nonConsecutiveCount;
      }
    }
    for (int i = k - 1; i < n; ++i) {
      if (!consecutives[i]) {
        ++nonConsecutiveCount;
      }
      if (!consecutives[i - k + 1]) {
        --nonConsecutiveCount;
      }

      result[i - k + 1] = (nonConsecutiveCount == 0) ? nums[i] : -1;
    }

    return result;
  }
}