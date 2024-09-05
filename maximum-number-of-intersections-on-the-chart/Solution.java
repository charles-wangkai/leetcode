import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int maxIntersectionCount(int[] y) {
    int[] values =
        Arrays.stream(y)
            .flatMap(yi -> IntStream.of(2 * yi, 2 * yi - 1, 2 * yi + 1))
            .sorted()
            .distinct()
            .toArray();
    Map<Integer, Integer> valueToCompressed =
        IntStream.range(0, values.length).boxed().collect(Collectors.toMap(i -> values[i], i -> i));

    int[] deltas = new int[values.length + 1];
    for (int i = 0; i < y.length - 1; ++i) {
      int from = valueToCompressed.get(2 * y[i]);
      int to = valueToCompressed.get(2 * y[i + 1]);
      if (from < to) {
        ++deltas[from + ((i == 0) ? 0 : 1)];
        --deltas[to + 1];
      } else {
        ++deltas[to];
        --deltas[from + ((i == 0) ? 1 : 0)];
      }
    }

    int result = 0;
    int intersectionNum = 0;
    for (int delta : deltas) {
      intersectionNum += delta;
      result = Math.max(result, intersectionNum);
    }

    return result;
  }
}