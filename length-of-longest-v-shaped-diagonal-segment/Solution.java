class Solution {
  static final int[] R_OFFSETS = {-1, -1, 1, 1};
  static final int[] C_OFFSETS = {-1, 1, 1, -1};

  public int lenOfVDiagonal(int[][] grid) {
    int n = grid.length;
    int m = grid[0].length;

    int result = 0;
    Integer[][][][] cache = new Integer[n][m][R_OFFSETS.length][2];
    for (int r = 0; r < n; ++r) {
      for (int c = 0; c < m; ++c) {
        if (grid[r][c] == 1) {
          for (int direction = 0; direction < R_OFFSETS.length; ++direction) {
            result =
                Math.max(
                    result,
                    1
                        + computeMaxLength(
                            grid,
                            r + R_OFFSETS[direction],
                            c + C_OFFSETS[direction],
                            2,
                            direction,
                            true,
                            cache));
          }
        }
      }
    }

    return result;
  }

  int computeMaxLength(
      int[][] grid,
      int r,
      int c,
      int targetValue,
      int direction,
      boolean canTurn,
      Integer[][][][] cache) {
    int n = grid.length;
    int m = grid[0].length;

    if (r < 0 || r >= n || c < 0 || c >= m || grid[r][c] != targetValue) {
      return 0;
    }

    if (cache[r][c][direction][canTurn ? 1 : 0] == null) {
      int result =
          1
              + computeMaxLength(
                  grid,
                  r + R_OFFSETS[direction],
                  c + C_OFFSETS[direction],
                  2 - targetValue,
                  direction,
                  canTurn,
                  cache);
      if (canTurn) {
        result =
            Math.max(
                result,
                1
                    + computeMaxLength(
                        grid,
                        r + R_OFFSETS[(direction + 1) % R_OFFSETS.length],
                        c + C_OFFSETS[(direction + 1) % R_OFFSETS.length],
                        2 - targetValue,
                        (direction + 1) % R_OFFSETS.length,
                        false,
                        cache));
      }

      cache[r][c][direction][canTurn ? 1 : 0] = result;
    }

    return cache[r][c][direction][canTurn ? 1 : 0];
  }
}
