import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  static final int ALPHABET_SIZE = 26;

  public long minimumCost(
      String source, String target, char[] original, char[] changed, int[] cost) {
    int[][] distances = buildDistances(original, changed, cost);
    int[] costs =
        IntStream.range(0, source.length())
            .map(i -> distances[source.charAt(i) - 'a'][target.charAt(i) - 'a'])
            .toArray();

    return Arrays.stream(costs).anyMatch(c -> c == Integer.MAX_VALUE)
        ? -1
        : Arrays.stream(costs).asLongStream().sum();
  }

  int[][] buildDistances(char[] original, char[] changed, int[] cost) {
    int[][] distances = new int[ALPHABET_SIZE][ALPHABET_SIZE];
    for (int i = 0; i < ALPHABET_SIZE; ++i) {
      for (int j = 0; j < ALPHABET_SIZE; ++j) {
        distances[i][j] = (j == i) ? 0 : Integer.MAX_VALUE;
      }
    }

    for (int i = 0; i < original.length; ++i) {
      distances[original[i] - 'a'][changed[i] - 'a'] =
          Math.min(distances[original[i] - 'a'][changed[i] - 'a'], cost[i]);
    }

    for (int k = 0; k < ALPHABET_SIZE; ++k) {
      for (int i = 0; i < ALPHABET_SIZE; ++i) {
        for (int j = 0; j < ALPHABET_SIZE; ++j) {
          if (distances[i][k] != Integer.MAX_VALUE && distances[k][j] != Integer.MAX_VALUE) {
            distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);
          }
        }
      }
    }

    return distances;
  }
}