// https://leetcode.com/problems/minimum-weighted-subgraph-with-the-required-paths-ii/solutions/6754998/c-very-easy-to-understand-beginner-friendly-clean-intuitive-solution/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public int[] minimumWeight(int[][] edges, int[][] queries) {
    int n = edges.length + 1;

    @SuppressWarnings("unchecked")
    List<Integer>[] edgeLists = new List[n];
    for (int i = 0; i < edgeLists.length; ++i) {
      edgeLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < edges.length; ++i) {
      edgeLists[edges[i][0]].add(i);
      edgeLists[edges[i][1]].add(i);
    }

    int[] heights = new int[n];
    int[] pathWeights = new int[n];
    int[][] parents = new int[n][Integer.toBinaryString(n).length()];
    search(edges, edgeLists, heights, parents, pathWeights, 0, 0, -1, 0);

    int[] sortedNodes =
        IntStream.range(0, n)
            .boxed()
            .sorted(Comparator.comparing(i -> heights[i]))
            .mapToInt(Integer::intValue)
            .toArray();
    for (int node : sortedNodes) {
      for (int i = 1; i < parents[node].length; ++i) {
        parents[node][i] = (parents[node][i - 1] == -1) ? -1 : parents[parents[node][i - 1]][i - 1];
      }
    }

    return Arrays.stream(queries)
        .mapToInt(
            query ->
                (computeDistance(heights, pathWeights, parents, query[0], query[1])
                        + computeDistance(heights, pathWeights, parents, query[1], query[2])
                        + computeDistance(heights, pathWeights, parents, query[2], query[0]))
                    / 2)
        .toArray();
  }

  int computeDistance(int[] heights, int[] pathWeights, int[][] parents, int node1, int node2) {
    return pathWeights[node1]
        + pathWeights[node2]
        - 2 * pathWeights[findLca(heights, parents, node1, node2)];
  }

  int findLca(int[] heights, int[][] parents, int node1, int node2) {
    if (heights[node1] < heights[node2]) {
      return findLca(heights, parents, node2, node1);
    }

    int diff = heights[node1] - heights[node2];
    for (int i = 0; diff != 0; ++i) {
      if (((diff >> i) & 1) == 1) {
        node1 = parents[node1][i];

        diff -= 1 << i;
      }
    }
    if (node1 == node2) {
      return node1;
    }

    while (true) {
      int index = -1;
      while (parents[node1][index + 1] != parents[node2][index + 1]) {
        ++index;
      }
      if (index == -1) {
        break;
      }

      node1 = parents[node1][index];
      node2 = parents[node2][index];
    }

    return parents[node1][0];
  }

  void search(
      int[][] edges,
      List<Integer>[] edgeLists,
      int[] heights,
      int[][] parents,
      int[] pathWeights,
      int height,
      int pathWeight,
      int parent,
      int node) {
    heights[node] = height;
    parents[node][0] = parent;
    pathWeights[node] = pathWeight;

    for (int edge : edgeLists[node]) {
      int other = (edges[edge][0] == node) ? edges[edge][1] : edges[edge][0];
      if (other != parent) {
        search(
            edges,
            edgeLists,
            heights,
            parents,
            pathWeights,
            height + 1,
            pathWeight + edges[edge][2],
            node,
            other);
      }
    }
  }
}