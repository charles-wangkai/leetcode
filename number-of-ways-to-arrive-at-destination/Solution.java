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

    int[] pathNums = new int[n];
    pathNums[0] = 1;

    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(Element::distance));
    pq.offer(new Element(0, 0));

    while (!pq.isEmpty()) {
      Element head = pq.poll();
      if (distances[head.node()] == -1) {
        distances[head.node()] = head.distance();

        for (int edge : edgeLists[head.node()]) {
          int other = (roads[edge][0] == head.node()) ? roads[edge][1] : roads[edge][0];
          if (distances[other] == -1) {
            pq.offer(new Element(other, head.distance() + roads[edge][2]));
          } else if (distances[other] + roads[edge][2] == head.distance()) {
            pathNums[head.node()] = addMod(pathNums[head.node()], pathNums[other]);
          }
        }
      }
    }

    return pathNums[n - 1];
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}

record Element(int node, long distance) {}
