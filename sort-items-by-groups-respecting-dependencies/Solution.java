import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Solution {
  public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
    int groupNum = m;
    for (int i = 0; i < group.length; ++i) {
      if (group[i] == -1) {
        group[i] = groupNum;
        ++groupNum;
      }
    }

    Graph groupGraph = new Graph();
    for (int g : group) {
      groupGraph.addNode(g);
    }

    Map<Integer, Graph> groupToItemGraph = new HashMap<>();
    for (int g : groupGraph.nodeToAdjs.keySet()) {
      groupToItemGraph.put(g, new Graph());
    }
    for (int i = 0; i < group.length; ++i) {
      groupToItemGraph.get(group[i]).addNode(i);
    }

    for (int i = 0; i < beforeItems.size(); ++i) {
      for (int beforeItem : beforeItems.get(i)) {
        if (group[i] == group[beforeItem]) {
          groupToItemGraph.get(group[i]).addEdge(beforeItem, i);
        } else {
          groupGraph.addEdge(group[beforeItem], group[i]);
        }
      }
    }

    List<Integer> sortedGroups = groupGraph.topologicalSort();
    if (sortedGroups == null) {
      return new int[0];
    }

    List<Integer> result = new ArrayList<>();
    for (int g : sortedGroups) {
      List<Integer> sortedItems = groupToItemGraph.get(g).topologicalSort();
      if (sortedItems == null) {
        return new int[0];
      }

      result.addAll(sortedItems);
    }

    return result.stream().mapToInt(Integer::intValue).toArray();
  }
}

class Graph {
  Map<Integer, List<Integer>> nodeToAdjs = new HashMap<>();

  void addNode(int node) {
    nodeToAdjs.putIfAbsent(node, new ArrayList<>());
  }

  void addEdge(int from, int to) {
    nodeToAdjs.get(from).add(to);
  }

  List<Integer> topologicalSort() {
    Map<Integer, Integer> nodeToInDegree = new HashMap<>();
    for (int node : nodeToAdjs.keySet()) {
      nodeToInDegree.put(node, 0);
    }

    for (List<Integer> adjs : nodeToAdjs.values()) {
      for (int adj : adjs) {
        nodeToInDegree.put(adj, nodeToInDegree.get(adj) + 1);
      }
    }

    List<Integer> result = new ArrayList<>();
    Queue<Integer> queue = new ArrayDeque<>();
    for (int node : nodeToInDegree.keySet()) {
      if (nodeToInDegree.get(node) == 0) {
        queue.offer(node);
      }
    }

    while (!queue.isEmpty()) {
      int head = queue.poll();

      result.add(head);

      for (int adj : nodeToAdjs.get(head)) {
        nodeToInDegree.put(adj, nodeToInDegree.get(adj) - 1);
        if (nodeToInDegree.get(adj) == 0) {
          queue.offer(adj);
        }
      }
    }

    return (result.size() == nodeToInDegree.size()) ? result : null;
  }
}
