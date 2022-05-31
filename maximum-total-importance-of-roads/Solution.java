import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public long maximumImportance(int n, int[][] roads) {
    int[] counts = new int[n];
    for (int[] road : roads) {
      ++counts[road[0]];
      ++counts[road[1]];
    }

    int[] sorted = Arrays.stream(counts).boxed().sorted().mapToInt(x -> x).toArray();

    return IntStream.range(0, n).mapToLong(i -> (i + 1L) * sorted[i]).sum();
  }
}