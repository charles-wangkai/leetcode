import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public int findMinDifference(List<String> timePoints) {
    int[] minutes = timePoints.stream().mapToInt(this::toMinute).sorted().toArray();

    return IntStream.range(0, minutes.length)
        .map(
            i -> ((i == minutes.length - 1) ? (minutes[0] + 24 * 60) : minutes[i + 1]) - minutes[i])
        .min()
        .getAsInt();
  }

  int toMinute(String timePoint) {
    String[] parts = timePoint.split(":");

    return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
  }
}
