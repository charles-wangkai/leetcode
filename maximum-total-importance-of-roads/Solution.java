import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public long maximumImportance(int n, int[][] roads) {
    int[] degrees = new int[n];
    for (int[] road : roads) {
      ++degrees[road[0]];
      ++degrees[road[1]];
    }
    Arrays.sort(degrees);

    return IntStream.range(0, n).mapToLong(i -> (i + 1L) * degrees[i]).sum();
  }
}