class Solution {
  public int countNegatives(int[][] grid) {
    int n = grid[0].length;

    int result = 0;
    int c = n;
    for (int[] line : grid) {
      while (c != 0 && line[c - 1] < 0) {
        --c;
      }

      result += n - c;
    }

    return result;
  }
}
