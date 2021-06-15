// https://leetcode.com/problems/find-a-peak-element-ii/discuss/1274069/JavaScript-O(mlgn)-with-explanation-and-proof

import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int[] findPeakGrid(int[][] mat) {
    int m = mat.length;
    int n = mat[0].length;

    int lowerC = 0;
    int upperC = n - 1;
    while (true) {
      int middleC = (lowerC + upperC) / 2;

      int maxR =
          IntStream.range(0, m).boxed().max(Comparator.comparing(r -> mat[r][middleC])).get();
      if (middleC + 1 != n && mat[maxR][middleC] < mat[maxR][middleC + 1]) {
        int[] subResult = findPeakGrid(buildSubMatrix(mat, 0, middleC + 1, m - 1, n - 1));

        return new int[] {subResult[0], subResult[1] + middleC + 1};
      } else if (middleC != 0 && mat[maxR][middleC] < mat[maxR][middleC - 1]) {
        return findPeakGrid(buildSubMatrix(mat, 0, 0, m - 1, middleC - 1));
      } else {
        return new int[] {maxR, middleC};
      }
    }
  }

  int[][] buildSubMatrix(int[][] mat, int minR, int minC, int maxR, int maxC) {
    int row = maxR - minR + 1;
    int col = maxC - minC + 1;

    int[][] result = new int[row][col];
    for (int r = 0; r < row; ++r) {
      for (int c = 0; c < col; ++c) {
        result[r][c] = mat[minR + r][minC + c];
      }
    }

    return result;
  }
}
