// https://leetcode.com/problems/longest-palindromic-path-in-graph/solutions/6951713/build-from-center-2-n/

import java.util.HashSet;
import java.util.Set;

class Solution {
  public int maxLen(int n, int[][] edges, String label) {
    @SuppressWarnings("unchecked")
    Set<Integer>[] adjSets = new Set[n];
    for (int i = 0; i < adjSets.length; ++i) {
      adjSets[i] = new HashSet<>();
    }
    for (int[] edge : edges) {
      adjSets[edge[0]].add(edge[1]);
      adjSets[edge[1]].add(edge[0]);
    }

    int result = 0;
    for (int i = 0; i < n; ++i) {
      Set<Integer> seen = new HashSet<>();
      result = Math.max(result, search(seen, label, adjSets, i, i, 0));
      for (int j : adjSets[i]) {
        if (label.charAt(i) == label.charAt(j)) {
          result = Math.max(result, search(seen, label, adjSets, i, j, 0));
        }
      }
    }

    return result;
  }

  int search(
      Set<Integer> seen, String label, Set<Integer>[] adjSets, int node1, int node2, int prevMask) {
    int mask = prevMask | (1 << node1) | (1 << node2);
    int result = Integer.bitCount(mask);
    if (!seen.contains(mask)) {
      seen.add(mask);

      for (int x : adjSets[node1]) {
        if (((mask >> x) & 1) == 0) {
          for (int y : adjSets[node2]) {
            if (((mask >> y) & 1) == 0 && x != y && label.charAt(x) == label.charAt(y)) {
              result = Math.max(result, search(seen, label, adjSets, x, y, mask));
            }
          }
        }
      }
    }

    return result;
  }
}
