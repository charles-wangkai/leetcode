class Solution {
  public int maximalNetworkRank(int n, int[][] roads) {
    int[] counts = new int[n];
    int[][] connections = new int[n][n];
    for (int[] road : roads) {
      ++counts[road[0]];
      ++counts[road[1]];

      ++connections[road[0]][road[1]];
      ++connections[road[1]][road[0]];
    }

    int result = 0;
    for (int i = 0; i < n; ++i) {
      for (int j = i + 1; j < n; ++j) {
        result = Math.max(result, counts[i] + counts[j] - connections[i][j]);
      }
    }

    return result;
  }
}
