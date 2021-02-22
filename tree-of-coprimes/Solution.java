import java.util.ArrayList;
import java.util.List;

class Solution {
  static final int LIMIT = 50;

  public int[] getCoprimes(int[] nums, int[][] edges) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[nums.length];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
      adjLists[edge[1]].add(edge[0]);
    }

    int[] coprimeAncestors = new int[nums.length];
    search(
        coprimeAncestors, nums, adjLists, new Element[LIMIT + 1], new boolean[nums.length], 0, 0);

    return coprimeAncestors;
  }

  void search(
      int[] coprimeAncestors,
      int[] nums,
      List<Integer>[] adjLists,
      Element[] elements,
      boolean[] visited,
      int depth,
      int node) {
    visited[node] = true;

    int index = -1;
    for (int i = 0; i < elements.length; ++i) {
      if (elements[i] != null
          && gcd(nums[node], nums[elements[i].node]) == 1
          && (index == -1 || elements[i].depth > elements[index].depth)) {
        index = i;
      }
    }
    coprimeAncestors[node] = (index == -1) ? -1 : elements[index].node;

    Element old = elements[nums[node]];
    elements[nums[node]] = new Element(depth, node);

    for (int adj : adjLists[node]) {
      if (!visited[adj]) {
        search(coprimeAncestors, nums, adjLists, elements, visited, depth + 1, adj);
      }
    }

    elements[nums[node]] = old;
  }

  int gcd(int x, int y) {
    return (x % y == 0) ? y : gcd(y, x % y);
  }
}

class Element {
  int depth;
  int node;

  Element(int depth, int node) {
    this.depth = depth;
    this.node = node;
  }
}
