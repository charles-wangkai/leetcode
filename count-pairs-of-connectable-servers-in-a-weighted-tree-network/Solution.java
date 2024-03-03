import java.util.ArrayList;
import java.util.List;

class Solution {
  public int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
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

    int[] pairNums = new int[n];
    for (int i = 0; i < n; ++i) {
      search(edges, edgeLists, signalSpeed, pairNums, new boolean[n], new ArrayList<>(), i, 0);
    }
    for (int i = 0; i < pairNums.length; ++i) {
      pairNums[i] /= 2;
    }

    return pairNums;
  }

  void search(
      int[][] edges,
      List<Integer>[] edgeLists,
      int signalSpeed,
      int[] pairNums,
      boolean[] visited,
      List<Integer> centers,
      int node,
      int distance) {
    visited[node] = true;

    if (distance == 0) {
      for (int i = 1; i < centers.size(); ++i) {
        ++pairNums[centers.get(i)];
      }

      centers.add(node);
    }

    for (int edge : edgeLists[node]) {
      int other = (node == edges[edge][0]) ? edges[edge][1] : edges[edge][0];
      if (!visited[other]) {
        search(
            edges,
            edgeLists,
            signalSpeed,
            pairNums,
            visited,
            centers,
            other,
            (distance + edges[edge][2]) % signalSpeed);
      }
    }

    if (distance == 0) {
      centers.remove(centers.size() - 1);
    }
  }
}