import java.util.HashMap;
import java.util.Map;

class Solution {
  public int longestCycle(int[] edges) {
    int result = -1;
    boolean[] visited = new boolean[edges.length];
    for (int i = 0; i < visited.length; ++i) {
      result = Math.max(result, computeCycleLength(edges, visited, i));
    }

    return result;
  }

  int computeCycleLength(int[] edges, boolean[] visited, int node) {
    Map<Integer, Integer> nodeToIndex = new HashMap<>();
    while (node != -1 && !visited[node]) {
      visited[node] = true;
      nodeToIndex.put(node, nodeToIndex.size());
      node = edges[node];
    }

    return (node != -1 && nodeToIndex.containsKey(node))
        ? (nodeToIndex.size() - nodeToIndex.get(node))
        : -1;
  }
}