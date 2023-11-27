// https://leetcode.com/problems/find-maximum-non-decreasing-array-length/solutions/4329598/o-n-solution-with-detailed-explanation/

import java.util.ArrayList;
import java.util.List;

class Solution {
  public int findMaximumLength(int[] nums) {
    long[] prefixSums = new long[nums.length + 1];
    for (int i = 1; i < prefixSums.length; ++i) {
      prefixSums[i] = prefixSums[i - 1] + nums[i - 1];
    }

    int[] dp = new int[nums.length + 1];
    long[] lasts = new long[nums.length + 1];
    List<Integer> indices = new ArrayList<>();
    indices.add(0);
    for (int i = 1; i <= nums.length; ++i) {
      int prevIndex = findPrevIndex(prefixSums, lasts, indices, prefixSums[i]);
      dp[i] = dp[prevIndex] + 1;
      lasts[i] = prefixSums[i] - prefixSums[prevIndex];

      while (lasts[indices.get(indices.size() - 1)] + prefixSums[indices.get(indices.size() - 1)]
          >= lasts[i] + prefixSums[i]) {
        indices.remove(indices.size() - 1);
      }
      indices.add(i);
    }

    return dp[dp.length - 1];
  }

  int findPrevIndex(long[] prefixSums, long[] lasts, List<Integer> indices, long target) {
    int pos = -1;
    int lower = 0;
    int upper = indices.size() - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (lasts[indices.get(middle)] + prefixSums[indices.get(middle)] <= target) {
        pos = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return indices.get(pos);
  }
}