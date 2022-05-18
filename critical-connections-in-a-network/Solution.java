// https://www.geeksforgeeks.org/bridge-in-a-graph/

import java.util.ArrayList;
import java.util.List;

class Solution {
  int time;

  public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
    @SuppressWarnings("unchecked")
    List<Integer>[] edgeLists = new List[n];
    for (int i = 0; i < edgeLists.length; ++i) {
      edgeLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < connections.size(); ++i) {
      edgeLists[connections.get(i).get(0)].add(i);
      edgeLists[connections.get(i).get(1)].add(i);
    }

    List<List<Integer>> bridgeEdges = new ArrayList<>();
    time = 0;
    search(bridgeEdges, connections, edgeLists, new int[n], new int[n], -1, 0);

    return bridgeEdges;
  }

  void search(
      List<List<Integer>> bridgeEdges,
      List<List<Integer>> connections,
      List<Integer>[] edgeLists,
      int[] times,
      int[] lows,
      int parent,
      int node) {
    ++time;
    times[node] = time;
    lows[node] = time;

    for (int edge : edgeLists[node]) {
      List<Integer> connection = connections.get(edge);
      int adj = (connection.get(0) == node) ? connection.get(1) : connection.get(0);

      if (times[adj] == 0) {
        search(bridgeEdges, connections, edgeLists, times, lows, node, adj);

        lows[node] = Math.min(lows[node], lows[adj]);

        if (lows[adj] > times[node]) {
          bridgeEdges.add(connection);
        }
      } else if (adj != parent) {
        lows[node] = Math.min(lows[node], times[adj]);
      }
    }
  }
}
