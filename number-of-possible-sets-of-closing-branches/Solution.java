import java.util.stream.IntStream;

class Solution {
  public int numberOfSets(int n, int maxDistance, int[][] roads) {
    return (int)
        IntStream.range(0, 1 << n).filter(mask -> check(n, maxDistance, roads, mask)).count();
  }

  boolean check(int n, int maxDistance, int[][] roads, int mask) {
    int[][] distances = new int[n][n];
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < n; ++j) {
        distances[i][j] = (i == j) ? 0 : Integer.MAX_VALUE;
      }
    }
    for (int[] road : roads) {
      if (((mask >> road[0]) & 1) == 1 && ((mask >> road[1]) & 1) == 1) {
        distances[road[0]][road[1]] = Math.min(distances[road[0]][road[1]], road[2]);
        distances[road[1]][road[0]] = Math.min(distances[road[1]][road[0]], road[2]);
      }
    }

    for (int k = 0; k < n; ++k) {
      for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
          if (distances[i][k] != Integer.MAX_VALUE && distances[k][j] != Integer.MAX_VALUE) {
            distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);
          }
        }
      }
    }

    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < n; ++j) {
        if (((mask >> i) & 1) == 1 && ((mask >> j) & 1) == 1 && distances[i][j] > maxDistance) {
          return false;
        }
      }
    }

    return true;
  }
}