class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public boolean exist(char[][] board, String word) {
    int m = board.length;
    int n = board[0].length;

    boolean[][] visited = new boolean[m][n];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if (check(board, word, visited, r, c, 0)) {
          return true;
        }
      }
    }

    return false;
  }

  boolean check(char[][] board, String word, boolean[][] visited, int r, int c, int index) {
    int m = board.length;
    int n = board[0].length;

    if (index == word.length()) {
      return true;
    }

    if (!(r >= 0 && r < m && c >= 0 && c < n)
        || visited[r][c]
        || board[r][c] != word.charAt(index)) {
      return false;
    }

    boolean result = false;
    visited[r][c] = true;
    for (int i = 0; i < R_OFFSETS.length; ++i) {
      if (check(board, word, visited, r + R_OFFSETS[i], c + C_OFFSETS[i], index + 1)) {
        result = true;

        break;
      }
    }
    visited[r][c] = false;

    return result;
  }
}
