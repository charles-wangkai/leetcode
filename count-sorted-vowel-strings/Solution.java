import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int countVowelStrings(int n) {
    int[] counts = IntStream.range(0, 5).map(i -> 1).toArray();
    for (int i = 0; i < n - 1; ++i) {
      int[] nextCounts = new int[5];
      for (int j = 0; j < nextCounts.length; ++j) {
        for (int k = 0; k <= j; ++k) {
          nextCounts[j] += counts[k];
        }
      }

      counts = nextCounts;
    }

    return Arrays.stream(counts).sum();
  }
}
