import java.util.stream.IntStream;

class Solution {
    public boolean isPrintable(int[][] targetGrid) {
        int m = targetGrid.length;
        int n = targetGrid[0].length;

        while (true) {
            boolean changed = false;

            for (int color = 1; color <= 60; ++color) {
                int minR = Integer.MAX_VALUE;
                int maxR = Integer.MIN_VALUE;
                int minC = Integer.MAX_VALUE;
                int maxC = Integer.MIN_VALUE;
                for (int r = 0; r < m; ++r) {
                    for (int c = 0; c < n; ++c) {
                        if (targetGrid[r][c] == color) {
                            minR = Math.min(minR, r);
                            maxR = Math.max(maxR, r);
                            minC = Math.min(minC, c);
                            maxC = Math.max(maxC, c);
                        }
                    }
                }

                if (minR != Integer.MAX_VALUE && canPrint(targetGrid, color, minR, maxR, minC, maxC)) {
                    for (int r = 0; r < m; ++r) {
                        for (int c = 0; c < n; ++c) {
                            if (targetGrid[r][c] == color) {
                                targetGrid[r][c] = -1;
                            }
                        }
                    }

                    changed = true;
                }
            }

            if (!changed) {
                break;
            }
        }

        return IntStream.range(0, m).allMatch(r -> IntStream.range(0, n).allMatch(c -> targetGrid[r][c] == -1));
    }

    boolean canPrint(int[][] targetGrid, int color, int minR, int maxR, int minC, int maxC) {
        for (int r = minR; r <= maxR; ++r) {
            for (int c = minC; c <= maxC; ++c) {
                if (targetGrid[r][c] != color && targetGrid[r][c] != -1) {
                    return false;
                }
            }
        }

        return true;
    }
}