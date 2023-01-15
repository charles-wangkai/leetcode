import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public int numberOfGoodPaths(int[] vals, int[][] edges) {
    int n = vals.length;

    @SuppressWarnings("unchecked")
    List<Integer>[] edgeLists = new List[n];
    for (int i = 0; i < edgeLists.length; ++i) {
      edgeLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < edges.length; ++i) {
      edgeLists[edges[i][0]].add(i);
      edgeLists[edges[i][1]].add(i);
    }

    SortedMap<Integer, List<Integer>> valueToIndices = new TreeMap<>();
    for (int i = 0; i < vals.length; ++i) {
      valueToIndices.putIfAbsent(vals[i], new ArrayList<>());
      valueToIndices.get(vals[i]).add(i);
    }

    int result = n;
    boolean[] visited = new boolean[n];
    int[] parents = new int[n];
    Arrays.fill(parents, -1);
    for (List<Integer> indices : valueToIndices.values()) {
      for (int index : indices) {
        visited[index] = true;
      }
      for (int index : indices) {
        for (int e : edgeLists[index]) {
          int other = (edges[e][0] == index) ? edges[e][1] : edges[e][0];
          if (visited[other]) {
            int root1 = findRoot(parents, index);
            int root2 = findRoot(parents, other);
            if (root1 != root2) {
              parents[root2] = root1;
            }
          }
        }
      }
      Map<Integer, Integer> rootToCount = new HashMap<>();
      for (int index : indices) {
        int root = findRoot(parents, index);
        rootToCount.put(root, rootToCount.getOrDefault(root, 0) + 1);
      }

      result += rootToCount.values().stream().mapToInt(count -> count * (count - 1) / 2).sum();
    }

    return result;
  }

  static int findRoot(int[] parents, int node) {
    int root = node;
    while (parents[root] != -1) {
      root = parents[root];
    }

    int p = node;
    while (p != root) {
      int next = parents[p];
      parents[p] = root;

      p = next;
    }

    return root;
  }
}
