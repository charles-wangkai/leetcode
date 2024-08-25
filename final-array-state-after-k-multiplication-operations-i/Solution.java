import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int[] getFinalState(int[] nums, int k, int multiplier) {
    int[] result = nums.clone();
    for (int i = 0; i < k; ++i) {
      int index =
          IntStream.range(0, result.length)
              .boxed()
              .min(Comparator.<Integer, Integer>comparing(j -> result[j]).thenComparing(j -> j))
              .get();
      result[index] *= multiplier;
    }

    return result;
  }
}