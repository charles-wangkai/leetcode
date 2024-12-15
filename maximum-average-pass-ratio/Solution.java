import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
  public double maxAverageRatio(int[][] classes, int extraStudents) {
    PriorityQueue<Element> pq =
        new PriorityQueue<>(
            Comparator.<Element, Double>comparing(
                    e -> (e.pass() + 1.0) / (e.total() + 1) - (double) e.pass() / e.total())
                .reversed());
    for (int[] cls : classes) {
      pq.offer(new Element(cls[0], cls[1]));
    }

    for (int i = 0; i < extraStudents; ++i) {
      Element head = pq.poll();
      pq.offer(new Element(head.pass() + 1, head.total() + 1));
    }

    return pq.stream().mapToDouble(e -> (double) e.pass() / e.total()).average().getAsDouble();
  }
}

record Element(int pass, int total) {}
