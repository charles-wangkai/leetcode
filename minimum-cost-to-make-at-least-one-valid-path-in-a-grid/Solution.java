import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    static final int[] R_OFFSETS = { Integer.MIN_VALUE, 0, 0, 1, -1 };
    static final int[] C_OFFSETS = { Integer.MIN_VALUE, 1, -1, 0, 0 };

    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] distances = new int[m][n];
        for (int r = 0; r < m; ++r) {
            Arrays.fill(distances[r], -1);
        }
        distances[0][0] = 0;

        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(0, 0));

        while (!queue.isEmpty()) {
            Point head = queue.poll();

            int nextR = head.r + R_OFFSETS[grid[head.r][head.c]];
            int nextC = head.c + C_OFFSETS[grid[head.r][head.c]];
            if (nextR >= 0 && nextR < m && nextC >= 0 && nextC < n
                    && (distances[nextR][nextC] == -1 || distances[head.r][head.c] < distances[nextR][nextC])) {
                distances[nextR][nextC] = distances[head.r][head.c];
                queue.offer(new Point(nextR, nextC));
            }

            for (int i = 1; i < R_OFFSETS.length; ++i) {
                if (i != grid[head.r][head.c]) {
                    int adjR = head.r + R_OFFSETS[i];
                    int adjC = head.c + C_OFFSETS[i];
                    if (adjR >= 0 && adjR < m && adjC >= 0 && adjC < n
                            && (distances[adjR][adjC] == -1 || distances[head.r][head.c] + 1 < distances[adjR][adjC])) {
                        distances[adjR][adjC] = distances[head.r][head.c] + 1;
                        queue.offer(new Point(adjR, adjC));
                    }
                }
            }
        }

        return distances[m - 1][n - 1];
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