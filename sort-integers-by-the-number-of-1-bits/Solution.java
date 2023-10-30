import java.util.Arrays;
import java.util.Comparator;

class Solution {
  public int[] sortByBits(int[] arr) {
    return Arrays.stream(arr)
        .boxed()
        .sorted(Comparator.comparing(Integer::bitCount).thenComparing(x -> x))
        .mapToInt(Integer::intValue)
        .toArray();
  }
}
