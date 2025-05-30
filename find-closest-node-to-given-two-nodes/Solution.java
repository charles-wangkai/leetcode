import java.util.Arrays;

class Solution {
  public int closestMeetingNode(int[] edges, int node1, int node2) {
    int n = edges.length;

    int[] distances1 = computeDistances(edges, node1);
    int[] distances2 = computeDistances(edges, node2);

    int minMax = Integer.MAX_VALUE;
    int result = -1;
    for (int i = 0; i < n; ++i) {
      int max = Math.max(distances1[i], distances2[i]);
      if (max < minMax) {
        minMax = max;
        result = i;
      }
    }

    return result;
  }

  int[] computeDistances(int[] edges, int node) {
    int[] result = new int[edges.length];
    Arrays.fill(result, Integer.MAX_VALUE);

    int distance = 0;
    while (node != -1 && result[node] == Integer.MAX_VALUE) {
      result[node] = distance;

      node = edges[node];
      ++distance;
    }

    return result;
  }
}
