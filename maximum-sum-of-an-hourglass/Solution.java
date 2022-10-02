class Solution {
  static final int[] R_OFFSETS = {0, 0, 0, 1, 2, 2, 2};
  static final int[] C_OFFSETS = {0, 1, 2, 1, 0, 1, 2};

  public int maxSum(int[][] grid) {
    int result = 0;
    for (int r = 0; r + 2 < grid.length; ++r) {
      for (int c = 0; c + 2 < grid[0].length; ++c) {
        int sum = 0;
        for (int i = 0; i < R_OFFSETS.length; ++i) {
          sum += grid[r + R_OFFSETS[i]][c + C_OFFSETS[i]];
        }

        result = Math.max(result, sum);
      }
    }

    return result;
  }
}
