import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public long maxOutput(int n, int[][] edges, int[] price) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
      adjLists[edge[1]].add(edge[0]);
    }

    long[] subtreeMaxSums = new long[n];
    buildSubtreeMaxSums(subtreeMaxSums, price, adjLists, 0, -1);

    return search(price, adjLists, subtreeMaxSums, 0, -1, 0);
  }

  long search(
      int[] price,
      List<Integer>[] adjLists,
      long[] subtreeMaxSums,
      int node,
      int parent,
      long parentMaxSum) {
    long result = Math.max(subtreeMaxSums[node], price[node] + parentMaxSum) - price[node];

    SortedMap<Long, Integer> subtreeMaxSumToCount = new TreeMap<>();
    for (int adj : adjLists[node]) {
      if (adj != parent) {
        updateMap(subtreeMaxSumToCount, price[node] + subtreeMaxSums[adj], 1);
      }
    }
    for (int adj : adjLists[node]) {
      if (adj != parent) {
        updateMap(subtreeMaxSumToCount, price[node] + subtreeMaxSums[adj], -1);
        result =
            Math.max(
                result,
                search(
                    price,
                    adjLists,
                    subtreeMaxSums,
                    adj,
                    node,
                    Math.max(
                        price[node] + parentMaxSum,
                        subtreeMaxSumToCount.isEmpty()
                            ? price[node]
                            : subtreeMaxSumToCount.lastKey())));
        updateMap(subtreeMaxSumToCount, price[node] + subtreeMaxSums[adj], 1);
      }
    }

    return result;
  }

  void updateMap(SortedMap<Long, Integer> map, long key, int delta) {
    map.put(key, map.getOrDefault(key, 0) + delta);
    map.remove(key, 0);
  }

  void buildSubtreeMaxSums(
      long[] subtreeMaxSums, int[] price, List<Integer>[] adjLists, int node, int parent) {
    subtreeMaxSums[node] = price[node];

    for (int adj : adjLists[node]) {
      if (adj != parent) {
        buildSubtreeMaxSums(subtreeMaxSums, price, adjLists, adj, node);
        subtreeMaxSums[node] = Math.max(subtreeMaxSums[node], price[node] + subtreeMaxSums[adj]);
      }
    }
  }
}
