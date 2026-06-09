import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.IntStream;

class Solution {
  public long maximumSum(int[] nums, int m, int l, int r) {
    int n = nums.length;

    long[] prefixSums = new long[n + 1];
    for (int i = 1; i < prefixSums.length; ++i) {
      prefixSums[i] = prefixSums[i - 1] + nums[i - 1];
    }

    long[][] dp = new long[n + 1][m + 1];
    for (int length = 0; length < dp.length; ++length) {
      for (int subarrayNum = 1; subarrayNum <= m; ++subarrayNum) {
        dp[length][subarrayNum] = Long.MIN_VALUE;
      }
    }

    for (int subarrayNum = 1; subarrayNum <= m; ++subarrayNum) {
      SortedMap<Long, Integer> prevContribToCount = new TreeMap<>();
      for (int length = 1; length <= n; ++length) {
        dp[length][subarrayNum] = dp[length - 1][subarrayNum];

        if (length - l >= 0) {
          updateMap(
              prevContribToCount,
              subtract(dp[length - l][subarrayNum - 1], prefixSums[length - l]),
              1);
        }

        if (!prevContribToCount.isEmpty()) {
          dp[length][subarrayNum] =
              Math.max(
                  dp[length][subarrayNum], add(prefixSums[length], prevContribToCount.lastKey()));
        }

        if (length - r >= 0) {
          updateMap(
              prevContribToCount,
              subtract(dp[length - r][subarrayNum - 1], prefixSums[length - r]),
              -1);
        }
      }
    }

    return IntStream.rangeClosed(1, m)
        .mapToLong(subarrayNum -> dp[n][subarrayNum])
        .max()
        .getAsLong();
  }

  long add(long x, long y) {
    return (x == Long.MIN_VALUE || y == Long.MIN_VALUE) ? Long.MIN_VALUE : (x + y);
  }

  long subtract(long x, long y) {
    return (x == Long.MIN_VALUE || y == Long.MIN_VALUE) ? Long.MIN_VALUE : (x - y);
  }

  void updateMap(SortedMap<Long, Integer> prevContribToCount, long prevContrib, int delta) {
    prevContribToCount.put(prevContrib, prevContribToCount.getOrDefault(prevContrib, 0) + delta);
    prevContribToCount.remove(prevContrib, 0);
  }
}