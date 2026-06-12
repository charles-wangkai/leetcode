// https://leetcode.com/problems/maximum-sum-of-m-non-overlapping-subarrays-ii/solutions/8318803/on-log-w-aliens-trick-wqs-binary-search-a5xei/

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
  static final long LIMIT = 100_000_000_000L;

  public long maximumSum(int[] nums, int m, int l, int r) {
    int n = nums.length;

    long[] prefixSums = new long[n + 1];
    for (int i = 1; i < prefixSums.length; ++i) {
      prefixSums[i] = prefixSums[i - 1] + nums[i - 1];
    }

    Outcome outcome = calc(l, r, prefixSums, 0);
    if (outcome.subarrayCount() > m) {
      return solveForExactSubarrayCount(l, r, prefixSums, m);
    }
    if (outcome.subarrayCount() < 1) {
      return solveForExactSubarrayCount(l, r, prefixSums, 1);
    }

    return outcome.maxSum();
  }

  long solveForExactSubarrayCount(int l, int r, long[] prefixSums, int targetSubarrayCount) {
    long result = Long.MIN_VALUE;
    long lower = -LIMIT;
    long upper = LIMIT;
    while (lower <= upper) {
      long middle = (lower + upper) / 2;

      Outcome outcome = calc(l, r, prefixSums, middle);
      if (outcome.subarrayCount() >= targetSubarrayCount) {
        result = outcome.maxSum() + targetSubarrayCount * middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  Outcome calc(int l, int r, long[] prefixSums, long penalty) {
    int n = prefixSums.length - 1;

    long[] dp = new long[n + 1];
    int[] cnt = new int[n + 1];
    Deque<Integer> dq = new ArrayDeque<>();

    for (int i = 1; i <= n; ++i) {
      dp[i] = dp[i - 1];
      cnt[i] = cnt[i - 1];

      int a = i - l;
      if (a >= 0) {
        while (!dq.isEmpty()
            && compare(getVal(prefixSums, dp, cnt, dq.peekLast()), getVal(prefixSums, dp, cnt, a))
                <= 0) {
          dq.pollLast();
        }
        dq.offerLast(a);
      }

      while (!dq.isEmpty() && dq.peekFirst() < i - r) {
        dq.pollFirst();
      }

      if (!dq.isEmpty()) {
        int f = dq.peekFirst();

        long candVal = prefixSums[i] + dp[f] - prefixSums[f] - penalty;
        int candCnt = cnt[f] + 1;

        if (candVal > dp[i] || (candVal == dp[i] && candCnt > cnt[i])) {
          dp[i] = candVal;
          cnt[i] = candCnt;
        }
      }
    }

    return new Outcome(dp[n], cnt[n]);
  }

  int compare(Element e1, Element e2) {
    if (e1.contrib() != e2.contrib()) {
      return Long.compare(e1.contrib(), e2.contrib());
    }

    return Integer.compare(e1.subarrayCount(), e2.subarrayCount());
  }

  Element getVal(long[] prefixSums, long[] dp, int[] cnt, int a) {
    return new Element(dp[a] - prefixSums[a], cnt[a]);
  }
}

record Element(long contrib, int subarrayCount) {}

record Outcome(long maxSum, int subarrayCount) {}
