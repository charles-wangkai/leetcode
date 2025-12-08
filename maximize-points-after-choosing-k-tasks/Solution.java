import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public long maxPoints(int[] technique1, int[] technique2, int k) {
    int[] sortedDiffs =
        IntStream.range(0, technique1.length)
            .map(i -> technique1[i] - technique2[i])
            .boxed()
            .sorted(Comparator.reverseOrder())
            .mapToInt(Integer::intValue)
            .toArray();

    return Arrays.stream(technique2).asLongStream().sum()
        + IntStream.range(0, sortedDiffs.length)
            .map(i -> (i < k) ? sortedDiffs[i] : Math.max(0, sortedDiffs[i]))
            .asLongStream()
            .sum();
  }
}