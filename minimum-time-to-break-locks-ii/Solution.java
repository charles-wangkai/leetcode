import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
  public int findMinimumTime(int[] strength) {
    int n = strength.length;

    // Indices:
    // 0 - source
    // [1,n] - locks
    // [n+1,2n] - orders
    // 2n+1 - sink

    List<Edge> edges = new ArrayList<>();

    @SuppressWarnings("unchecked")
    List<Integer>[] edgeLists = new List[2 * n + 2];
    for (int i = 0; i < edgeLists.length; ++i) {
      edgeLists[i] = new ArrayList<>();
    }
    for (int i = 1; i <= n; ++i) {
      addEdges(edges, edgeLists, 0, i, 1, 0);
    }
    for (int i = n + 1; i <= 2 * n; ++i) {
      addEdges(edges, edgeLists, i, 2 * n + 1, 1, 0);
    }
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < n; ++j) {
        addEdges(edges, edgeLists, i + 1, n + j + 1, 1, (strength[i] + j) / (j + 1));
      }
    }

    return computeMinCostFlow(edges, edgeLists, 0, 2 * n + 1, n);
  }

  int computeMinCostFlow(List<Edge> edges, List<Integer>[] edgeLists, int s, int t, int f) {
    int N = edgeLists.length;

    int result = 0;
    int[] h = new int[N];
    while (f != 0) {
      PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(Element::distance));

      int[] prevEdges = new int[N];
      int[] distances = new int[N];
      Arrays.fill(distances, Integer.MAX_VALUE);
      distances[s] = 0;
      pq.offer(new Element(s, 0));
      while (!pq.isEmpty()) {
        Element head = pq.poll();
        if (head.distance() <= distances[head.index()]) {
          for (int e : edgeLists[head.index()]) {
            Edge edge = edges.get(e);
            if (edge.capacity != 0
                && distances[head.index()] + edge.cost + h[head.index()] - h[edge.to]
                    < distances[edge.to]) {
              distances[edge.to] =
                  distances[head.index()] + edge.cost + h[head.index()] - h[edge.to];
              prevEdges[edge.to] = e;

              pq.offer(new Element(edge.to, distances[edge.to]));
            }
          }
        }
      }

      for (int i = 0; i < h.length; ++i) {
        h[i] += distances[i];
      }

      int d = f;
      for (int v = t; v != s; v = edges.get(prevEdges[v]).from) {
        d = Math.min(d, edges.get(prevEdges[v]).capacity);
      }
      f -= d;
      result += d * h[t];

      for (int v = t; v != s; v = edges.get(prevEdges[v]).from) {
        Edge edge = edges.get(prevEdges[v]);

        edge.capacity -= d;
        edges.get(prevEdges[v] ^ 1).capacity += d;
      }
    }

    return result;
  }

  void addEdges(List<Edge> edges, List<Integer>[] edgeLists, int u, int v, int z, int cost) {
    edges.add(new Edge(u, v, z, cost));
    edgeLists[u].add(edges.size() - 1);

    edges.add(new Edge(v, u, 0, -cost));
    edgeLists[v].add(edges.size() - 1);
  }
}

class Edge {
  int from;
  int to;
  int capacity;
  int cost;

  Edge(int from, int to, int capacity, int cost) {
    this.from = from;
    this.to = to;
    this.capacity = capacity;
    this.cost = cost;
  }
}

record Element(int index, int distance) {}
