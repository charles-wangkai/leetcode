// https://chatgpt.com/share/6ae48716-d0fc-83ec-97be-f460518c8850

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

class Solution {
  public long minPartitionScore(int[] nums, int k) {
    int[] prefixSums = new int[nums.length + 1];
    for (int i = 1; i < prefixSums.length; ++i) {
      prefixSums[i] = prefixSums[i - 1] + nums[i - 1];
    }

    int total = prefixSums[prefixSums.length - 1];

    long maxPenalty = (long) total * total / 4;

    long bestPenalty = -1;
    long lower = 0;
    long upper = maxPenalty;
    while (lower <= upper) {
      long middle = (lower + upper) / 2;
      if (solve(prefixSums, middle).partitionNum() <= k) {
        bestPenalty = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return (solve(prefixSums, bestPenalty).score() - 2 * k * bestPenalty) / 2;
  }

  Outcome solve(int[] prefixSums, long penalty) {
    int n = prefixSums.length - 1;

    List<Line> hull = new ArrayList<>();
    int beginIndex = 0;

    hull.add(new Line(0, 0, 0));

    long score = 0;
    int partitionNum = 0;
    for (int i = 1; i <= n; ++i) {
      int x = prefixSums[i];

      while (beginIndex + 1 != hull.size()
          && hull.get(beginIndex + 1).eval(x) < hull.get(beginIndex).eval(x)) {
        ++beginIndex;
      }

      long bestValue = hull.get(beginIndex).eval(x);
      int bestPartitionNum = hull.get(beginIndex).partitionNum();

      while (beginIndex + 1 < hull.size() && hull.get(beginIndex + 1).eval(x) == bestValue) {
        ++beginIndex;
        bestPartitionNum = Math.min(bestPartitionNum, hull.get(beginIndex).partitionNum());
      }

      score = bestValue + (long) x * x + x + 2 * penalty;
      partitionNum = bestPartitionNum + 1;

      Line line = new Line(-2 * x, score + (long) x * x - x, partitionNum);

      while (hull.size() - beginIndex >= 2
          && isRedundant(hull.get(hull.size() - 2), hull.getLast(), line)) {
        hull.removeLast();
      }

      hull.add(line);
    }

    return new Outcome(score, partitionNum);
  }

  boolean isRedundant(Line a, Line b, Line c) {
    BigInteger left =
        BigInteger.valueOf(b.intercept() - a.intercept())
            .multiply(BigInteger.valueOf(b.slope() - c.slope()));

    BigInteger right =
        BigInteger.valueOf(c.intercept() - b.intercept())
            .multiply(BigInteger.valueOf(a.slope() - b.slope()));

    return left.compareTo(right) > 0;
  }
}

record Outcome(long score, int partitionNum) {}

record Line(long slope, long intercept, int partitionNum) {
  long eval(long x) {
    return slope * x + intercept;
  }
}
