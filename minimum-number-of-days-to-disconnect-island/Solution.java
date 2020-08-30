class Solution {
    static final int[] R_OFFSETS = { -1, 0, 1, 0 };
    static final int[] C_OFFSETS = { 0, 1, 0, -1 };

    public int minDays(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        if (isDisconnected(grid)) {
            return 0;
        }

        for (int r = 0; r < row; ++r) {
            for (int c = 0; c < col; ++c) {
                if (grid[r][c] == 1) {
                    grid[r][c] = 0;

                    if (isDisconnected(grid)) {
                        return 1;
                    }

                    grid[r][c] = 1;
                }
            }
        }

        return 2;
    }

    boolean isDisconnected(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        boolean[][] visited = new boolean[row][col];
        int groupCount = 0;
        for (int r = 0; r < row; ++r) {
            for (int c = 0; c < col; ++c) {
                if (grid[r][c] == 1 && !visited[r][c]) {
                    ++groupCount;
                    search(grid, visited, r, c);
                }
            }
        }

        return groupCount != 1;
    }

    void search(int[][] grid, boolean[][] visited, int r, int c) {
        int row = grid.length;
        int col = grid[0].length;

        visited[r][c] = true;

        for (int i = 0; i < R_OFFSETS.length; ++i) {
            int adjR = r + R_OFFSETS[i];
            int adjC = c + C_OFFSETS[i];

            if (adjR >= 0 && adjR < row && adjC >= 0 && adjC < col && grid[adjR][adjC] == 1 && !visited[adjR][adjC]) {
                search(grid, visited, adjR, adjC);
            }
        }
    }
}