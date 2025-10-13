import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
  public long sumOfAncestors(int n, int[][] edges, int[] nums) {
    Map<Integer, String> valueToKey =
        Arrays.stream(nums)
            .distinct()
            .boxed()
            .collect(Collectors.toMap(value -> value, this::buildKey));

    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
      adjLists[edge[1]].add(edge[0]);
    }

    return search(nums, valueToKey, adjLists, new HashMap<>(), -1, 0);
  }

  long search(
      int[] nums,
      Map<Integer, String> valueToKey,
      List<Integer>[] adjLists,
      Map<String, Integer> keyToCount,
      int parent,
      int node) {
    String key = valueToKey.get(nums[node]);

    long result = keyToCount.getOrDefault(key, 0);

    keyToCount.put(key, keyToCount.getOrDefault(key, 0) + 1);

    for (int adj : adjLists[node]) {
      if (adj != parent) {
        result += search(nums, valueToKey, adjLists, keyToCount, node, adj);
      }
    }

    keyToCount.put(key, keyToCount.get(key) - 1);

    return result;
  }

  String buildKey(int x) {
    List<Integer> orphanPrimes = new ArrayList<>();
    for (int i = 2; i * i <= x; ++i) {
      int count = 0;
      while (x % i == 0) {
        x /= i;
        ++count;
      }

      if (count % 2 == 1) {
        orphanPrimes.add(i);
      }
    }
    if (x != 1) {
      orphanPrimes.add(x);
    }

    return orphanPrimes.stream().map(String::valueOf).collect(Collectors.joining(","));
  }
}