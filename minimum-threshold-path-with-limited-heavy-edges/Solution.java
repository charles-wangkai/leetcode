import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
  public int minimumThreshold(int n, int[][] edges, int source, int target, int k) {
    int result = -1;
    int lower = 0;
    int upper = Arrays.stream(edges).mapToInt(edge -> edge[2]).max().orElse(0);
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(n, edges, source, target, k, middle)) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  boolean check(int n, int[][] edges, int source, int target, int k, int threshold) {
    @SuppressWarnings("unchecked")
    List<Integer>[] edgeLists = new List[n];
    for (int i = 0; i < edgeLists.length; ++i) {
      edgeLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < edges.length; ++i) {
      edgeLists[edges[i][0]].add(i);
      edgeLists[edges[i][1]].add(i);
    }

    int[] distances = new int[n];
    Arrays.fill(distances, Integer.MAX_VALUE);

    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(Element::distance));
    pq.offer(new Element(source, 0));

    while (!pq.isEmpty()) {
      Element head = pq.poll();
      if (distances[head.node()] == Integer.MAX_VALUE) {
        distances[head.node()] = head.distance();

        for (int e : edgeLists[head.node()]) {
          int other = (edges[e][0] == head.node()) ? edges[e][1] : edges[e][0];
          if (distances[other] == Integer.MAX_VALUE) {
            pq.offer(
                new Element(other, distances[head.node()] + ((edges[e][2] > threshold) ? 1 : 0)));
          }
        }
      }
    }

    return distances[target] <= k;
  }
}

record Element(int node, int distance) {}
