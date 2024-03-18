// https://leetcode.com/problems/minimum-moves-to-pick-k-ones/solutions/4886430/java-c-python-minimum-distance-of-points/

import java.util.ArrayList;
import java.util.List;

class Solution {
  public long minimumMoves(int[] nums, int k, int maxChanges) {
    List<Long> prefixSums = new ArrayList<>();
    prefixSums.add(0L);
    for (int i = 0; i < nums.length; ++i) {
      if (nums[i] == 1) {
        prefixSums.add(prefixSums.get(prefixSums.size() - 1) + i);
      }
    }

    int oneNum = prefixSums.size() - 1;
    int m = Math.max(0, k - maxChanges);
    long result = Long.MAX_VALUE;
    for (int length = m; length <= Math.min(oneNum, Math.min(m + 3, k)); ++length) {
      for (int i = 0; i + length <= oneNum; ++i) {
        result =
            Math.min(
                result,
                (prefixSums.get(i + length) - prefixSums.get(i + length - length / 2))
                    - (prefixSums.get(i + length / 2) - prefixSums.get(i))
                    + (k - length) * 2);
      }
    }

    return result;
  }
}