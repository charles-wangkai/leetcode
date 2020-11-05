import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    int remainNum = n;
    while (remainNum > 2) {
      Set<Integer> removedNodes = degreeToNodes.remove(1);
      for (int removedNode : removedNodes) {
        for (int adj : adjSets[removedNode]) {
          int degree = adjSets[adj].size();
          degreeToNodes.get(degree).remove(adj);
          addInMap(degreeToNodes, degree - 1, adj);

          adjSets[adj].remove(removedNode);
        }
      }

      remainNum -= removedNodes.size();
    }

    return new ArrayList<>(degreeToNodes.get(remainNum == 1 ? 0 : 1));
  }

  void addInMap(Map<Integer, Set<Integer>> degreeToNodes, int degree, int node) {
    if (!degreeToNodes.containsKey(degree)) {
      degreeToNodes.put(degree, new HashSet<>());
    }

    degreeToNodes.get(degree).add(node);
  }
}
