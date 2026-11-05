// https://leetcode.com/problems/total-sum-of-interaction-cost-in-tree-groups-ii/solutions/8454969/small-to-large-merging-simple-dfs-same-a-zxgy/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public long interactionCosts(int n, int[][] edges, int[] group) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
      adjLists[edge[1]].add(edge[0]);
    }

    Map<Integer, Integer> groupToTotal = new HashMap<>();
    for (int g : group) {
      groupToTotal.put(g - 1, groupToTotal.getOrDefault(g - 1, 0) + 1);
    }

    return search(group, adjLists, groupToTotal, -1, 0).costSum();
  }

  Outcome search(
      int[] group,
      List<Integer>[] adjLists,
      Map<Integer, Integer> groupToTotal,
      int parent,
      int node) {
    long costSum = 0;

    Element element = new Element();
    element.update(groupToTotal, group[node] - 1, 1);

    for (int adj : adjLists[node]) {
      if (adj != parent) {
        Outcome outcome = search(group, adjLists, groupToTotal, node, adj);
        costSum += outcome.costSum() + outcome.element().cost;
        element = Element.merge(groupToTotal, element, outcome.element());
      }
    }

    return new Outcome(costSum, element);
  }
}

class Element {
  long cost;
  Map<Integer, Integer> groupToCount = new HashMap<>();

  void update(Map<Integer, Integer> groupToTotal, int group, int delta) {
    int oldCount = groupToCount.getOrDefault(group, 0);
    int newCount = oldCount + delta;

    groupToCount.put(group, newCount);

    cost -= (long) oldCount * (groupToTotal.get(group) - oldCount);
    cost += (long) newCount * (groupToTotal.get(group) - newCount);
  }

  static Element merge(Map<Integer, Integer> groupToTotal, Element e1, Element e2) {
    if (e1.groupToCount.size() < e2.groupToCount.size()) {
      return merge(groupToTotal, e2, e1);
    }

    for (int group : e2.groupToCount.keySet()) {
      e1.update(groupToTotal, group, e2.groupToCount.get(group));
    }

    return e1;
  }
}

record Outcome(long costSum, Element element) {}
