import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
  public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {
    List<Integer>[] edgeLists = buildEdgeLists(n, edges);

    int[][] reversedEdges =
        Arrays.stream(edges)
            .map(edge -> new int[] {edge[1], edge[0], edge[2]})
            .toArray(int[][]::new);
    List<Integer>[] reversedEdgeLists = buildEdgeLists(n, reversedEdges);

    long[] distancesFromSrc1 = computeDistances(edges, edgeLists, src1);
    long[] distancesFromSrc2 = computeDistances(edges, edgeLists, src2);
    long[] distancesFromDest = computeDistances(reversedEdges, reversedEdgeLists, dest);

    long result = Long.MAX_VALUE;
    for (int i = 0; i < n; ++i) {
      if (distancesFromSrc1[i] != Long.MAX_VALUE
          && distancesFromSrc2[i] != Long.MAX_VALUE
          && distancesFromDest[i] != Long.MAX_VALUE) {
        result =
            Math.min(result, distancesFromSrc1[i] + distancesFromSrc2[i] + distancesFromDest[i]);
      }
    }

    return (result == Long.MAX_VALUE) ? -1 : result;
  }

  List<Integer>[] buildEdgeLists(int n, int[][] edges) {
    @SuppressWarnings("unchecked")
    List<Integer>[] edgeLists = new List[n];
    for (int i = 0; i < edgeLists.length; ++i) {
      edgeLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < edges.length; ++i) {
      edgeLists[edges[i][0]].add(i);
    }

    return edgeLists;
  }

  long[] computeDistances(int[][] edges, List<Integer>[] edgeLists, int source) {
    long[] distances = new long[edgeLists.length];
    Arrays.fill(distances, Long.MAX_VALUE);

    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(e -> e.distance));
    pq.offer(new Element(source, 0));
    while (!pq.isEmpty()) {
      Element head = pq.poll();
      if (distances[head.v] == Long.MAX_VALUE) {
        distances[head.v] = head.distance;

        for (int edgeIndex : edgeLists[head.v]) {
          int[] edge = edges[edgeIndex];
          if (distances[edge[1]] == Long.MAX_VALUE) {
            pq.offer(new Element(edge[1], head.distance + edge[2]));
          }
        }
      }
    }

    return distances;
  }
}

class Element {
  int v;
  long distance;

  Element(int v, long distance) {
    this.v = v;
    this.distance = distance;
  }
}