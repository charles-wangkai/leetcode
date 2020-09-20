class Solution {
    public int maxProductPath(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        long[][] minProducts = new long[row][col];
        long[][] maxProducts = new long[row][col];
        for (int r = 0; r < row; ++r) {
            for (int c = 0; c < col; ++c) {
                if (r == 0 && c == 0) {
                    minProducts[0][0] = grid[0][0];
                    maxProducts[0][0] = grid[0][0];
                } else {
                    minProducts[r][c] = Long.MAX_VALUE;
                    maxProducts[r][c] = Long.MIN_VALUE;

                    if (r != 0) {
                        minProducts[r][c] = Math.min(minProducts[r][c],
                                Math.min(minProducts[r - 1][c] * grid[r][c], maxProducts[r - 1][c] * grid[r][c]));
                        maxProducts[r][c] = Math.max(maxProducts[r][c],
                                Math.max(minProducts[r - 1][c] * grid[r][c], maxProducts[r - 1][c] * grid[r][c]));
                    }
                    if (c != 0) {
                        minProducts[r][c] = Math.min(minProducts[r][c],
                                Math.min(minProducts[r][c - 1] * grid[r][c], maxProducts[r][c - 1] * grid[r][c]));
                        maxProducts[r][c] = Math.max(maxProducts[r][c],
                                Math.max(minProducts[r][c - 1] * grid[r][c], maxProducts[r][c - 1] * grid[r][c]));
                    }
                }
            }
        }

        return (maxProducts[row - 1][col - 1] >= 0) ? (int) (maxProducts[row - 1][col - 1] % 1_000_000_007) : -1;
    }
}