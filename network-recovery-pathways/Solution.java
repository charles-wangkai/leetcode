import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
  public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
    int result = -1;
    int lower = 0;
    int upper = Arrays.stream(edges).mapToInt(edge -> edge[2]).max().orElse(0);
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(edges, online, k, middle)) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  boolean check(int[][] edges, boolean[] online, long k, int minCost) {
    int n = online.length;

    @SuppressWarnings("unchecked")
    List<Integer>[] edgeLists = new List[n];
    for (int i = 0; i < edgeLists.length; ++i) {
      edgeLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < edges.length; ++i) {
      edgeLists[edges[i][0]].add(i);
    }

    long[] distances = new long[n];
    Arrays.fill(distances, Long.MAX_VALUE);

    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(Element::distance));
    pq.offer(new Element(0, 0));

    while (!pq.isEmpty()) {
      Element head = pq.poll();
      if (distances[head.node()] == Long.MAX_VALUE) {
        distances[head.node()] = head.distance();
        for (int e : edgeLists[head.node()]) {
          if (online[edges[e][1]]
              && distances[edges[e][1]] == Long.MAX_VALUE
              && edges[e][2] >= minCost) {
            pq.offer(new Element(edges[e][1], head.distance() + edges[e][2]));
          }
        }
      }
    }

    return distances[n - 1] <= k;
  }
}

record Element(int node, long distance) {}
