import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
  public int minCostConnectPoints(int[][] points) {
    int[] parents = new int[points.length];
    Arrays.fill(parents, -1);

    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(e -> e.cost));
    for (int i = 0; i < points.length; ++i) {
      for (int j = i + 1; j < points.length; ++j) {
        pq.offer(
            new Element(
                i,
                j,
                Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1])));
      }
    }

    int result = 0;
    while (!pq.isEmpty()) {
      Element head = pq.poll();

      int root1 = findRoot(parents, head.index1);
      int root2 = findRoot(parents, head.index2);
      if (root1 != root2) {
        result += head.cost;
        parents[root2] = root1;
      }
    }

    return result;
  }

  int findRoot(int[] parents, int node) {
    int root = node;
    while (parents[root] != -1) {
      root = parents[root];
    }

    int p = node;
    while (p != root) {
      int next = parents[p];
      parents[p] = root;

      p = next;
    }

    return root;
  }
}

class Element {
  int index1;
  int index2;
  int cost;

  Element(int index1, int index2, int cost) {
    this.index1 = index1;
    this.index2 = index2;
    this.cost = cost;
  }
}