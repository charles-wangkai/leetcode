import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int minimumIndex(int[] capacity, int itemSize) {
    return IntStream.range(0, capacity.length)
        .filter(i -> capacity[i] >= itemSize)
        .boxed()
        .min(Comparator.<Integer, Integer>comparing(i -> capacity[i]).thenComparing(i -> i))
        .orElse(-1);
  }
}