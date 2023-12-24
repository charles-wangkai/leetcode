class Solution {
  static final int ALPHABET_SIZE = 26;

  public long minimumCost(
      String source, String target, char[] original, char[] changed, int[] cost) {
    int[][] distances = buildDistances(original, changed, cost);

    long result = 0;
    for (int i = 0; i < source.length(); ++i) {
      if (distances[source.charAt(i) - 'a'][target.charAt(i) - 'a'] == Integer.MAX_VALUE) {
        return -1;
      }

      result += distances[source.charAt(i) - 'a'][target.charAt(i) - 'a'];
    }

    return result;
  }

  int[][] buildDistances(char[] original, char[] changed, int[] cost) {
    int[][] distances = new int[ALPHABET_SIZE][ALPHABET_SIZE];
    for (int i = 0; i < ALPHABET_SIZE; ++i) {
      for (int j = 0; j < ALPHABET_SIZE; ++j) {
        distances[i][j] = (i == j) ? 0 : Integer.MAX_VALUE;
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