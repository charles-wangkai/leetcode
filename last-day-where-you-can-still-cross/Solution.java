import java.util.Arrays;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int latestDayToCross(int row, int col, int[][] cells) {
    int[] parents = new int[row * col];
    Arrays.fill(parents, -1);
    for (int c = 0; c < col - 1; ++c) {
      int root1 = findRoot(parents, c);
      int root2 = findRoot(parents, c + 1);
      if (root1 != root2) {
        parents[root2] = root1;
      }
    }
    for (int c = 0; c < col - 1; ++c) {
      int root1 = findRoot(parents, (row - 1) * col + c);
      int root2 = findRoot(parents, (row - 1) * col + c + 1);
      if (root1 != root2) {
        parents[root2] = root1;
      }
    }

    int[][] matrix = new int[row][col];
    for (int r = 0; r < matrix.length; ++r) {
      Arrays.fill(matrix[r], 1);
    }

    for (int i = cells.length - 1; ; --i) {
      int r = cells[i][0] - 1;
      int c = cells[i][1] - 1;

      matrix[r][c] = 0;

      for (int j = 0; j < R_OFFSETS.length; ++j) {
        int adjR = r + R_OFFSETS[j];
        int adjC = c + C_OFFSETS[j];
        if (adjR >= 0 && adjR < row && adjC >= 0 && adjC < col && matrix[adjR][adjC] == 0) {
          int root1 = findRoot(parents, r * col + c);
          int root2 = findRoot(parents, adjR * col + adjC);
          if (root1 != root2) {
            parents[root2] = root1;
          }
        }
      }

      if (findRoot(parents, 0) == findRoot(parents, row * col - 1)) {
        return i;
      }
    }
  }

  int findRoot(int[] parents, int node) {
    if (parents[node] == -1) {
      return node;
    }

    parents[node] = findRoot(parents, parents[node]);

    return parents[node];
  }
}
