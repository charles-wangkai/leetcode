import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int maxValue(int[][] events, int k) {
    int[] days =
        Arrays.stream(events)
            .flatMapToInt(e -> IntStream.of(e[0], e[1]))
            .distinct()
            .sorted()
            .toArray();
    Map<Integer, Integer> dayToSequence =
        IntStream.range(0, days.length).boxed().collect(Collectors.toMap(i -> days[i], i -> i + 1));

    int[] maxValueSums = new int[days.length + 1];
    for (int i = 0; i < k; ++i) {
      int[] nextSums = Arrays.copyOf(maxValueSums, maxValueSums.length);
      for (int[] event : events) {
        nextSums[dayToSequence.get(event[1])] =
            Math.max(
                nextSums[dayToSequence.get(event[1])],
                maxValueSums[dayToSequence.get(event[0]) - 1] + event[2]);
      }

      int nextValueSum = 0;
      int[] nextValueSums = new int[maxValueSums.length];
      for (int j = 0; j < nextValueSums.length; ++j) {
        nextValueSum = Math.max(nextValueSum, nextSums[j]);
        nextValueSums[j] = nextValueSum;
      }

      maxValueSums = nextValueSums;
    }

    return maxValueSums[maxValueSums.length - 1];
  }
}
