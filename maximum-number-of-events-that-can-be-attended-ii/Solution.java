import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int maxValue(int[][] events, int k) {
    int[] days =
        Arrays.stream(events)
            .flatMapToInt(event -> IntStream.of(event[0], event[1]))
            .distinct()
            .sorted()
            .toArray();
    Map<Integer, Integer> dayToSequence =
        IntStream.range(0, days.length).boxed().collect(Collectors.toMap(i -> days[i], i -> i + 1));

    int[] dp = new int[days.length + 1];
    for (int i = 0; i < k; ++i) {
      int[] nextSums = dp.clone();
      for (int[] event : events) {
        nextSums[dayToSequence.get(event[1])] =
            Math.max(
                nextSums[dayToSequence.get(event[1])],
                dp[dayToSequence.get(event[0]) - 1] + event[2]);
      }

      int[] nextDp = new int[dp.length];
      for (int j = 0; j < nextDp.length; ++j) {
        nextDp[j] = Math.max((j == 0) ? 0 : nextDp[j - 1], nextSums[j]);
      }

      dp = nextDp;
    }

    return dp[dp.length - 1];
  }
}
