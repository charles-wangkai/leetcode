import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public int maxNumEdgesToRemove(int n, int[][] edges) {
    Map<Integer, List<int[]>> typeToEdges = new HashMap<>();
    for (int type = 1; type <= 3; ++type) {
      typeToEdges.put(type, new ArrayList<>());
    }
    for (int[] edge : edges) {
      typeToEdges.get(edge[0]).add(edge);
    }

    int removedCommonEdgeNum = computeRemovedCommonEdgeNum(n, typeToEdges.get(3));
    int removedEdgeNum1 = computeRemovedEdgeNum(n, typeToEdges.get(3), typeToEdges.get(1));
    int removedEdgeNum2 = computeRemovedEdgeNum(n, typeToEdges.get(3), typeToEdges.get(2));

    return (removedEdgeNum1 == -1 || removedEdgeNum2 == -1)
        ? -1
        : (removedCommonEdgeNum + removedEdgeNum1 + removedEdgeNum2);
  }

  int computeRemovedCommonEdgeNum(int n, List<int[]> commonEdges) {
    int[] parents = new int[n];
    Arrays.fill(parents, -1);

    int result = 0;
    for (int[] edge : commonEdges) {
      int root1 = findRoot(parents, edge[1] - 1);
      int root2 = findRoot(parents, edge[2] - 1);
      if (root2 == root1) {
        ++result;
      } else {
        parents[root2] = root1;
      }
    }

    return result;
  }

  int computeRemovedEdgeNum(int n, List<int[]> commonEdges, List<int[]> onlyEdges) {
    int[] parents = new int[n];
    Arrays.fill(parents, -1);

    for (int[] edge : commonEdges) {
      int root1 = findRoot(parents, edge[1] - 1);
      int root2 = findRoot(parents, edge[2] - 1);
      if (root2 != root1) {
        parents[root2] = root1;
      }
    }

    int result = 0;
    for (int[] edge : onlyEdges) {
      int root1 = findRoot(parents, edge[1] - 1);
      int root2 = findRoot(parents, edge[2] - 1);
      if (root2 == root1) {
        ++result;
      } else {
        parents[root2] = root1;
      }
    }

    return (IntStream.range(0, n).map(i -> findRoot(parents, i)).distinct().count() == 1)
        ? result
        : -1;
  }

  int findRoot(int[] parents, int node) {
    if (parents[node] == -1) {
      return node;
    }

    parents[node] = findRoot(parents, parents[node]);

    return parents[node];
  }
}
