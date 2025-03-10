// https://leetcode.com/problems/maximize-subarrays-after-removing-one-conflicting-pair/solutions/6515832/python-greedy/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public long maxSubarrays(int n, int[][] conflictingPairs) {
    @SuppressWarnings("unchecked")
    List<Integer>[] leftEnds = new List[n + 1];
    for (int i = 1; i < leftEnds.length; ++i) {
      leftEnds[i] = new ArrayList<>();
    }
    for (int[] conflictingPair : conflictingPairs) {
      int leftEnd = Math.min(conflictingPair[0], conflictingPair[1]);
      int rightEnd = Math.max(conflictingPair[0], conflictingPair[1]);

      leftEnds[rightEnd].add(leftEnd);
    }

    long subarrayNumForAll = 0;
    int maxLeft = 0;
    int secondMaxLeft = 0;
    long[] bonuses = new long[n + 1];
    for (int right = 1; right <= n; ++right) {
      for (int left : leftEnds[right]) {
        if (left >= maxLeft) {
          secondMaxLeft = maxLeft;
          maxLeft = left;
        } else if (left >= secondMaxLeft) {
          secondMaxLeft = left;
        }
      }

      subarrayNumForAll += right - maxLeft;
      bonuses[maxLeft] += maxLeft - secondMaxLeft;
    }

    return subarrayNumForAll + Arrays.stream(bonuses).max().getAsLong();
  }
}
