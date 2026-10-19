import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.LongStream;

class Solution {
  public long minCostExcludingMax(int n, int[][] edges) {
    @SuppressWarnings("unchecked")
    List<Integer>[] edgeLists = new List[n];
    for (int i = 0; i < edgeLists.length; ++i) {
      edgeLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < edges.length; ++i) {
      edgeLists[edges[i][0]].add(i);
      edgeLists[edges[i][1]].add(i);
    }

    long[] distances1 = buildDistances(edges, edgeLists, 0);
    long[] distances2 = buildDistances(edges, edgeLists, n - 1);

    return Arrays.stream(edges)
        .flatMapToLong(
            edge ->
                LongStream.of(
                    distances1[edge[0]] + distances2[edge[1]],
                    distances1[edge[1]] + distances2[edge[0]]))
        .min()
        .getAsLong();
  }

  long[] buildDistances(int[][] edges, List<Integer>[] edgeLists, int startNode) {
    int n = edgeLists.length;

    long[] distances = new long[n];
    Arrays.fill(distances, -1);

    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(Element::distance));
    pq.offer(new Element(startNode, 0));

    while (!pq.isEmpty()) {
      Element head = pq.poll();
      if (distances[head.node()] == -1) {
        distances[head.node()] = head.distance();

        for (int e : edgeLists[head.node()]) {
          int adj = (edges[e][0] == head.node()) ? edges[e][1] : edges[e][0];
          if (distances[adj] == -1) {
            pq.offer(new Element(adj, head.distance() + edges[e][2]));
          }
        }
      }
    }

    return distances;
  }
}

record Element(int node, long distance) {}
