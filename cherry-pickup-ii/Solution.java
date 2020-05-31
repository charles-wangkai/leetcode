class Solution {
    public int cherryPickup(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        int[][][] cherrySums = new int[row][col][col];
        for (int r = 0; r < row; ++r) {
            for (int i = 0; i < col; ++i) {
                for (int j = 0; j < col; ++j) {
                    cherrySums[r][i][j] = Integer.MIN_VALUE;
                }
            }
        }
        cherrySums[0][0][col - 1] = grid[0][0] + grid[0][col - 1];

        for (int r = 0; r < row - 1; ++r) {
            for (int i = 0; i < col; ++i) {
                for (int j = i; j < col; ++j) {
                    if (cherrySums[r][i][j] != Integer.MIN_VALUE) {
                        for (int offsetI = -1; offsetI <= 1; ++offsetI) {
                            for (int offsetJ = -1; offsetJ <= 1; ++offsetJ) {
                                int nextI = i + offsetI;
                                int nextJ = j + offsetJ;
                                if (nextI >= 0 && nextI < col && nextJ >= 0 && nextJ < col && nextI <= nextJ) {
                                    cherrySums[r + 1][nextI][nextJ] = Math.max(cherrySums[r + 1][nextI][nextJ],
                                            cherrySums[r][i][j] + grid[r + 1][nextI]
                                                    + (nextI == nextJ ? 0 : grid[r + 1][nextJ]));
                                }
                            }
                        }
                    }
                }
            }
        }

        int result = 0;
        for (int i = 0; i < col; ++i) {
            for (int j = i; j < col; ++j) {
                result = Math.max(result, cherrySums[row - 1][i][j]);
            }
        }

        return result;
    }
}