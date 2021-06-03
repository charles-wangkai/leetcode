import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
    return (int)
        (((long) computeMaxGap(h, horizontalCuts) * computeMaxGap(w, verticalCuts))
            % 1_000_000_007);
  }

  int computeMaxGap(int limit, int[] cuts) {
    int[] values =
        IntStream.concat(IntStream.of(0, limit), Arrays.stream(cuts))
            .boxed()
            .sorted()
            .mapToInt(x -> x)
            .toArray();

    return IntStream.range(0, values.length - 1)
        .map(i -> values[i + 1] - values[i])
        .max()
        .getAsInt();
  }
}
