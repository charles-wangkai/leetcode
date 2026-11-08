import java.util.ArrayList;
import java.util.List;

class Solution {
  public long minCost(int n, String colors, int[][] edges) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
      adjLists[edge[1]].add(edge[0]);
    }

    Outcome outcome = search(colors, adjLists, -1, 0);

    return Math.min(outcome.dp0(), Math.min(outcome.dp10(), outcome.dp11()));
  }

  Outcome search(String colors, List<Integer>[] adjLists, int parent, int node) {
    List<Outcome> outcomes = new ArrayList<>();
    for (int adj : adjLists[node]) {
      if (adj != parent) {
        outcomes.add(search(colors, adjLists, node, adj));
      }
    }

    int subtreeSize = 1 + outcomes.stream().mapToInt(Outcome::subtreeSize).sum();

    long dp0 =
        ((colors.charAt(node) == 'R') ? subtreeSize : 0)
            + outcomes.stream().mapToLong(Outcome::dp0).sum();

    long dp10 =
        outcomes.isEmpty()
            ? Long.MAX_VALUE
            : (((colors.charAt(node) == 'R') ? subtreeSize : 0)
                + outcomes.stream().mapToLong(Outcome::dp0).sum()
                + outcomes.stream()
                    .mapToLong(outcome -> Math.min(outcome.dp10(), outcome.dp11()) - outcome.dp0())
                    .min()
                    .getAsLong());

    long dp11 =
        ((colors.charAt(node) == 'R') ? 0 : subtreeSize)
            + outcomes.stream().mapToLong(outcome -> Math.min(outcome.dp0(), outcome.dp11())).sum();

    return new Outcome(subtreeSize, dp0, dp10, dp11);
  }
}

record Outcome(int subtreeSize, long dp0, long dp10, long dp11) {}
