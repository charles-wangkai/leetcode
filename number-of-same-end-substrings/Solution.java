import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  static final int ALPHABET_SIZE = 26;

  public int[] sameEndSubstringCount(String s, int[][] queries) {
    int[][] prefixCounts = new int[s.length() + 1][ALPHABET_SIZE];
    for (int i = 1; i < prefixCounts.length; ++i) {
      for (int j = 0; j < ALPHABET_SIZE; ++j) {
        prefixCounts[i][j] = prefixCounts[i - 1][j] + ((j == s.charAt(i - 1) - 'a') ? 1 : 0);
      }
    }

    return Arrays.stream(queries)
        .mapToInt(
            query ->
                IntStream.range(0, ALPHABET_SIZE)
                    .map(
                        i -> {
                          int count = prefixCounts[query[1] + 1][i] - prefixCounts[query[0]][i];

                          return count * (count + 1) / 2;
                        })
                    .sum())
        .toArray();
  }
}