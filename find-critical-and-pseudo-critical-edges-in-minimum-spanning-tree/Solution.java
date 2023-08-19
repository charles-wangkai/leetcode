import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
    int mstWeight = computeMSTWeight(n, edges, -1, -1);

    List<Integer> criticals = new ArrayList<>();
    List<Integer> pseudos = new ArrayList<>();
    for (int i = 0; i < edges.length; ++i) {
      if (isCritical(n, edges, mstWeight, i)) {
        criticals.add(i);
      } else if (isPseudo(n, edges, mstWeight, i)) {
        pseudos.add(i);
      }
    }

    return List.of(criticals, pseudos);
  }

  Integer computeMSTWeight(int n, int[][] edges, int removedEdgeIndex, int requiredEdgeIndex) {
    int[] sortedIndices =
        IntStream.range(0, edges.length)
            .filter(i -> i != removedEdgeIndex)
            .boxed()
            .sorted(Comparator.comparing(i -> edges[i][2]))
            .mapToInt(Integer::intValue)
            .toArray();

    int[] parents = new int[n];
    Arrays.fill(parents, -1);

    int result = 0;
    if (requiredEdgeIndex != -1) {
      result += union(parents, edges[requiredEdgeIndex]);
    }
    for (int sortedIndex : sortedIndices) {
      result += union(parents, edges[sortedIndex]);
    }

    return (IntStream.range(0, n).map(i -> findRoot(parents, i)).distinct().count() == 1)
        ? result
        : null;
  }

  int union(int[] parents, int[] edge) {
    int root1 = findRoot(parents, edge[0]);
    int root2 = findRoot(parents, edge[1]);
    if (root1 == root2) {
      return 0;
    }

    parents[root2] = root1;

    return edge[2];
  }

  int findRoot(int[] parents, int node) {
    if (parents[node] == -1) {
      return node;
    }

    parents[node] = findRoot(parents, parents[node]);

    return parents[node];
  }

  boolean isCritical(int n, int[][] edges, int mstWeight, int edgeIndex) {
    Integer weight = computeMSTWeight(n, edges, edgeIndex, -1);

    return weight == null || weight != mstWeight;
  }

  boolean isPseudo(int n, int[][] edges, int mstWeight, int edgeIndex) {
    return computeMSTWeight(n, edges, -1, edgeIndex) == mstWeight;
  }
}
