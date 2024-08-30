// https://leetcode.com/problems/modify-graph-edge-weights/solutions/3546829/c-solution/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

class Solution {
  static final int INF = 2_000_000_000;

  public int[][] modifiedGraphEdges(int n, int[][] edges, int source, int destination, int target) {
    int[] todoEdgeIndices =
        IntStream.range(0, edges.length).filter(i -> edges[i][2] == -1).toArray();

    int[] distances = computeDistances(n, edges, source, destination);
    if (distances[destination] < target) {
      return new int[0][];
    }
    if (distances[destination] == target) {
      for (int todoEdgeIndex : todoEdgeIndices) {
        edges[todoEdgeIndex][2] = INF;
      }

      return edges;
    }

    for (int i = 0; i < todoEdgeIndices.length; ++i) {
      edges[todoEdgeIndices[i]][2] = 1;
      distances = computeDistances(n, edges, source, destination);
      if (distances[destination] <= target) {
        edges[todoEdgeIndices[i]][2] += target - distances[destination];

        for (int j = i + 1; j < todoEdgeIndices.length; ++j) {
          edges[todoEdgeIndices[j]][2] = INF;
        }

        return edges;
      }
    }

    return new int[0][];
  }

  int[] computeDistances(int n, int[][] edges, int source, int destination) {
    @SuppressWarnings("unchecked")
    List<Integer>[] edgeLists = new List[n];
    for (int i = 0; i < edgeLists.length; ++i) {
      edgeLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < edges.length; ++i) {
      if (edges[i][2] != -1) {
        edgeLists[edges[i][0]].add(i);
        edgeLists[edges[i][1]].add(i);
      }
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
            pq.offer(new Element(other, distances[head.node()] + edges[e][2]));
          }
        }
      }
    }

    return distances;
  }
}

record Element(int node, int distance) {}
