import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public int firstCompleteIndex(int[] arr, int[][] mat) {
    int m = mat.length;
    int n = mat[0].length;

    int[] rowRests = new int[m];
    Arrays.fill(rowRests, n);

    int[] colRests = new int[n];
    Arrays.fill(colRests, m);

    Map<Integer, Point> valueToPoint = new HashMap<>();
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        valueToPoint.put(mat[r][c], new Point(r, c));
      }
    }

    for (int i = 0; ; ++i) {
      Point point = valueToPoint.get(arr[i]);
      --rowRests[point.r()];
      --colRests[point.c()];

      if (rowRests[point.r()] == 0 || colRests[point.c()] == 0) {
        return i;
      }
    }
  }
}

record Point(int r, int c) {}
