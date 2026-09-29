import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Solution {
  public long maxEarnings(int[][] meetings) {
    int[] times =
        Arrays.stream(meetings)
            .flatMapToInt(meeting -> IntStream.of(meeting[0], meeting[1]))
            .sorted()
            .distinct()
            .toArray();

    Range[] ranges =
        Stream.concat(
                Arrays.stream(meetings)
                    .map(meeting -> new Range(false, meeting[0], meeting[1], meeting[2])),
                IntStream.range(0, times.length - 1)
                    .mapToObj(
                        i -> new Range(true, times[i], times[i + 1], times[i + 1] - times[i])))
            .sorted(Comparator.comparing(Range::end))
            .toArray(Range[]::new);

    long[] dp = new long[ranges.length];
    Arrays.fill(dp, -1);

    long result = 0;
    for (int i = 0; i < dp.length; ++i) {
      if (i != 0) {
        dp[i] = dp[i - 1];
      }

      if (!ranges[i].isIdle()) {
        dp[i] = Math.max(dp[i], ranges[i].revenue());
        result = Math.max(result, ranges[i].revenue());
      }

      int prevIndex = findPrevIndex(ranges, ranges[i].start());
      if (prevIndex != -1 && dp[prevIndex] != -1) {
        dp[i] = Math.max(dp[i], dp[prevIndex] + ranges[i].revenue());

        if (!ranges[i].isIdle()) {
          result = Math.max(result, dp[prevIndex] + ranges[i].revenue());
        }
      }
    }

    return result;
  }

  int findPrevIndex(Range[] ranges, int start) {
    int result = -1;
    int lower = 0;
    int upper = ranges.length - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (ranges[middle].end() <= start) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }
}

record Range(boolean isIdle, int start, int end, int revenue) {}
