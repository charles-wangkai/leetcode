import java.util.stream.IntStream;

class Solution {
  public int captureForts(int[] forts) {
    int result = 0;
    for (int i = 0; i < forts.length; ++i) {
      if (forts[i] == 1) {
        for (int j = 0; j < forts.length; ++j) {
          if (forts[j] == -1
              && IntStream.range(Math.min(i, j) + 1, Math.max(i, j)).allMatch(k -> forts[k] == 0)) {
            result = Math.max(result, Math.abs(i - j) - 1);
          }
        }
      }
    }

    return result;
  }
}
