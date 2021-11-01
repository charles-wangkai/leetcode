import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public void solve(char[][] board) {
    fill(board);

    replace(board, 'O', 'X');
    replace(board, '_', 'O');
  }

  void fill(char[][] board) {
    int row = board.length;
    int col = board[0].length;

    Queue<Point> queue = new ArrayDeque<>();
    for (int r = 0; r < row; ++r) {
      for (int c = 0; c < col; ++c) {
        if ((r == 0 || r == row - 1 || c == 0 || c == col - 1) && board[r][c] == 'O') {
          board[r][c] = '_';
          queue.offer(new Point(r, c));
        }
      }
    }

    while (!queue.isEmpty()) {
      Point head = queue.poll();
      for (int i = 0; i < R_OFFSETS.length; ++i) {
        int adjR = head.r + R_OFFSETS[i];
        int adjC = head.c + C_OFFSETS[i];
        if (adjR >= 0 && adjR < row && adjC >= 0 && adjC < col && board[adjR][adjC] == 'O') {
          board[adjR][adjC] = '_';
          queue.offer(new Point(adjR, adjC));
        }
      }
    }
  }

  void replace(char[][] board, char from, char to) {
    int row = board.length;
    int col = board[0].length;

    for (int r = 0; r < row; ++r) {
      for (int c = 0; c < col; ++c) {
        if (board[r][c] == from) {
          board[r][c] = to;
        }
      }
    }
  }
}

class Point {
  int r;
  int c;

  Point(int r, int c) {
    this.r = r;
    this.c = c;
  }
}
