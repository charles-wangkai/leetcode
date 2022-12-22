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
    buildSubtreeSizes(subtreeSizes, adjLists, new boolean[n], 0);

    int[] distanceSums = new int[n];
    distanceSums[0] = computeDistanceSum(adjLists, new boolean[n], 0, 0);

    buildDistanceSums(distanceSums, adjLists, subtreeSizes, new boolean[n], 0);

    return distanceSums;
  }

  void buildDistanceSums(
      int[] distanceSums,
      List<Integer>[] adjLists,
      int[] subtreeSizes,
      boolean[] visited,
      int node) {
    visited[node] = true;

    for (int adj : adjLists[node]) {
      if (!visited[adj]) {
        distanceSums[adj] =
            distanceSums[node] - subtreeSizes[adj] + (distanceSums.length - subtreeSizes[adj]);
        buildDistanceSums(distanceSums, adjLists, subtreeSizes, visited, adj);
      }
    }
  }

  int computeDistanceSum(List<Integer>[] adjLists, boolean[] visited, int node, int distance) {
    visited[node] = true;

    int result = distance;
    for (int adj : adjLists[node]) {
      if (!visited[adj]) {
        result += computeDistanceSum(adjLists, visited, adj, distance + 1);
      }
    }

    return result;
  }

  void buildSubtreeSizes(
      int[] subtreeSizes, List<Integer>[] adjLists, boolean[] visited, int node) {
    visited[node] = true;

    subtreeSizes[node] = 1;
    for (int adj : adjLists[node]) {
      if (!visited[adj]) {
        buildSubtreeSizes(subtreeSizes, adjLists, visited, adj);
        subtreeSizes[node] += subtreeSizes[adj];
      }
    }
  }
}
