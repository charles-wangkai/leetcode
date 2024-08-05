import java.util.HashMap;
import java.util.Map;

class neighborSum {
  static final int[] ADJACENT_R_OFFSETS = {-1, 0, 1, 0};
  static final int[] ADJACENT_C_OFFSETS = {0, 1, 0, -1};
  static final int[] DIAGONAL_R_OFFSETS = {-1, -1, 1, 1};
  static final int[] DIAGONAL_C_OFFSETS = {-1, 1, 1, -1};

  int[][] grid;
  Map<Integer, Point> valueToPoint = new HashMap<>();

  public neighborSum(int[][] grid) {
    this.grid = grid;

    int n = grid.length;
    for (int r = 0; r < n; ++r) {
      for (int c = 0; c < n; ++c) {
        valueToPoint.put(grid[r][c], new Point(r, c));
      }
    }
  }

  public int adjacentSum(int value) {
    int n = grid.length;

    int result = 0;
    for (int i = 0; i < ADJACENT_R_OFFSETS.length; ++i) {
      int adjacentR = valueToPoint.get(value).r() + ADJACENT_R_OFFSETS[i];
      int adjacentC = valueToPoint.get(value).c() + ADJACENT_C_OFFSETS[i];
      if (adjacentR >= 0 && adjacentR < n && adjacentC >= 0 && adjacentC < n) {
        result += grid[adjacentR][adjacentC];
      }
    }

    return result;
  }

  public int diagonalSum(int value) {
    int n = grid.length;

    int result = 0;
    for (int i = 0; i < DIAGONAL_R_OFFSETS.length; ++i) {
      int diagonalR = valueToPoint.get(value).r() + DIAGONAL_R_OFFSETS[i];
      int diagonalC = valueToPoint.get(value).c() + DIAGONAL_C_OFFSETS[i];
      if (diagonalR >= 0 && diagonalR < n && diagonalC >= 0 && diagonalC < n) {
        result += grid[diagonalR][diagonalC];
      }
    }

    return result;
  }
}

record Point(int r, int c) {}

// Your neighborSum object will be instantiated and called as such:
// neighborSum obj = new neighborSum(grid);
// int param_1 = obj.adjacentSum(value);
// int param_2 = obj.diagonalSum(value);
