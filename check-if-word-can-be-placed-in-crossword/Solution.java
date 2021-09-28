class Solution {
  public boolean placeWordInCrossword(char[][] board, String word) {
    return canPlaceHorizontally(board, word) || canPlaceHorizontally(transpose(board), word);
  }

  char[][] transpose(char[][] board) {
    int m = board.length;
    int n = board[0].length;

    char[][] result = new char[n][m];
    for (int r = 0; r < n; ++r) {
      for (int c = 0; c < m; ++c) {
        result[r][c] = board[c][r];
      }
    }

    return result;
  }

  boolean canPlaceHorizontally(char[][] board, String word) {
    for (char[] row : board) {
      int beginC = 0;
      for (int c = 0; c <= row.length; ++c) {
        if (c == row.length || row[c] == '#') {
          int endC = c - 1;
          if (endC - beginC + 1 == word.length()
              && (isMatched(row, beginC, 1, word) || isMatched(row, endC, -1, word))) {
            return true;
          }

          beginC = c + 1;
        }
      }
    }

    return false;
  }

  boolean isMatched(char[] row, int fromC, int offset, String word) {
    for (int i = 0; i < word.length(); ++i) {
      char pattern = row[fromC + i * offset];

      if (pattern != ' ' && pattern != word.charAt(i)) {
        return false;
      }
    }

    return true;
  }
}
