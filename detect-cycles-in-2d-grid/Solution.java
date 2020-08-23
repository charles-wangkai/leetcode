import java.util.ArrayList;
import java.util.List;

class Solution {
    static final int[] R_OFFSETS = { -1, 0, 1, 0 };
    static final int[] C_OFFSETS = { 0, 1, 0, -1 };

    public boolean containsCycle(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        List<Point> toChecks = new ArrayList<>();
        for (int r = 0; r < row; ++r) {
            for (int c = 0; c < col; ++c) {
                toChecks.add(new Point(r, c));
            }
        }

        while (!toChecks.isEmpty()) {
            List<Point> nextToChecks = new ArrayList<>();
            for (Point toCheck : toChecks) {
                if (grid[toCheck.r][toCheck.c] == 0) {
                    continue;
                }

                int sameCount = 0;
                for (int i = 0; i < R_OFFSETS.length; ++i) {
                    int adjR = toCheck.r + R_OFFSETS[i];
                    int adjC = toCheck.c + C_OFFSETS[i];

                    if (adjR >= 0 && adjR < row && adjC >= 0 && adjC < col
                            && grid[adjR][adjC] == grid[toCheck.r][toCheck.c]) {
                        ++sameCount;
                    }
                }

                if (sameCount <= 1) {
                    for (int i = 0; i < R_OFFSETS.length; ++i) {
                        int adjR = toCheck.r + R_OFFSETS[i];
                        int adjC = toCheck.c + C_OFFSETS[i];

                        if (adjR >= 0 && adjR < row && adjC >= 0 && adjC < col
                                && grid[adjR][adjC] == grid[toCheck.r][toCheck.c]) {
                            nextToChecks.add(new Point(adjR, adjC));
                        }
                    }

                    grid[toCheck.r][toCheck.c] = 0;
                }
            }

            toChecks = nextToChecks;
        }

        for (int r = 0; r < row; ++r) {
            for (int c = 0; c < col; ++c) {
                if (grid[r][c] != 0 && fill(grid, r, c) >= 4) {
                    return true;
                }
            }
        }

        return false;
    }

    int fill(char[][] grid, int r, int c) {
        int row = grid.length;
        int col = grid[0].length;

        char value = grid[r][c];
        grid[r][c] = 0;

        int result = 1;
        for (int i = 0; i < R_OFFSETS.length; ++i) {
            int adjR = r + R_OFFSETS[i];
            int adjC = c + C_OFFSETS[i];

            if (adjR >= 0 && adjR < row && adjC >= 0 && adjC < col && grid[adjR][adjC] == value) {
                result += fill(grid, adjR, adjC);
            }
        }

        return result;
    }
}

class Point {
    int r;
    int c;

    Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}