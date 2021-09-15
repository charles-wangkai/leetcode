import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
    int n = parents.length;

    Map<Integer, Integer> valueToIndex =
        IntStream.range(0, n).boxed().collect(Collectors.toMap(i -> nums[i], i -> i));

    @SuppressWarnings("unchecked")
    List<Integer>[] childrens = new List[n];
    for (int i = 0; i < childrens.length; ++i) {
      childrens[i] = new ArrayList<>();
    }
    for (int i = 1; i < parents.length; ++i) {
      childrens[parents[i]].add(i);
    }

    int[] depths = new int[n];
    @SuppressWarnings("unchecked")
    List<Integer>[] ancestorLists = new List[n];
    for (int i = 0; i < ancestorLists.length; ++i) {
      ancestorLists[i] = new ArrayList<>();
    }

    dfs1(childrens, depths, ancestorLists, 0, new ArrayList<>());

    int[] missings = new int[n];
    dfs2(childrens, depths, ancestorLists, valueToIndex, 0, missings);

    return missings;
  }

  void dfs1(
      List<Integer>[] childrens,
      int[] depths,
      List<Integer>[] ancestorLists,
      int node,
      List<Integer> path) {
    depths[node] = path.size();
    for (int step = 1; step <= path.size(); step *= 2) {
      ancestorLists[node].add(path.get(path.size() - step));
    }

    path.add(node);
    for (int child : childrens[node]) {
      dfs1(childrens, depths, ancestorLists, child, path);
    }
    path.remove(path.size() - 1);
  }

  int dfs2(
      List<Integer>[] childrens,
      int[] depths,
      List<Integer>[] ancestorLists,
      Map<Integer, Integer> valueToIndex,
      int node,
      int[] missings) {
    int result = 1;
    for (int child : childrens[node]) {
      result =
          Math.max(result, dfs2(childrens, depths, ancestorLists, valueToIndex, child, missings));
    }

    while (isDescendant(depths, ancestorLists, valueToIndex, result, node)) {
      ++result;
    }

    missings[node] = result;

    return result;
  }

  boolean isDescendant(
      int[] depths,
      List<Integer>[] ancestorLists,
      Map<Integer, Integer> valueToIndex,
      int value,
      int node) {
    if (!valueToIndex.containsKey(value)) {
      return false;
    }

    int index = valueToIndex.get(value);
    int depthDiff = depths[index] - depths[node];
    if (depthDiff < 0) {
      return false;
    }

    int exponent = -1;
    while (1 << (exponent + 1) <= depthDiff) {
      ++exponent;
    }

    for (int i = exponent; i >= 0; --i) {
      if (1 << i <= depthDiff) {
        index = ancestorLists[index].get(i);
        depthDiff = depths[index] - depths[node];
      }
    }

    return index == node;
  }
}
