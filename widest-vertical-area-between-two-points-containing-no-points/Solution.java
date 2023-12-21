import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int maxWidthOfVerticalArea(int[][] points) {
    int[] sortedXs = Arrays.stream(points).mapToInt(point -> point[0]).sorted().toArray();

    return IntStream.range(0, sortedXs.length - 1)
        .map(i -> sortedXs[i + 1] - sortedXs[i])
        .max()
        .getAsInt();
  }
}
