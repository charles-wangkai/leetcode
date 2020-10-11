import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
    int[][] distances = new int[n][n];
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < n; ++j) {
        distances[i][j] = (i == j) ? 0 : Integer.MAX_VALUE;
      }
    }
    for (int[] edge : edges) {
      distances[edge[0] - 1][edge[1] - 1] = 1;
      distances[edge[1] - 1][edge[0] - 1] = 1;
    }
    for (int k = 0; k < n; ++k) {
      for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
          if (distances[i][k] != Integer.MAX_VALUE && distances[k][j] != Integer.MAX_VALUE) {
            distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);
          }
        }
      }
    }

    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0] - 1].add(edge[1] - 1);
      adjLists[edge[1] - 1].add(edge[0] - 1);
    }

    int[] result = new int[n - 1];
    for (int code = 1; code < 1 << n; ++code) {
      boolean[] chosens = decode(n, code);
      if (isSubtree(adjLists, chosens)) {
        int d = 0;
        for (int i = 0; i < n; ++i) {
          if (chosens[i]) {
            for (int j = 0; j < n; ++j) {
              if (chosens[j]) {
                d = Math.max(d, distances[i][j]);
              }
            }
          }
        }

        if (d != 0) {
          ++result[d - 1];
        }
      }
    }

    return result;
  }

  boolean[] decode(int size, int code) {
    boolean[] result = new boolean[size];
    for (int i = 0; i < result.length; ++i) {
      result[i] = (code & 1) != 0;
      code >>= 1;
    }

    return result;
  }

  boolean isSubtree(List<Integer>[] adjLists, boolean[] chosens) {
    int n = adjLists.length;

    boolean[] visited = new boolean[n];
    int startNode = IntStream.range(0, n).filter(i -> chosens[i]).findAny().getAsInt();
    search(adjLists, chosens, visited, startNode);

    return IntStream.range(0, n).allMatch(i -> !chosens[i] || visited[i]);
  }

  void search(List<Integer>[] adjLists, boolean[] chosens, boolean[] visited, int node) {
    visited[node] = true;
    for (int adj : adjLists[node]) {
      if (chosens[adj] && !visited[adj]) {
        search(adjLists, chosens, visited, adj);
      }
    }
  }
}
