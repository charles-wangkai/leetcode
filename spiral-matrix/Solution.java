import java.util.ArrayList;
import java.util.List;

class Solution {
  static final int[] R_OFFSETS = {0, 1, 0, -1};
  static final int[] C_OFFSETS = {1, 0, -1, 0};

  public List<Integer> spiralOrder(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;

    List<Integer> result = new ArrayList<>();
    boolean[][] visited = new boolean[m][n];
    int r = 0;
    int c = -1;
    int direction = 0;
    for (int i = 0; i < m * n; ++i) {
      r += R_OFFSETS[direction];
      c += C_OFFSETS[direction];
      if (!(r >= 0 && r < m && c >= 0 && c < n && !visited[r][c])) {
        r -= R_OFFSETS[direction];
        c -= C_OFFSETS[direction];

        direction = (direction + 1) % R_OFFSETS.length;

        r += R_OFFSETS[direction];
        c += C_OFFSETS[direction];
      }

      visited[r][c] = true;
      result.add(matrix[r][c]);
    }

    return result;
  }
}
