class Solution {
  public int numberOfPaths(int n, int[][] corridors) {
    boolean[][] connected = new boolean[n + 1][n + 1];
    for (int[] corridor : corridors) {
      connected[corridor[0]][corridor[1]] = true;
      connected[corridor[1]][corridor[0]] = true;
    }

    int result = 0;
    for (int[] corridor : corridors) {
      for (int i = 1; i <= n; ++i) {
        if (connected[i][corridor[0]] && connected[i][corridor[1]]) {
          ++result;
        }
      }
    }
    result /= 3;

    return result;
  }
}