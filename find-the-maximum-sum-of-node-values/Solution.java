import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public long maximumValueSum(int[] nums, int k, int[][] edges) {
    int[] sortedDeltas =
        Arrays.stream(nums)
            .map(x -> (x ^ k) - x)
            .boxed()
            .sorted(Comparator.reverseOrder())
            .mapToInt(Integer::intValue)
            .toArray();

    return Arrays.stream(nums).asLongStream().sum()
        + IntStream.range(0, sortedDeltas.length / 2)
            .map(i -> Math.max(0, sortedDeltas[i * 2] + sortedDeltas[i * 2 + 1]))
            .asLongStream()
            .sum();
  }
}