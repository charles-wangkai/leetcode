import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

class Solution {
  public int reachableNodes(int[][] edges, int maxMoves, int n) {
    int[][] graph = new int[n][n];
    for (int[] edge : edges) {
      graph[edge[0]][edge[1]] = edge[2] + 1;
      graph[edge[1]][edge[0]] = edge[2] + 1;
    }

    int[] distances = new int[n];
    Arrays.fill(distances, -1);
    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(e -> e.distance));
    pq.offer(new Element(0, 0));
    boolean[] used = new boolean[n];
    while (!pq.isEmpty()) {
      Element element = pq.poll();
      if (used[element.node]) {
        continue;
      }

      used[element.node] = true;
      distances[element.node] = element.distance;

      for (int adj = 0; adj < n; ++adj) {
        if (!used[adj] && graph[element.node][adj] != 0) {
          pq.offer(new Element(adj, distances[element.node] + graph[element.node][adj]));
        }
      }
    }

    int result =
        (int) IntStream.range(0, n).filter(i -> isReachable(distances, maxMoves, i)).count();
    for (int[] edge : edges) {
      int middleNum = 0;
      if (isReachable(distances, maxMoves, edge[0])) {
        middleNum += Math.min(edge[2], maxMoves - distances[edge[0]]);
      }
      if (isReachable(distances, maxMoves, edge[1])) {
        middleNum += Math.min(edge[2], maxMoves - distances[edge[1]]);
      }

      result += Math.min(edge[2], middleNum);
    }

    return result;
  }

  boolean isReachable(int[] distances, int maxMoves, int node) {
    return distances[node] != -1 && distances[node] <= maxMoves;
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
