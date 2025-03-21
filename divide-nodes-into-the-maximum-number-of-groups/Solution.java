import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Solution {
  public int magnificentSets(int n, int[][] edges) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0] - 1].add(edge[1] - 1);
      adjLists[edge[1] - 1].add(edge[0] - 1);
    }

    int result = 0;
    boolean[] visited = new boolean[n];
    for (int i = 0; i < visited.length; ++i) {
      if (!visited[i]) {
        List<Integer> component = new ArrayList<>();
        search(component, adjLists, visited, i);

        int groupNum = 0;
        for (int beginNode : component) {
          int maxDistance = computeMaxDistance(adjLists, beginNode);
          if (maxDistance == -1) {
            return -1;
          }

          groupNum = Math.max(groupNum, maxDistance + 1);
        }
        result += groupNum;
      }
    }

    return result;
  }

  int computeMaxDistance(List<Integer>[] adjLists, int beginNode) {
    Map<Integer, Integer> nodeToDistance = new HashMap<>();
    nodeToDistance.put(beginNode, 0);

    Queue<Integer> queue = new ArrayDeque<>();
    queue.offer(beginNode);
    while (!queue.isEmpty()) {
      int head = queue.poll();
      for (int adj : adjLists[head]) {
        if (nodeToDistance.containsKey(adj)) {
          if (nodeToDistance.get(adj) == nodeToDistance.get(head)) {
            return -1;
          }
        } else {
          nodeToDistance.put(adj, nodeToDistance.get(head) + 1);
          queue.offer(adj);
        }
      }
    }

    return nodeToDistance.values().stream().mapToInt(Integer::intValue).max().getAsInt();
  }

  void search(List<Integer> component, List<Integer>[] adjLists, boolean[] visited, int node) {
    component.add(node);
    visited[node] = true;

    for (int adj : adjLists[node]) {
      if (!visited[adj]) {
        search(component, adjLists, visited, adj);
      }
    }
  }
}
