import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int findTheCity(int n, int[][] edges, int distanceThreshold) {
    int[][] distances = new int[n][n];
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < n; ++j) {
        distances[i][j] = (j == i) ? 0 : Integer.MAX_VALUE;
      }
    }

    for (int[] edge : edges) {
      distances[edge[0]][edge[1]] = edge[2];
      distances[edge[1]][edge[0]] = edge[2];
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

    int[] reachableNums =
        IntStream.range(0, n)
            .map(
                i ->
                    (int)
                        Arrays.stream(distances[i])
                            .filter(distance -> distance <= distanceThreshold)
                            .count())
            .toArray();
    int minReachableNum = Arrays.stream(reachableNums).min().getAsInt();

    return IntStream.range(0, reachableNums.length)
        .filter(i -> reachableNums[i] == minReachableNum)
        .max()
        .getAsInt();
  }
}
