import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

class Solution {
  public int maxWeight(int n, int[][] edges, int k, int t) {
    @SuppressWarnings("unchecked")
    List<Integer>[] edgeLists = new List[n];
    for (int i = 0; i < edgeLists.length; ++i) {
      edgeLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < edges.length; ++i) {
      edgeLists[edges[i][0]].add(i);
    }

    List<Integer> sorted = topologicalSort(edges, edgeLists);

    @SuppressWarnings("unchecked")
    Set<Integer>[][] weightSums = new Set[n][k + 1];
    for (int i = 0; i < weightSums.length; ++i) {
      for (int j = 0; j < weightSums[i].length; ++j) {
        weightSums[i][j] = new HashSet<>();
      }

      weightSums[i][0].add(0);
    }

    for (int node : sorted) {
      for (int i = 0; i < k; ++i) {
        for (int e : edgeLists[node]) {
          for (int w : weightSums[node][i]) {
            if (w + edges[e][2] < t) {
              weightSums[edges[e][1]][i + 1].add(w + edges[e][2]);
            }
          }
        }
      }
    }

    return IntStream.range(0, n)
        .flatMap(i -> weightSums[i][k].stream().mapToInt(Integer::intValue))
        .max()
        .orElse(-1);
  }

  List<Integer> topologicalSort(int[][] edges, List<Integer>[] edgeLists) {
    int n = edgeLists.length;

    List<Integer> sorted = new ArrayList<>();
    boolean[] visited = new boolean[n];
    for (int i = 0; i < n; ++i) {
      if (!visited[i]) {
        search(sorted, edges, edgeLists, visited, i);
      }
    }
    Collections.reverse(sorted);

    return sorted;
  }

  void search(
      List<Integer> sorted, int[][] edges, List<Integer>[] edgeLists, boolean[] visited, int node) {
    visited[node] = true;

    for (int e : edgeLists[node]) {
      if (!visited[edges[e][1]]) {
        search(sorted, edges, edgeLists, visited, edges[e][1]);
      }
    }

    sorted.add(node);
  }
}