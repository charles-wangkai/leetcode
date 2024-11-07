import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int largestCombination(int[] candidates) {
    return IntStream.range(0, 24)
        .map(
            i ->
                (int)
                    Arrays.stream(candidates)
                        .filter(candidate -> ((candidate >> i) & 1) == 1)
                        .count())
        .max()
        .getAsInt();
  }
}