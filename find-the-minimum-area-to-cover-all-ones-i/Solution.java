class Solution {
  public int minimumArea(int[][] grid) {
    int minR = Integer.MAX_VALUE;
    int maxR = Integer.MIN_VALUE;
    int minC = Integer.MAX_VALUE;
    int maxC = Integer.MIN_VALUE;
    for (int r = 0; r < grid.length; ++r) {
      for (int c = 0; c < grid[r].length; ++c) {
        if (grid[r][c] == 1) {
          minR = Math.min(minR, r);
          maxR = Math.max(maxR, r);
          minC = Math.min(minC, c);
          maxC = Math.max(maxC, c);
        }
      }
    }

    return (maxR - minR + 1) * (maxC - minC + 1);
  }
}