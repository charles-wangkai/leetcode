import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public long maximumHappinessSum(int[] happiness, int k) {
    int[] sorted =
        Arrays.stream(happiness)
            .boxed()
            .sorted(Comparator.reverseOrder())
            .mapToInt(Integer::intValue)
            .toArray();

    return IntStream.range(0, k).map(i -> Math.max(0, sorted[i] - i)).asLongStream().sum();
  }
}