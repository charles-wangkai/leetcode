import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Solution {
  static final int ALPHABET_SIZE = 26;

  public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
    int[][] counts = new int[s.length() + 1][ALPHABET_SIZE];
    for (int i = 0; i < s.length(); i++) {
      for (int j = 0; j < ALPHABET_SIZE; j++) {
        counts[i + 1][j] = counts[i][j] + ((s.charAt(i) - 'a' == j) ? 1 : 0);
      }
    }

    return Arrays.stream(queries)
        .map(
            query ->
                IntStream.range(0, ALPHABET_SIZE)
                            .filter(i -> (counts[query[1] + 1][i] - counts[query[0]][i]) % 2 != 0)
                            .count()
                        - query[2] * 2
                    <= 1)
        .toList();
  }
}
