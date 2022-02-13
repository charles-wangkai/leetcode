import java.util.Arrays;

class Solution {
  public long minimumRemoval(int[] beans) {
    int[] sorted = Arrays.stream(beans).boxed().sorted().mapToInt(x -> x).toArray();

    long result = Long.MAX_VALUE;
    long total = Arrays.stream(beans).asLongStream().sum();
    for (int i = 0; i < beans.length; ++i) {
      result = Math.min(result, total - (long) sorted[i] * (beans.length - i));
    }

    return result;
  }
}