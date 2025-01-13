import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public int minMaxWeight(int n, int[][] edges, int threshold) {
    @SuppressWarnings("unchecked")
    List<Integer>[] reversedEdgeLists = new List[n];
    for (int i = 0; i < reversedEdgeLists.length; ++i) {
      reversedEdgeLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < edges.length; ++i) {
      reversedEdgeLists[edges[i][1]].add(i);
    }

    int result = -1;
    int lower = 1;
    int upper = Arrays.stream(edges).mapToInt(edge -> edge[2]).max().getAsInt();
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(edges, reversedEdgeLists, middle)) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  boolean check(int[][] edges, List<Integer>[] reversedEdgeLists, int weightLimit) {
    boolean[] visited = new boolean[reversedEdgeLists.length];
    search(edges, reversedEdgeLists, weightLimit, visited, 0);

    return IntStream.range(0, visited.length).allMatch(i -> visited[i]);
  }

  void search(
      int[][] edges,
      List<Integer>[] reversedEdgeLists,
      int weightLimit,
      boolean[] visited,
      int node) {
    visited[node] = true;

    for (int e : reversedEdgeLists[node]) {
      if (edges[e][2] <= weightLimit && !visited[edges[e][0]]) {
        search(edges, reversedEdgeLists, weightLimit, visited, edges[e][0]);
      }
    }
  }
}