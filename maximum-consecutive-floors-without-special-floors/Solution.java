import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int maxConsecutive(int bottom, int top, int[] special) {
    int[] sorted =
        IntStream.concat(IntStream.of(bottom - 1, top + 1), Arrays.stream(special))
            .boxed()
            .sorted()
            .mapToInt(x -> x)
            .toArray();

    return IntStream.range(0, sorted.length - 1)
        .map(i -> sorted[i + 1] - sorted[i] - 1)
        .max()
        .orElse(0);
  }
}