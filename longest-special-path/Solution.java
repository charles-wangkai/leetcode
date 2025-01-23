import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

  public int[] longestSpecialPath(int[][] edges, int[] nums) {
    int n = edges.length + 1;

    @SuppressWarnings("unchecked")
    List<Integer>[] edgeLists = new List[n];
    for (int i = 0; i < edgeLists.length; ++i) {
      edgeLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < edges.length; ++i) {
      edgeLists[edges[i][0]].add(i);
      edgeLists[edges[i][1]].add(i);
    }

    Outcome outcome =
        search(edges, nums, edgeLists, new HashMap<>(), new ArrayList<>(), 0, -1, 0, 0, 0);

    return new int[] {outcome.maxDistance(), outcome.minNodeNum()};
  }

  Outcome search(
      int[][] edges,
      int[] nums,
      List<Integer>[] edgeLists,
      Map<Integer, Deque<Integer>> valueToDepths,
      List<Integer> pathDistances,
      int fromDepth,
      int parent,
      int node,
      int depth,
      int pathDistance) {
    pathDistances.add(pathDistance);

    if (valueToDepths.containsKey(nums[node])) {
      fromDepth = Math.max(fromDepth, valueToDepths.get(nums[node]).peek() + 1);
    } else {
      valueToDepths.put(nums[node], new ArrayDeque<>());
    }
    valueToDepths.get(nums[node]).push(depth);

    Outcome result =
        new Outcome(pathDistance - pathDistances.get(fromDepth), depth - fromDepth + 1);
    for (int e : edgeLists[node]) {
      int adj = (edges[e][0] == node) ? edges[e][1] : edges[e][0];
      if (adj != parent) {
        result =
            merge(
                result,
                search(
                    edges,
                    nums,
                    edgeLists,
                    valueToDepths,
                    pathDistances,
                    fromDepth,
                    node,
                    adj,
                    depth + 1,
                    pathDistance + edges[e][2]));
      }
    }

    valueToDepths.get(nums[node]).pop();
    if (valueToDepths.get(nums[node]).isEmpty()) {
      valueToDepths.remove(nums[node]);
    }

    pathDistances.removeLast();

    return result;
  }

  Outcome merge(Outcome o1, Outcome o2) {
    return (o1.maxDistance() > o2.maxDistance()
            || (o1.maxDistance() == o2.maxDistance() && o1.minNodeNum() <= o2.minNodeNum()))
        ? o1
        : o2;
  }
}

record Outcome(int maxDistance, int minNodeNum) {}
