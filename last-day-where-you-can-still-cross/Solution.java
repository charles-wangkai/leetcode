import java.util.Arrays;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int latestDayToCross(int row, int col, int[][] cells) {
    int[] minParents = new int[row * col];
    Arrays.fill(minParents, -1);

    int[] maxParents = new int[row * col];
    Arrays.fill(maxParents, -1);

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
          int minRoot1 = findRoot(minParents, r * col + c);
          int minRoot2 = findRoot(minParents, adjR * col + adjC);
          int maxRoot1 = findRoot(maxParents, r * col + c);
          int maxRoot2 = findRoot(maxParents, adjR * col + adjC);
          if (minRoot1 != minRoot2) {
            if (minRoot1 > minRoot2) {
              int temp = minRoot1;
              minRoot1 = minRoot2;
              minRoot2 = temp;
            }
            if (maxRoot1 > maxRoot2) {
              int temp = maxRoot1;
              maxRoot1 = maxRoot2;
              maxRoot2 = temp;
            }

            if (minRoot1 / col == 0 && maxRoot2 / col == row - 1) {
              return i;
            }

            minParents[minRoot2] = minRoot1;
            maxParents[maxRoot1] = maxRoot2;
          }
        }
      }
    }
  }

  static int findRoot(int[] parents, int node) {
    int root = node;
    while (parents[root] != -1) {
      root = parents[root];
    }

    int p = node;
    while (p != root) {
      int next = parents[p];
      parents[p] = root;

      p = next;
    }

    return root;
  }
}
