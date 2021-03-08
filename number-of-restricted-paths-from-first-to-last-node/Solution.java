import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int countRestrictedPaths(int n, int[][] edges) {
    @SuppressWarnings("unchecked")
    List<Integer>[] edgeLists = new List[n];
    for (int i = 0; i < edgeLists.length; ++i) {
      edgeLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < edges.length; ++i) {
      edgeLists[edges[i][0] - 1].add(i);
      edgeLists[edges[i][1] - 1].add(i);
    }

    int[] distances = new int[n];
    Arrays.fill(distances, -1);

    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(e -> e.distance));
    pq.offer(new Element(n - 1, 0));
    while (!pq.isEmpty()) {
      Element head = pq.poll();
      if (distances[head.node] == -1) {
        distances[head.node] = head.distance;

        for (int edgeIndex : edgeLists[head.node]) {
          int adj =
              ((edges[edgeIndex][0] == head.node + 1) ? edges[edgeIndex][1] : edges[edgeIndex][0])
                  - 1;
          if (distances[adj] == -1) {
            pq.offer(new Element(adj, distances[head.node] + edges[edgeIndex][2]));
          }
        }
      }
    }

    int[] pathNums = new int[n];
    pathNums[n - 1] = 1;
    int[] sortedNodes =
        IntStream.range(0, n)
            .boxed()
            .sorted(Comparator.comparing(i -> distances[i]))
            .mapToInt(x -> x)
            .toArray();
    for (int node : sortedNodes) {
      for (int edgeIndex : edgeLists[node]) {
        int adj =
            ((edges[edgeIndex][0] == node + 1) ? edges[edgeIndex][1] : edges[edgeIndex][0]) - 1;
        if (distances[adj] > distances[node]) {
          pathNums[adj] = addMod(pathNums[adj], pathNums[node]);
        }
      }
    }

    return pathNums[0];
  }

  static int addMod(int x, int y) {
    return (x + y) % MODULUS;
  }
}

class Element {
  int node;
  int distance;

  Element(int node, int distance) {
    this.node = node;
    this.distance = distance;
  }
}
