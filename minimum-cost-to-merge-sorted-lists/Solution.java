import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  static final int LIMIT = 1_000_000_000;

  public long minMergeCost(int[][] lists) {
    int n = lists.length;

    int[] lengths = new int[1 << n];
    for (int mask = 1; mask < lengths.length; ++mask) {
      int mask_ = mask;
      lengths[mask] =
          IntStream.range(0, n)
              .filter(b -> ((mask_ >> b) & 1) == 1)
              .map(b -> lists[b].length)
              .sum();
    }

    int[] medians = new int[1 << n];
    for (int mask = 1; mask < medians.length; ++mask) {
      medians[mask] = findMedian(lists, mask, (lengths[mask] - 1) / 2);
    }

    long[] dp = new long[1 << n];
    int[] sortedMasks =
        IntStream.range(0, 1 << n)
            .filter(mask -> Integer.bitCount(mask) > 1)
            .boxed()
            .sorted(Comparator.comparing(Integer::bitCount))
            .mapToInt(Integer::intValue)
            .toArray();
    for (int mask : sortedMasks) {
      dp[mask] = Long.MAX_VALUE;
      for (int i = 1; i < mask; ++i) {
        if ((mask & i) == i) {
          dp[mask] =
              Math.min(
                  dp[mask],
                  dp[i]
                      + dp[mask - i]
                      + lengths[i]
                      + lengths[mask - i]
                      + Math.abs(medians[i] - medians[mask - i]));
        }
      }
    }

    return dp[dp.length - 1];
  }

  int findMedian(int[][] lists, int mask, int targetIndex) {
    int[] indices = IntStream.range(0, lists.length).filter(b -> ((mask >> b) & 1) == 1).toArray();

    int result = Integer.MIN_VALUE;
    int lower = -LIMIT;
    int upper = LIMIT;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(lists, indices, targetIndex, middle)) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  boolean check(int[][] lists, int[] indices, int targetIndex, int value) {
    return Arrays.stream(indices)
            .map(
                index -> {
                  int result = 0;
                  int lower = 0;
                  int upper = lists[index].length - 1;
                  while (lower <= upper) {
                    int middle = (lower + upper) / 2;
                    if (lists[index][middle] <= value) {
                      result = middle + 1;
                      lower = middle + 1;
                    } else {
                      upper = middle - 1;
                    }
                  }

                  return result;
                })
            .sum()
        >= targetIndex + 1;
  }
}