import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

class Solution {
  public int[] processQueries(int c, int[][] connections, int[][] queries) {
    int[] parents = new int[c];
    Arrays.fill(parents, -1);

    for (int[] connection : connections) {
      int root1 = findRoot(parents, connection[0] - 1);
      int root2 = findRoot(parents, connection[1] - 1);
      if (root1 != root2) {
        parents[root2] = root1;
      }
    }

    Map<Integer, SortedSet<Integer>> rootToNodes = new HashMap<>();
    for (int node = 0; node < c; ++node) {
      int root = findRoot(parents, node);
      rootToNodes.putIfAbsent(root, new TreeSet<>());
      rootToNodes.get(root).add(node);
    }

    List<Integer> result = new ArrayList<>();
    for (int[] query : queries) {
      int type = query[0];
      int node = query[1] - 1;

      if (type == 1) {
        SortedSet<Integer> nodes = rootToNodes.get(findRoot(parents, node));

        result.add(nodes.isEmpty() ? -1 : ((nodes.contains(node) ? node : nodes.first()) + 1));
      } else {
        rootToNodes.get(findRoot(parents, node)).remove(node);
      }
    }

    return result.stream().mapToInt(Integer::intValue).toArray();
  }

  int findRoot(int[] parents, int node) {
    if (parents[node] == -1) {
      return node;
    }

    parents[node] = findRoot(parents, parents[node]);

    return parents[node];
  }
}