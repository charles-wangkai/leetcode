import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
      adjLists[edge[1]].add(edge[0]);
    }

    int[] counts = new int[n];
    for (int[] trip : trips) {
      buildCounts(counts, adjLists, trip[1], -1, new ArrayDeque<>(), trip[0]);
    }

    return search(price, adjLists, counts, -1, false, 0, new HashMap<>());
  }

  int search(
      int[] price,
      List<Integer>[] adjLists,
      int[] counts,
      int parent,
      boolean parentChosen,
      int node,
      Map<State, Integer> cache) {
    State state = new State(node, parentChosen);
    if (!cache.containsKey(state)) {
      int result =
          price[node] * counts[node]
              + adjLists[node].stream()
                  .filter(adj -> adj != parent)
                  .mapToInt(adj -> search(price, adjLists, counts, node, false, adj, cache))
                  .sum();
      if (!parentChosen) {
        result =
            Math.min(
                result,
                price[node] / 2 * counts[node]
                    + adjLists[node].stream()
                        .filter(adj -> adj != parent)
                        .mapToInt(adj -> search(price, adjLists, counts, node, true, adj, cache))
                        .sum());
      }

      cache.put(state, result);
    }

    return cache.get(state);
  }

  void buildCounts(
      int[] counts,
      List<Integer>[] adjLists,
      int target,
      int parent,
      Deque<Integer> path,
      int node) {
    path.push(node);

    if (node == target) {
      for (int x : path) {
        ++counts[x];
      }
    }

    for (int adj : adjLists[node]) {
      if (adj != parent) {
        buildCounts(counts, adjLists, target, node, path, adj);
      }
    }

    path.pop();
  }
}

record State(int node, boolean parentChosen) {}
