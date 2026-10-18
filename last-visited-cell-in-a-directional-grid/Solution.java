class Solution {
  public int[] lastVisitedCell(char[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    boolean[][] visited = new boolean[m][n];
    visited[0][0] = true;

    int r = 0;
    int c = 0;
    while (true) {
      int nextR = r;
      int nextC = c;
      if (grid[r][c] == 'L') {
        --nextC;
      } else if (grid[r][c] == 'R') {
        ++nextC;
      } else if (grid[r][c] == 'U') {
        --nextR;
      } else {
        ++nextR;
      }

      if (!(nextR >= 0 && nextR < m && nextC >= 0 && nextC < n && !visited[nextR][nextC])) {
        return new int[] {r, c};
      }

      visited[nextR][nextC] = true;

      r = nextR;
      c = nextC;
    }
  }
}