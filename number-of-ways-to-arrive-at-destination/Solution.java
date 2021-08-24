import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int countPaths(int n, int[][] roads) {
    @SuppressWarnings("unchecked")
    List<Integer>[] edgeLists = new List[n];
    for (int i = 0; i < n; ++i) {
      edgeLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < roads.length; ++i) {
      edgeLists[roads[i][0]].add(i);
      edgeLists[roads[i][1]].add(i);
    }

    long[] distances = new long[n];
    Arrays.fill(distances, -1);

    int[] pathCounts = new int[n];
    pathCounts[0] = 1;

    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(e -> e.distance));
    pq.offer(new Element(0, 0));

    while (!pq.isEmpty()) {
      Element head = pq.poll();
      if (distances[head.node] == -1) {
        distances[head.node] = head.distance;

        for (int edge : edgeLists[head.node]) {
          int other = (roads[edge][0] == head.node) ? roads[edge][1] : roads[edge][0];
          if (distances[other] == -1) {
            pq.offer(new Element(other, head.distance + roads[edge][2]));
          } else if (distances[other] + roads[edge][2] == head.distance) {
            pathCounts[head.node] = addMod(pathCounts[head.node], pathCounts[other]);
          }
        }
      }
    }

    return pathCounts[n - 1];
  }

  static int addMod(int x, int y) {
    return (x + y) % MODULUS;
  }
}

class Element {
  int node;
  long distance;

  Element(int node, long distance) {
    this.node = node;
    this.distance = distance;
  }
}
