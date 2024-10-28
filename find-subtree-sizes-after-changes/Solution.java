import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public int[] findSubtreeSizes(int[] parent, String s) {
    List<Integer>[] childLists = buildChildLists(parent);

    int[] changedParents = parent.clone();
    search1(changedParents, s, childLists, new HashMap<>(), 0);

    int[] subtreeSizes = new int[parent.length];
    search2(subtreeSizes, buildChildLists(changedParents), 0);

    return subtreeSizes;
  }

  void search2(int[] subtreeSizes, List<Integer>[] childLists, int node) {
    subtreeSizes[node] = 1;
    for (int child : childLists[node]) {
      search2(subtreeSizes, childLists, child);
      subtreeSizes[node] += subtreeSizes[child];
    }
  }

  void search1(
      int[] changedParents,
      String s,
      List<Integer>[] childLists,
      Map<Character, Deque<Integer>> letterToNodes,
      int node) {
    char letter = s.charAt(node);
    if (letterToNodes.containsKey(letter)) {
      changedParents[node] = letterToNodes.get(letter).peek();
    } else {
      letterToNodes.put(letter, new ArrayDeque<>());
    }
    letterToNodes.get(letter).push(node);

    for (int child : childLists[node]) {
      search1(changedParents, s, childLists, letterToNodes, child);
    }

    letterToNodes.get(letter).pop();
    if (letterToNodes.get(letter).isEmpty()) {
      letterToNodes.remove(letter);
    }
  }

  List<Integer>[] buildChildLists(int[] parents) {
    @SuppressWarnings("unchecked")
    List<Integer>[] result = new List[parents.length];
    for (int i = 0; i < result.length; ++i) {
      result[i] = new ArrayList<>();
    }
    for (int i = 1; i < parents.length; ++i) {
      result[parents[i]].add(i);
    }

    return result;
  }
}