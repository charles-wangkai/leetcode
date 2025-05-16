// https://leetcode.com/problems/maximum-sum-of-edge-values-in-a-graph/solutions/6734708/all-solution-are-wrong-by-lee215-ypvg/
// https://leetcode.com/problems/maximum-sum-of-edge-values-in-a-graph/solutions/6732538/python-find-connected-components/

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
  public long maxScore(int n, int[][] edges) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
      adjLists[edge[1]].add(edge[0]);
    }

    List<Integer> circles = new ArrayList<>();
    List<Integer> lines = new ArrayList<>();
    boolean[] seen = new boolean[n];
    for (int i = 0; i < seen.length; ++i) {
      if (!seen[i]) {
        Set<Integer> component = new HashSet<>();
        search(component, adjLists, seen, i);

        (component.stream().allMatch(node -> adjLists[node].size() == 2) ? circles : lines)
            .add(component.size());
      }
    }
    Collections.sort(lines, Comparator.reverseOrder());

    long result = 0;
    int rest = n;
    for (int circle : circles) {
      result += computeScore(rest - circle + 1, rest, true);
      rest -= circle;
    }
    for (int line : lines) {
      result += computeScore(rest - line + 1, rest, false);
      rest -= line;
    }

    return result;
  }

  long computeScore(int l, int r, boolean isCircle) {
    Deque<Integer> dq = new ArrayDeque<>();
    dq.offer(r);
    dq.offer(r);

    long result = 0;
    for (int i = r - 1; i >= l; --i) {
      result += (long) dq.pollFirst() * i;
      dq.offerLast(i);
    }

    if (isCircle) {
      int first = dq.pollFirst();
      int second = dq.pollFirst();

      result += (long) first * second;
    }

    return result;
  }

  void search(Set<Integer> component, List<Integer>[] adjLists, boolean[] seen, int node) {
    if (!seen[node]) {
      seen[node] = true;
      component.add(node);

      for (int adj : adjLists[node]) {
        search(component, adjLists, seen, adj);
      }
    }
  }
}
