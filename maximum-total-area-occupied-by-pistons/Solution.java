import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public long maxArea(int height, int[] positions, String directions) {
    int[] deltas = new int[2 * height + 1];
    for (int i = 0; i < positions.length; ++i) {
      if (isUp(height, positions, directions, i)) {
        --deltas[height - positions[i]];
        ++deltas[height - positions[i] + height];
      } else {
        ++deltas[positions[i]];
        --deltas[positions[i] + height];
      }
    }

    long result = Arrays.stream(positions).asLongStream().sum();
    long area = result;
    int upNum =
        (int)
            IntStream.range(0, positions.length)
                .filter(i -> isUp(height, positions, directions, i))
                .count();
    for (int i = 0; i < deltas.length; ++i) {
      upNum += deltas[i];
      area += upNum - (positions.length - upNum);
      result = Math.max(result, area);
    }

    return result;
  }

  boolean isUp(int height, int[] positions, String directions, int index) {
    return positions[index] == 0 || (positions[index] != height && directions.charAt(index) == 'U');
  }
}