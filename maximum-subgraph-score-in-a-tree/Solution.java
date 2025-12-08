import java.util.ArrayList;
import java.util.List;

class Solution {
  public int[] maxSubgraphScore(int n, int[][] edges, int[] good) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
      adjLists[edge[1]].add(edge[0]);
    }

    int[] subtreeMaxSums = new int[n];
    buildSubtreeMaxSums(subtreeMaxSums, good, adjLists, -1, 0);

    int[] maxScores = new int[n];
    buildMaxScores(maxScores, adjLists, subtreeMaxSums, 0, -1, 0);

    return maxScores;
  }

  void buildMaxScores(
      int[] maxScores,
      List<Integer>[] adjLists,
      int[] subtreeMaxSums,
      int upMaxSum,
      int parent,
      int node) {
    maxScores[node] = subtreeMaxSums[node] + upMaxSum;

    for (int adj : adjLists[node]) {
      if (adj != parent) {
        buildMaxScores(
            maxScores,
            adjLists,
            subtreeMaxSums,
            Math.max(0, maxScores[node] - Math.max(0, subtreeMaxSums[adj])),
            node,
            adj);
      }
    }
  }

  void buildSubtreeMaxSums(
      int[] subtreeMaxSums, int[] good, List<Integer>[] adjLists, int parent, int node) {
    subtreeMaxSums[node] = (good[node] == 1) ? 1 : -1;

    for (int adj : adjLists[node]) {
      if (adj != parent) {
        buildSubtreeMaxSums(subtreeMaxSums, good, adjLists, node, adj);
        subtreeMaxSums[node] += Math.max(0, subtreeMaxSums[adj]);
      }
    }
  }
}