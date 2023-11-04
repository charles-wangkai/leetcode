import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
  public int shortestPathWithHops(int n, int[][] edges, int s, int d, int k) {
    @SuppressWarnings("unchecked")
    List<Integer>[] edgeLists = new List[n];
    for (int i = 0; i < edgeLists.length; ++i) {
      edgeLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < edges.length; ++i) {
      edgeLists[edges[i][0]].add(i);
      edgeLists[edges[i][1]].add(i);
    }

    int[][] distances = new int[n][k + 1];
    for (int i = 0; i < distances.length; ++i) {
      Arrays.fill(distances[i], -1);
    }

    PriorityQueue<Element> queue = new PriorityQueue<>(Comparator.comparing(Element::distance));
    queue.offer(new Element(s, 0, 0));

    while (true) {
      Element head = queue.poll();
      if (head.node() == d) {
        return head.distance();
      }

      if (distances[head.node()][head.hopCount()] == -1) {
        distances[head.node()][head.hopCount()] = head.distance();

        for (int e : edgeLists[head.node()]) {
          int other = (edges[e][0] == head.node()) ? edges[e][1] : edges[e][0];
          if (distances[other][head.hopCount()] == -1) {
            queue.offer(new Element(other, head.hopCount(), head.distance() + edges[e][2]));
          }
          if (head.hopCount() != k && distances[other][head.hopCount() + 1] == -1) {
            queue.offer(new Element(other, head.hopCount() + 1, head.distance()));
          }
        }
      }
    }
  }
}

record Element(int node, int hopCount, int distance) {}
