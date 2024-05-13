import java.util.List;

class Solution {
  public int maxScore(List<List<Integer>> grid) {
    int m = grid.size();
    int n = grid.get(0).size();

    int result = Integer.MIN_VALUE;
    int[][] mins = new int[m][n];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if (r != 0 || c != 0) {
          result =
              Math.max(
                  result,
                  grid.get(r).get(c)
                      - Math.min(
                          (r == 0) ? Integer.MAX_VALUE : mins[r - 1][c],
                          (c == 0) ? Integer.MAX_VALUE : mins[r][c - 1]));
        }

        mins[r][c] = grid.get(r).get(c);
        if (r != 0) {
          mins[r][c] = Math.min(mins[r][c], mins[r - 1][c]);
        }
        if (c != 0) {
          mins[r][c] = Math.min(mins[r][c], mins[r][c - 1]);
        }
      }
    }

    return result;
  }
}