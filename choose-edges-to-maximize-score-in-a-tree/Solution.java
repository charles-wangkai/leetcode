import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public long maxScore(int[][] edges) {
    int n = edges.length;

    @SuppressWarnings("unchecked")
    List<Integer>[] childLists = new List[n];
    for (int i = 0; i < childLists.length; ++i) {
      childLists[i] = new ArrayList<>();
    }
    for (int i = 1; i < edges.length; ++i) {
      childLists[edges[i][0]].add(i);
    }

    return search(edges, childLists, 0).maxSum();
  }

  Outcome search(int[][] edges, List<Integer>[] childLists, int node) {
    List<Integer> weights = new ArrayList<>();
    List<Outcome> outcomes = new ArrayList<>();
    for (int child : childLists[node]) {
      weights.add(edges[child][1]);
      outcomes.add(search(edges, childLists, child));
    }

    if (weights.isEmpty()) {
      return new Outcome(0, 0);
    }

    long notChosenMaxSum = outcomes.stream().mapToLong(Outcome::maxSum).sum();
    long maxSum =
        Math.max(
            notChosenMaxSum,
            IntStream.range(0, weights.size())
                .mapToLong(
                    i ->
                        weights.get(i)
                            + outcomes.get(i).notChosenMaxSum()
                            + (notChosenMaxSum - outcomes.get(i).maxSum()))
                .max()
                .getAsLong());

    return new Outcome(maxSum, notChosenMaxSum);
  }
}

record Outcome(long maxSum, long notChosenMaxSum) {}
