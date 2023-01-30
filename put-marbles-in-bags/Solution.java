import java.util.stream.IntStream;

class Solution {
  public long putMarbles(int[] weights, int k) {
    int[] sortedSums =
        IntStream.range(0, weights.length - 1)
            .map(i -> weights[i] + weights[i + 1])
            .sorted()
            .toArray();

    return IntStream.range(sortedSums.length - k + 1, sortedSums.length)
            .map(i -> sortedSums[i])
            .asLongStream()
            .sum()
        - IntStream.range(0, k - 1).map(i -> sortedSums[i]).asLongStream().sum();
  }
}
