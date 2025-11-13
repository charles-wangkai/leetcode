// https://leetcode.com/problems/maximize-cyclic-partition-score/solutions/7336748/c-dc-dp-by-awice-219n/
// https://cp-algorithms.com/dynamic_programming/divide-and-conquer-dp.html

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public long maximumScore(int[] nums, int k) {
    int minValueIndex =
        IntStream.range(0, nums.length).boxed().min(Comparator.comparing(i -> nums[i])).get();

    return Math.max(
        computeMaxScore(k, rotate(nums, minValueIndex)),
        computeMaxScore(k, rotate(nums, (minValueIndex + 1) % nums.length)));
  }

  int[] rotate(int[] nums, int beginIndex) {
    return IntStream.range(0, nums.length).map(i -> nums[(beginIndex + i) % nums.length]).toArray();
  }

  long computeMaxScore(int k, int[] values) {
    int n = values.length;

    int[][] W = new int[n][n];
    for (int beginIndex = 0; beginIndex < n; ++beginIndex) {
      int min = Integer.MAX_VALUE;
      int max = Integer.MIN_VALUE;
      for (int endIndex = beginIndex; endIndex < n; ++endIndex) {
        min = Math.min(min, values[endIndex]);
        max = Math.max(max, values[endIndex]);

        W[beginIndex][endIndex] = max - min;
      }
    }

    long[] dp = new long[n + 1];
    Arrays.fill(dp, Long.MIN_VALUE);
    dp[0] = 0;

    long[] nextDp = new long[n + 1];

    long result = 0;
    for (int i = 0; i < k; ++i) {
      Arrays.fill(nextDp, Long.MIN_VALUE);

      search(nextDp, W, dp, 1, n, 1, n);
      result = Math.max(result, nextDp[n]);

      long[] temp = dp;
      dp = nextDp;
      nextDp = temp;
    }

    return result;
  }

  void search(long[] nextDp, int[][] W, long[] dp, int L, int R, int pL, int pR) {
    if (L <= R) {
      int M = (L + R) / 2;

      long bestV = Long.MIN_VALUE;
      int bestP = pL;
      for (int p = pL; p <= M && p <= pR; ++p) {
        if (dp[p - 1] != Long.MIN_VALUE) {
          long v = dp[p - 1] + W[p - 1][M - 1];
          if (v > bestV) {
            bestV = v;
            bestP = p;
          }
        }
      }
      nextDp[M] = bestV;

      search(nextDp, W, dp, L, M - 1, pL, bestP);
      search(nextDp, W, dp, M + 1, R, bestP, pR);
    }
  }
}
