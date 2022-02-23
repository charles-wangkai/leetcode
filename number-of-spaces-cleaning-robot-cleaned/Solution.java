class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int numberOfCleanRooms(int[][] room) {
    int m = room.length;
    int n = room[0].length;

    int result = 0;
    boolean[][][] visited = new boolean[m][n][R_OFFSETS.length];
    int r = 0;
    int c = 0;
    int direction = 1;
    boolean[][] cleaned = new boolean[m][n];
    while (!visited[r][c][direction]) {
      visited[r][c][direction] = true;
      if (!cleaned[r][c]) {
        ++result;
        cleaned[r][c] = true;
      }

      int nextR = r + R_OFFSETS[direction];
      int nextC = c + C_OFFSETS[direction];
      if (nextR >= 0 && nextR < m && nextC >= 0 && nextC < n && room[nextR][nextC] == 0) {
        r = nextR;
        c = nextC;
      } else {
        direction = (direction + 1) % R_OFFSETS.length;
      }
    }

    return result;
  }
}