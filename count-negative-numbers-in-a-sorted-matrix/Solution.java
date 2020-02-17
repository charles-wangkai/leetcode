public class Solution {
    public int countNegatives(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int result = 0;
        int c = n;
        for (int r = 0; r < m; ++r) {
            while (c != 0 && grid[r][c - 1] < 0) {
                --c;
            }

            result += n - c;
        }

        return result;
    }
}