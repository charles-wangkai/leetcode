import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    @SuppressWarnings("unchecked")
    Set<Integer>[] adjSets = new Set[n];
    for (int i = 0; i < adjSets.length; ++i) {
      adjSets[i] = new HashSet<>();
    }
    for (int[] edge : edges) {
      adjSets[edge[0]].add(edge[1]);
      adjSets[edge[1]].add(edge[0]);
    }

    Map<Integer, Set<Integer>> degreeToNodes = new HashMap<>();
    for (int i = 0; i < adjSets.length; ++i) {
      addInMap(degreeToNodes, adjSets[i].size(), i);
    }

    int restNum = n;
    while (restNum >= 3) {
      Set<Integer> removedNodes = degreeToNodes.remove(1);
      for (int removedNode : removedNodes) {
        for (int adj : adjSets[removedNode]) {
          int degree = adjSets[adj].size();
          degreeToNodes.get(degree).remove(adj);
          addInMap(degreeToNodes, degree - 1, adj);

          adjSets[adj].remove(removedNode);
        }
      }

      restNum -= removedNodes.size();
    }

    return degreeToNodes.values().stream()
        .flatMap(nodes -> nodes.stream())
        .collect(Collectors.toList());
  }

  void addInMap(Map<Integer, Set<Integer>> degreeToNodes, int degree, int node) {
    if (!degreeToNodes.containsKey(degree)) {
      degreeToNodes.put(degree, new HashSet<>());
    }

    degreeToNodes.get(degree).add(node);
  }
}
