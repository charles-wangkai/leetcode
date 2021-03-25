import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  static int[] R_OFFSETS = {-1, 0, 1, 0};
  static int[] C_OFFSETS = {0, 1, 0, -1};

  public List<List<Integer>> pacificAtlantic(int[][] matrix) {
    int row = matrix.length;
    if (row == 0) {
      return List.of();
    }
    int col = matrix[0].length;
    if (col == 0) {
      return List.of();
    }

    boolean[][] pacific = bfs(matrix, 0, 0);
    boolean[][] atlantic = bfs(matrix, row - 1, col - 1);

    return IntStream.range(0, row)
        .boxed()
        .flatMap(
            r ->
                IntStream.range(0, col)
                    .filter(c -> pacific[r][c] && atlantic[r][c])
                    .mapToObj(c -> List.of(r, c)))
        .collect(Collectors.toList());
  }

  boolean[][] bfs(int[][] matrix, int edgeR, int edgeC) {
    int row = matrix.length;
    int col = matrix[0].length;

    boolean[][] reaches = new boolean[row][col];
    Queue<Point> queue = new ArrayDeque<>();
    for (int r = 0; r < row; ++r) {
      for (int c = 0; c < col; ++c) {
        if (r == edgeR || c == edgeC) {
          reaches[r][c] = true;
          queue.offer(new Point(r, c));
        }
      }
    }

    while (!queue.isEmpty()) {
      Point head = queue.poll();

      for (int i = 0; i < R_OFFSETS.length; ++i) {
        int adjR = head.r + R_OFFSETS[i];
        int adjC = head.c + C_OFFSETS[i];

        if (adjR >= 0
            && adjR < row
            && adjC >= 0
            && adjC < col
            && !reaches[adjR][adjC]
            && matrix[adjR][adjC] >= matrix[head.r][head.c]) {
          reaches[adjR][adjC] = true;
          queue.offer(new Point(adjR, adjC));
        }
      }
    }

    return reaches;
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
