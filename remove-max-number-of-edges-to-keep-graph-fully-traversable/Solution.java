import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public int maxNumEdgesToRemove(int n, int[][] edges) {
    @SuppressWarnings("unchecked")
    List<int[]>[] typeToEdges = new List[4];
    for (int i = 0; i < typeToEdges.length; ++i) {
      typeToEdges[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      typeToEdges[edge[0]].add(edge);
    }

    int removedCommonEdgeNum = computeRemovedCommonEdgeNum(n, typeToEdges[3]);
    int removedEdgeNum1 = computeRemovedEdgeNum(n, typeToEdges[3], typeToEdges[1]);
    int removedEdgeNum2 = computeRemovedEdgeNum(n, typeToEdges[3], typeToEdges[2]);

    return (removedEdgeNum1 == -1 || removedEdgeNum2 == -1)
        ? -1
        : (removedCommonEdgeNum + removedEdgeNum1 + removedEdgeNum2);
  }

  int computeRemovedCommonEdgeNum(int n, List<int[]> commonEdges) {
    int[] parents = new int[n + 1];
    Arrays.fill(parents, -1);

    int result = 0;
    for (int[] edge : commonEdges) {
      int root1 = findRoot(parents, edge[1]);
      int root2 = findRoot(parents, edge[2]);
      if (root1 != root2) {
        parents[root2] = root1;
      } else {
        ++result;
      }
    }

    return result;
  }

  int computeRemovedEdgeNum(int n, List<int[]> commonEdges, List<int[]> onlyEdges) {
    int[] parents = new int[n + 1];
    Arrays.fill(parents, -1);

    for (int[] edge : commonEdges) {
      int root1 = findRoot(parents, edge[1]);
      int root2 = findRoot(parents, edge[2]);
      if (root1 != root2) {
        parents[root2] = root1;
      }
    }

    int result = 0;
    for (int[] edge : onlyEdges) {
      int root1 = findRoot(parents, edge[1]);
      int root2 = findRoot(parents, edge[2]);
      if (root1 != root2) {
        parents[root2] = root1;
      } else {
        ++result;
      }
    }

    return (IntStream.rangeClosed(1, n).map(i -> findRoot(parents, i)).distinct().count() == 1)
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
