import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int shortestDistance(int[][] maze, int[] start, int[] destination) {
    int row = maze.length;
    int col = maze[0].length;

    boolean[][] visited = new boolean[row][col];

    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(e -> e.distance));
    pq.offer(new Element(start[0], start[1], 0));

    while (!pq.isEmpty()) {
      Element head = pq.poll();

      if (visited[head.r][head.c]) {
        continue;
      }
      visited[head.r][head.c] = true;

      if (head.r == destination[0] && head.c == destination[1]) {
        return head.distance;
      }

      for (int i = 0; i < R_OFFSETS.length; i++) {
        int nextR = head.r;
        int nextC = head.c;
        int step = 0;

        while (isEmptySpace(maze, nextR + R_OFFSETS[i], nextC + C_OFFSETS[i])) {
          nextR += R_OFFSETS[i];
          nextC += C_OFFSETS[i];
          step++;
        }

        pq.offer(new Element(nextR, nextC, head.distance + step));
      }
    }
    return -1;
  }

  boolean isEmptySpace(int[][] maze, int r, int c) {
    int row = maze.length;
    int col = maze[0].length;

    return r >= 0 && r < row && c >= 0 && c < col && maze[r][c] == 0;
  }
}

class Element {
  int r;
  int c;
  int distance;

  Element(int r, int c, int distance) {
    this.r = r;
    this.c = c;
    this.distance = distance;
  }
}
