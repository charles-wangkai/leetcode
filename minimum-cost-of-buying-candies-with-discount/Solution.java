import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int minimumCost(int[] cost) {
    int[] sorted =
        Arrays.stream(cost).boxed().sorted(Comparator.reverseOrder()).mapToInt(x -> x).toArray();

    return Arrays.stream(sorted).sum()
        - IntStream.range(0, sorted.length).filter(i -> i % 3 == 2).map(i -> sorted[i]).sum();
  }
}