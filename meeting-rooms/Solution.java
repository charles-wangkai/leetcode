import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public boolean canAttendMeetings(int[][] intervals) {
    int[][] sortedIntervals =
        Arrays.stream(intervals)
            .sorted((i1, i2) -> Integer.compare(i1[0], i2[0]))
            .toArray(int[][]::new);

    return IntStream.range(0, sortedIntervals.length - 1)
        .allMatch(i -> sortedIntervals[i][1] <= sortedIntervals[i + 1][0]);
  }
}
