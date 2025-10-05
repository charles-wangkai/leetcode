import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public List<List<Integer>> pacificAtlantic(int[][] heights) {
    int m = heights.length;
    int n = heights[0].length;

    boolean[][] pacific = bfs(heights, 0, 0);
    boolean[][] atlantic = bfs(heights, m - 1, n - 1);

    return IntStream.range(0, m)
        .boxed()
        .flatMap(
            r ->
                IntStream.range(0, n)
                    .filter(c -> pacific[r][c] && atlantic[r][c])
                    .mapToObj(c -> List.of(r, c)))
        .toList();
  }

  boolean[][] bfs(int[][] heights, int edgeR, int edgeC) {
    int m = heights.length;
    int n = heights[0].length;

    boolean[][] reaches = new boolean[m][n];
    Queue<Point> queue = new ArrayDeque<>();
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if (r == edgeR || c == edgeC) {
          reaches[r][c] = true;
          queue.offer(new Point(r, c));
        }
      }
    }

    while (!queue.isEmpty()) {
      Point head = queue.poll();

      for (int i = 0; i < R_OFFSETS.length; ++i) {
        int adjR = head.r() + R_OFFSETS[i];
        int adjC = head.c() + C_OFFSETS[i];
        if (adjR >= 0
            && adjR < m
            && adjC >= 0
            && adjC < n
            && !reaches[adjR][adjC]
            && heights[adjR][adjC] >= heights[head.r()][head.c()]) {
          reaches[adjR][adjC] = true;
          queue.offer(new Point(adjR, adjC));
        }
      }
    }

    return reaches;
  }
}

record Point(int r, int c) {}
