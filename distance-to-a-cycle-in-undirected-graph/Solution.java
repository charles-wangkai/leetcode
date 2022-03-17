import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

class Solution {
  public int[] distanceToCycle(int n, int[][] edges) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
      adjLists[edge[1]].add(edge[0]);
    }

    int[] edgeInCycle = findEdgeInCycle(adjLists, new boolean[n], -1, 0);
    List<Integer> nodesInCycle =
        findNodesInCycle(adjLists, edgeInCycle, new boolean[n], new ArrayList<>(), edgeInCycle[0]);

    return computeDistances(adjLists, nodesInCycle);
  }

  int[] computeDistances(List<Integer>[] adjLists, List<Integer> nodesInCycle) {
    int[] distances = new int[adjLists.length];
    Arrays.fill(distances, -1);
    Queue<Integer> queue = new ArrayDeque<>();
    for (int node : nodesInCycle) {
      distances[node] = 0;
      queue.offer(node);
    }

    while (!queue.isEmpty()) {
      int head = queue.poll();
      for (int adj : adjLists[head]) {
        if (distances[adj] == -1) {
          distances[adj] = distances[head] + 1;
          queue.offer(adj);
        }
      }
    }

    return distances;
  }

  List<Integer> findNodesInCycle(
      List<Integer>[] adjLists,
      int[] edgeInCycle,
      boolean[] visited,
      List<Integer> path,
      int node) {
    visited[node] = true;

    path.add(node);
    if (node == edgeInCycle[1]) {
      return path;
    }
    for (int adj : adjLists[node]) {
      if (!visited[adj] && !(node == edgeInCycle[0] && adj == edgeInCycle[1])) {
        List<Integer> subResult = findNodesInCycle(adjLists, edgeInCycle, visited, path, adj);
        if (subResult != null) {
          return subResult;
        }
      }
    }
    path.remove(path.size() - 1);

    return null;
  }

  int[] findEdgeInCycle(List<Integer>[] adjLists, boolean[] visited, int parent, int node) {
    visited[node] = true;

    for (int adj : adjLists[node]) {
      if (visited[adj]) {
        if (adj != parent) {
          return new int[] {node, adj};
        }
      } else {
        int[] subResult = findEdgeInCycle(adjLists, visited, node, adj);
        if (subResult != null) {
          return subResult;
        }
      }
    }

    return null;
  }
}