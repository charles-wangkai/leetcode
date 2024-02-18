import java.util.HashMap;
import java.util.Map;

class Solution {
  public int mostFrequentPrime(int[][] mat) {
    int m = mat.length;
    int n = mat[0].length;

    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        for (int dr = -1; dr <= 1; ++dr) {
          for (int dc = -1; dc <= 1; ++dc) {
            if (dr != 0 || dc != 0) {
              int currentR = r;
              int currentC = c;
              int value = mat[r][c];
              while (true) {
                currentR += dr;
                currentC += dc;
                if (!(currentR >= 0 && currentR < m && currentC >= 0 && currentC < n)) {
                  break;
                }

                value = value * 10 + mat[currentR][currentC];
                if (isPrime(value)) {
                  valueToCount.put(value, valueToCount.getOrDefault(value, 0) + 1);
                }
              }
            }
          }
        }
      }
    }

    if (valueToCount.isEmpty()) {
      return -1;
    }

    int maxCount = valueToCount.values().stream().mapToInt(Integer::intValue).max().getAsInt();

    return valueToCount.keySet().stream()
        .filter(value -> valueToCount.get(value) == maxCount)
        .mapToInt(Integer::intValue)
        .max()
        .getAsInt();
  }

  boolean isPrime(int x) {
    for (int i = 2; i * i <= x; ++i) {
      if (x % i == 0) {
        return false;
      }
    }

    return true;
  }
}