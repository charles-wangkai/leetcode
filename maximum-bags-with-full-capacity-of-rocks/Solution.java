import java.util.stream.IntStream;

class Solution {
  public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
    int[] diffs =
        IntStream.range(0, capacity.length)
            .map(i -> capacity[i] - rocks[i])
            .boxed()
            .sorted()
            .mapToInt(x -> x)
            .toArray();

    int result = 0;
    for (int i = 0; i < diffs.length; ++i) {
      int delta = Math.min(diffs[i], additionalRocks);
      diffs[i] -= delta;
      additionalRocks -= delta;

      if (diffs[i] == 0) {
        ++result;
      }
    }

    return result;
  }
}