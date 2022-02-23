import java.util.stream.IntStream;

class Solution {
  static final int ALPHABET_SIZE = 26;

  public int equalCountSubstrings(String s, int count) {
    int[][] prefixSums = new int[s.length() + 1][ALPHABET_SIZE];
    for (int i = 1; i < prefixSums.length; ++i) {
      for (int j = 0; j < ALPHABET_SIZE; ++j) {
        prefixSums[i][j] = prefixSums[i - 1][j] + ((s.charAt(i - 1) - 'a' == j) ? 1 : 0);
      }
    }

    int result = 0;
    for (int i = 0; i < s.length(); ++i) {
      for (int j = 1; j <= ALPHABET_SIZE; ++j) {
        if (check(prefixSums, i + 1, i + j * count, count)) {
          ++result;
        }
      }
    }

    return result;
  }

  boolean check(int[][] prefixSums, int beginIndex, int endIndex, int count) {
    return endIndex < prefixSums.length
        && IntStream.range(0, ALPHABET_SIZE)
            .allMatch(
                i ->
                    prefixSums[endIndex][i] - prefixSums[beginIndex - 1][i] == 0
                        || prefixSums[endIndex][i] - prefixSums[beginIndex - 1][i] == count);
  }
}