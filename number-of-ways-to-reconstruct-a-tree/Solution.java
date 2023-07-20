import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {
  public int checkWays(int[][] pairs) {
    Map<Integer, Set<Integer>> nodeToAdjs = new HashMap<>();
    for (int[] pair : pairs) {
      if (!nodeToAdjs.containsKey(pair[0])) {
        nodeToAdjs.put(pair[0], new HashSet<>());
      }
      nodeToAdjs.get(pair[0]).add(pair[1]);

      if (!nodeToAdjs.containsKey(pair[1])) {
        nodeToAdjs.put(pair[1], new HashSet<>());
      }
      nodeToAdjs.get(pair[1]).add(pair[0]);
    }

    return check(nodeToAdjs);
  }

  int check(Map<Integer, Set<Integer>> nodeToAdjs) {
    List<Integer> roots = new ArrayList<>();
    for (int node : nodeToAdjs.keySet()) {
      if (nodeToAdjs.get(node).size() == nodeToAdjs.size() - 1) {
        roots.add(node);
      }
    }
    if (roots.isEmpty()) {
      return 0;
    }

    int root = roots.get(0);
    nodeToAdjs.values().forEach(adjs -> adjs.remove(root));
    nodeToAdjs.remove(root);

    Map<Integer, Boolean> nodeToVisited = new HashMap<>();
    for (int node : nodeToAdjs.keySet()) {
      nodeToVisited.put(node, false);
    }

    Set<Integer> subResults = new HashSet<>();
    for (int node : nodeToAdjs.keySet()) {
      if (!nodeToVisited.get(node)) {
        List<Integer> component = new ArrayList<>();
        search(nodeToAdjs, nodeToVisited, component, node);

        Map<Integer, Set<Integer>> nextNodeToAdjs = new HashMap<>();
        for (int n : component) {
          nextNodeToAdjs.put(n, nodeToAdjs.get(n));
        }
        subResults.add(check(nextNodeToAdjs));
      }
    }

    if (subResults.contains(0)) {
      return 0;
    }
    if (roots.size() != 1 || subResults.contains(2)) {
      return 2;
    }

    return 1;
  }

  void search(
      Map<Integer, Set<Integer>> nodeToAdjs,
      Map<Integer, Boolean> nodeToVisited,
      List<Integer> component,
      int node) {
    if (nodeToVisited.get(node)) {
      return;
    }
    nodeToVisited.put(node, true);
    component.add(node);

    for (int adj : nodeToAdjs.get(node)) {
      search(nodeToAdjs, nodeToVisited, component, adj);
    }
  }
}
