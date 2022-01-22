import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public List<List<Integer>> highestRankedKItems(int[][] grid, int[] pricing, int[] start, int k) {
    int m = grid.length;
    int n = grid[0].length;

    int[][] distances = new int[m][n];
    for (int r = 0; r < distances.length; ++r) {
      Arrays.fill(distances[r], Integer.MAX_VALUE);
    }
    distances[start[0]][start[1]] = 0;
    Queue<Point> queue = new ArrayDeque<>();
    queue.offer(new Point(start[0], start[1]));
    while (!queue.isEmpty()) {
      Point head = queue.poll();

      for (int i = 0; i < R_OFFSETS.length; ++i) {
        int adjR = head.r + R_OFFSETS[i];
        int adjC = head.c + C_OFFSETS[i];
        if (adjR >= 0
            && adjR < m
            && adjC >= 0
            && adjC < n
            && grid[adjR][adjC] != 0
            && distances[adjR][adjC] == Integer.MAX_VALUE) {
          distances[adjR][adjC] = distances[head.r][head.c] + 1;
          queue.offer(new Point(adjR, adjC));
        }
      }
    }

    return IntStream.range(0, m)
        .boxed()
        .flatMap(r -> IntStream.range(0, n).mapToObj(c -> List.of(r, c)))
        .filter(
            p ->
                grid[p.get(0)][p.get(1)] >= pricing[0]
                    && grid[p.get(0)][p.get(1)] <= pricing[1]
                    && distances[p.get(0)][p.get(1)] != Integer.MAX_VALUE)
        .sorted(
            Comparator.comparing((List<Integer> p) -> distances[p.get(0)][p.get(1)])
                .thenComparing(p -> grid[p.get(0)][p.get(1)])
                .thenComparing(p -> p.get(0))
                .thenComparing(p -> p.get(1)))
        .limit(k)
        .collect(Collectors.toList());
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