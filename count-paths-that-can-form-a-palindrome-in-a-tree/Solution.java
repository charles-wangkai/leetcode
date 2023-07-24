// https://leetcode.com/problems/count-paths-that-can-form-a-palindrome-in-a-tree/solutions/3804246/simple-dfs-solution-in-c-java-python/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  static final int ALPHABET_SIZE = 26;

  public long countPalindromePaths(List<Integer> parent, String s) {
    int n = parent.size();

    @SuppressWarnings("unchecked")
    List<Edge>[] edgeLists = new List[n];
    for (int i = 0; i < edgeLists.length; ++i) {
      edgeLists[i] = new ArrayList<>();
    }
    for (int i = 1; i < n; ++i) {
      edgeLists[parent.get(i)].add(new Edge(i, s.charAt(i)));
    }

    Map<Integer, Integer> maskToCount = new HashMap<>();
    search(maskToCount, edgeLists, 0, 0);

    long result = 0;
    for (int mask : maskToCount.keySet()) {
      result += (long) maskToCount.get(mask) * maskToCount.get(mask);
      for (int i = 0; i < ALPHABET_SIZE; ++i) {
        result += (long) maskToCount.get(mask) * maskToCount.getOrDefault(mask ^ (1 << i), 0);
      }
    }
    result = (result - n) / 2;

    return result;
  }

  void search(Map<Integer, Integer> maskToCount, List<Edge>[] edgeLists, int node, int mask) {
    maskToCount.put(mask, maskToCount.getOrDefault(mask, 0) + 1);

    for (Edge edge : edgeLists[node]) {
      search(maskToCount, edgeLists, edge.to(), mask ^ (1 << (edge.letter() - 'a')));
    }
  }
}

record Edge(int to, char letter) {}
