import java.util.ArrayList;
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
        search(
            edges,
            nums,
            edgeLists,
            new HashMap<>(),
            new ArrayList<>(),
            0,
            Integer.MIN_VALUE,
            -1,
            0,
            0,
            0);

    return new int[] {outcome.maxDistance(), outcome.minNodeNum()};
  }

  Outcome search(
      int[][] edges,
      int[] nums,
      List<Integer>[] edgeLists,
      Map<Integer, List<Integer>> valueToDepths,
      List<Integer> pathDistances,
      int fromDepth,
      int twiceValue,
      int parent,
      int node,
      int depth,
      int pathDistance) {
    pathDistances.add(pathDistance);

    if (valueToDepths.containsKey(nums[node])) {
      if (valueToDepths.get(nums[node]).getLast() >= fromDepth) {
        if (twiceValue == Integer.MIN_VALUE) {
          twiceValue = nums[node];
        } else if (nums[node] == twiceValue) {
          fromDepth = getSecondLast(valueToDepths.get(twiceValue)) + 1;
        } else if (valueToDepths.get(nums[node]).getLast()
            > getSecondLast(valueToDepths.get(twiceValue))) {
          fromDepth = getSecondLast(valueToDepths.get(twiceValue)) + 1;
          twiceValue = nums[node];
        } else {
          fromDepth = valueToDepths.get(nums[node]).getLast() + 1;
        }
      }
    } else {
      valueToDepths.put(nums[node], new ArrayList<>());
    }
    valueToDepths.get(nums[node]).add(depth);

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
                    twiceValue,
                    node,
                    adj,
                    depth + 1,
                    pathDistance + edges[e][2]));
      }
    }

    valueToDepths.get(nums[node]).removeLast();
    if (valueToDepths.get(nums[node]).isEmpty()) {
      valueToDepths.remove(nums[node]);
    }

    pathDistances.removeLast();

    return result;
  }

  int getSecondLast(List<Integer> a) {
    return a.get(a.size() - 2);
  }

  Outcome merge(Outcome o1, Outcome o2) {
    return (o1.maxDistance() > o2.maxDistance()
            || (o1.maxDistance() == o2.maxDistance() && o1.minNodeNum() <= o2.minNodeNum()))
        ? o1
        : o2;
  }
}

record Outcome(int maxDistance, int minNodeNum) {}
