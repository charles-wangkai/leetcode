import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public int[] findMedian(int n, int[][] edges, int[][] queries) {
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
    long[] pathWeights = new long[n];
    int[][] parents = new int[n][Integer.toBinaryString(n).length()];
    search(edges, edgeLists, heights, parents, pathWeights, 0, 0, -1, 0);

    for (int i = 1; i < parents[0].length; ++i) {
      for (int node = 0; node < n; ++node) {
        parents[node][i] = (parents[node][i - 1] == -1) ? -1 : parents[parents[node][i - 1]][i - 1];
      }
    }

    return Arrays.stream(queries)
        .mapToInt(
            query -> {
              int from = query[0];
              int to = query[1];
              int lca = findLca(heights, parents, from, to);

              long totalDistance = computeDistance(heights, pathWeights, parents, from, to);
              long upDistance = computeDistance(heights, pathWeights, parents, from, lca);

              int result = -1;
              if (upDistance * 2 >= totalDistance) {
                int lower = heights[lca];
                int upper = heights[from];
                while (lower <= upper) {
                  int middle = (lower + upper) / 2;
                  int node = findAncestor(heights, parents, from, middle);
                  if (computeDistance(heights, pathWeights, parents, from, node) * 2
                      >= totalDistance) {
                    result = node;
                    lower = middle + 1;
                  } else {
                    upper = middle - 1;
                  }
                }
              } else {
                int lower = heights[lca];
                int upper = heights[to];
                while (lower <= upper) {
                  int middle = (lower + upper) / 2;
                  int node = findAncestor(heights, parents, to, middle);
                  if ((upDistance + computeDistance(heights, pathWeights, parents, lca, node)) * 2
                      >= totalDistance) {
                    result = node;
                    upper = middle - 1;
                  } else {
                    lower = middle + 1;
                  }
                }
              }

              return result;
            })
        .toArray();
  }

  int findAncestor(int[] heights, int[][] parents, int node, int targetHeight) {
    int diff = heights[node] - targetHeight;
    for (int i = 0; diff != 0; ++i) {
      if (((diff >> i) & 1) == 1) {
        node = parents[node][i];

        diff -= 1 << i;
      }
    }

    return node;
  }

  long computeDistance(int[] heights, long[] pathWeights, int[][] parents, int node1, int node2) {
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
      long[] pathWeights,
      int height,
      long pathWeight,
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