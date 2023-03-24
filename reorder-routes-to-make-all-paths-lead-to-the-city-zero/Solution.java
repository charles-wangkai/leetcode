import java.util.ArrayList;
import java.util.List;

class Solution {
  public int minReorder(int n, int[][] connections) {
    @SuppressWarnings("unchecked")
    List<Integer>[] edgeLists = new List[n];
    for (int i = 0; i < n; ++i) {
      edgeLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < connections.length; ++i) {
      edgeLists[connections[i][0]].add(i);
      edgeLists[connections[i][1]].add(i);
    }

    return search(connections, edgeLists, -1, 0);
  }

  int search(int[][] connections, List<Integer>[] edgeLists, int parent, int node) {
    int result = 0;
    for (int edge : edgeLists[node]) {
      int[] connection = connections[edge];
      int other = (node == connection[0]) ? connection[1] : connection[0];
      if (other != parent) {
        if (node == connection[0]) {
          ++result;
        }

        result += search(connections, edgeLists, node, other);
      }
    }

    return result;
  }
}
