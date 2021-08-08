class Solution {
  static final int SIZE = 8;
  static final int[] R_OFFSETS = {-1, -1, 0, 1, 1, 1, 0, -1};
  static final int[] C_OFFSETS = {0, 1, 1, 1, 0, -1, -1, -1};

  public boolean checkMove(char[][] board, int rMove, int cMove, char color) {
    if (board[rMove][cMove] != '.') {
      return false;
    }

    for (int i = 0; i < R_OFFSETS.length; ++i) {
      int adjR = rMove + R_OFFSETS[i];
      int adjC = cMove + C_OFFSETS[i];
      if (adjR >= 0
          && adjR < SIZE
          && adjC >= 0
          && adjC < SIZE
          && board[adjR][adjC] != '.'
          && board[adjR][adjC] != color) {
        while (adjR >= 0
            && adjR < SIZE
            && adjC >= 0
            && adjC < SIZE
            && board[adjR][adjC] != '.'
            && board[adjR][adjC] != color) {
          adjR += R_OFFSETS[i];
          adjC += C_OFFSETS[i];
        }

        if (adjR >= 0 && adjR < SIZE && adjC >= 0 && adjC < SIZE && board[adjR][adjC] == color) {
          return true;
        }
      }
    }

    return false;
  }
}
