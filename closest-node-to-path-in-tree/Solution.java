import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public int[] closestNode(int n, int[][] edges, int[][] query) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
      adjLists[edge[1]].add(edge[0]);
    }

    int[][] distances = new int[n][n];
    for (int i = 0; i < distances.length; ++i) {
      buildDistances(adjLists, distances[i], new boolean[n], i, 0);
    }

    return Arrays.stream(query)
        .mapToInt(
            q ->
                IntStream.range(0, n)
                    .filter(i -> distances[q[0]][i] + distances[i][q[1]] == distances[q[0]][q[1]])
                    .boxed()
                    .min(Comparator.comparing(i -> distances[i][q[2]]))
                    .get())
        .toArray();
  }

  void buildDistances(
      List<Integer>[] adjLists, int[] d, boolean[] visited, int node, int distance) {
    d[node] = distance;
    visited[node] = true;

    for (int adj : adjLists[node]) {
      if (!visited[adj]) {
        buildDistances(adjLists, d, visited, adj, distance + 1);
      }
    }
  }
}
