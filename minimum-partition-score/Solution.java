// https://cp-algorithms.com/dynamic_programming/divide-and-conquer-dp.html

import java.util.stream.IntStream;

class Solution {
  public long minPartitionScore(int[] nums, int k) {
    int[] prefixSums = new int[nums.length + 1];
    for (int i = 1; i < prefixSums.length; ++i) {
      prefixSums[i] = prefixSums[i - 1] + nums[i - 1];
    }

    long[] dp = IntStream.range(0, nums.length).mapToLong(i -> C(prefixSums, 0, i)).toArray();
    for (int i = 0; i < k - 1; ++i) {
      long[] nextDp = new long[dp.length];
      search(nextDp, dp, prefixSums, 0, nums.length - 1, 0, nums.length - 1);

      dp = nextDp;
    }

    return dp[dp.length - 1];
  }

  void search(long[] nextDp, long[] dp, int[] prefixSums, int l, int r, int optL, int optR) {
    if (l <= r) {
      int mid = (l + r) / 2;

      long bestV = Long.MAX_VALUE;
      int bestK = -1;
      for (int k = optL; k <= Math.min(mid, optR); ++k) {
        long v = ((k == 0) ? 0 : dp[k - 1]) + C(prefixSums, k, mid);
        if (v < bestV) {
          bestV = v;
          bestK = k;
        }
      }

      nextDp[mid] = bestV;

      search(nextDp, dp, prefixSums, l, mid - 1, optL, bestK);
      search(nextDp, dp, prefixSums, mid + 1, r, bestK, optR);
    }
  }

  long C(int[] prefixSums, int beginIndex, int endIndex) {
    int sum = prefixSums[endIndex + 1] - prefixSums[beginIndex];

    return sum * (sum + 1L) / 2;
  }
}
