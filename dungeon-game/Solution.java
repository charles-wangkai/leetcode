class Solution {
  public int calculateMinimumHP(int[][] dungeon) {
    int m = dungeon.length;
    int n = dungeon[0].length;

    int[][] minHPs = new int[m][n];
    for (int i = m - 1; i >= 0; --i) {
      for (int j = n - 1; j >= 0; --j) {
        if (i == m - 1 && j == n - 1) {
          minHPs[i][j] = Math.max(1, -dungeon[i][j] + 1);
        } else {
          int right = (j == n - 1) ? Integer.MAX_VALUE : minHPs[i][j + 1];
          int down = (i == m - 1) ? Integer.MAX_VALUE : minHPs[i + 1][j];
          int minNext = Math.min(right, down);
          minHPs[i][j] = Math.max(1, minNext - dungeon[i][j]);
        }
      }
    }

    return minHPs[0][0];
  }
}
