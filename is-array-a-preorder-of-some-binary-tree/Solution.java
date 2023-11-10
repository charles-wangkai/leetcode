import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {
  int index;

  public boolean isPreorder(List<List<Integer>> nodes) {
    if (nodes.get(0).get(1) != -1) {
      return false;
    }

    Map<Integer, Set<Integer>> nodeToChildren = new HashMap<>();
    for (List<Integer> node : nodes) {
      nodeToChildren.putIfAbsent(node.get(0), new HashSet<>());
      if (node.get(1) != -1) {
        nodeToChildren.putIfAbsent(node.get(1), new HashSet<>());
        nodeToChildren.get(node.get(1)).add(node.get(0));
      }
    }

    index = 0;
    return search(nodes, nodeToChildren, nodes.get(0).get(0));
  }

  boolean search(List<List<Integer>> nodes, Map<Integer, Set<Integer>> nodeToChildren, int node) {
    ++index;

    while (!nodeToChildren.get(node).isEmpty()) {
      int nextNode = nodes.get(index).get(0);
      if (!nodeToChildren.get(node).contains(nextNode)) {
        return false;
      }

      nodeToChildren.get(node).remove(nextNode);
      if (!search(nodes, nodeToChildren, nextNode)) {
        return false;
      }
    }

    return true;
  }
}
