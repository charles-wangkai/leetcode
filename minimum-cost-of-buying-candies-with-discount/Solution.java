import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int minimumCost(int[] cost) {
    int[] sorted =
        Arrays.stream(cost)
            .boxed()
            .sorted(Comparator.reverseOrder())
            .mapToInt(Integer::intValue)
            .toArray();

    return IntStream.range(0, sorted.length).map(i -> (i % 3 == 2) ? 0 : sorted[i]).sum();
  }
}