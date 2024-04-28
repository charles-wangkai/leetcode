import java.util.ArrayList;
import java.util.List;

class Solution {
  public int[] sumOfDistancesInTree(int n, int[][] edges) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
      adjLists[edge[1]].add(edge[0]);
    }

    int[] subtreeSizes = new int[n];
    buildSubtreeSizes(subtreeSizes, adjLists, -1, 0);

    int[] distanceSums = new int[n];
    distanceSums[0] = computeDistanceSum(adjLists, -1, 0, 0);

    buildDistanceSums(distanceSums, adjLists, subtreeSizes, -1, 0);

    return distanceSums;
  }

  void buildDistanceSums(
      int[] distanceSums, List<Integer>[] adjLists, int[] subtreeSizes, int parent, int node) {
    for (int adj : adjLists[node]) {
      if (adj != parent) {
        distanceSums[adj] =
            distanceSums[node] - subtreeSizes[adj] + (distanceSums.length - subtreeSizes[adj]);
        buildDistanceSums(distanceSums, adjLists, subtreeSizes, node, adj);
      }
    }
  }

  int computeDistanceSum(List<Integer>[] adjLists, int parent, int node, int distance) {
    int result = distance;
    for (int adj : adjLists[node]) {
      if (adj != parent) {
        result += computeDistanceSum(adjLists, node, adj, distance + 1);
      }
    }

    return result;
  }

  void buildSubtreeSizes(int[] subtreeSizes, List<Integer>[] adjLists, int parent, int node) {
    subtreeSizes[node] = 1;
    for (int adj : adjLists[node]) {
      if (adj != parent) {
        buildSubtreeSizes(subtreeSizes, adjLists, node, adj);
        subtreeSizes[node] += subtreeSizes[adj];
      }
    }
  }
}
