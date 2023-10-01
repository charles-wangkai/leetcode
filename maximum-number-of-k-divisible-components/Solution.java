import java.util.ArrayList;
import java.util.List;

class Solution {
  public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
      adjLists[edge[1]].add(edge[0]);
    }

    return search(values, k, adjLists, -1, 0).divisibleCount();
  }

  Outcome search(int[] values, int k, List<Integer>[] adjLists, int parent, int node) {
    int divisibleCount = 0;
    int remainder = values[node] % k;
    for (int adj : adjLists[node]) {
      if (adj != parent) {
        Outcome subResult = search(values, k, adjLists, node, adj);
        divisibleCount += subResult.divisibleCount();
        remainder = (remainder + subResult.remainder()) % k;
      }
    }
    if (remainder == 0) {
      ++divisibleCount;
    }

    return new Outcome(divisibleCount, remainder);
  }
}

record Outcome(int divisibleCount, int remainder) {}
