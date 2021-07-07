import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
  public int kthSmallest(int[][] matrix, int k) {
    int n = matrix.length;

    PriorityQueue<Point> pq = new PriorityQueue<>(Comparator.comparing(p -> matrix[p.r][p.c]));
    pq.offer(new Point(0, 0));

    for (int i = 0; i < k - 1; ++i) {
      Point head = pq.poll();

      if (head.c + 1 != n) {
        pq.offer(new Point(head.r, head.c + 1));
      }
      if (head.c == 0 && head.r + 1 != n) {
        pq.offer(new Point(head.r + 1, head.c));
      }
    }

    return matrix[pq.peek().r][pq.peek().c];
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
