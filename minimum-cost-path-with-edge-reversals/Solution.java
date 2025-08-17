import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
  public int minCost(int n, int[][] edges) {
    @SuppressWarnings("unchecked")
    List<Edge>[] edgeLists = new List[n];
    for (int i = 0; i < edgeLists.length; ++i) {
      edgeLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      edgeLists[edge[0]].add(new Edge(edge[1], edge[2]));
      edgeLists[edge[1]].add(new Edge(edge[0], edge[2] * 2));
    }

    int[] distances = new int[n];
    Arrays.fill(distances, -1);

    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(Element::distance));
    pq.offer(new Element(0, 0));

    while (!pq.isEmpty()) {
      Element head = pq.poll();
      if (distances[head.node()] == -1) {
        distances[head.node()] = head.distance();

        for (Edge edge : edgeLists[head.node()]) {
          if (distances[edge.to()] == -1) {
            pq.offer(new Element(edge.to(), head.distance() + edge.cost()));
          }
        }
      }
    }

    return distances[n - 1];
  }
}

record Element(int node, int distance) {}

record Edge(int to, int cost) {}
