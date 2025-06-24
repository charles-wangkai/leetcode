import java.util.ArrayList;
import java.util.List;

class Solution {
  public int minIncrease(int n, int[][] edges, int[] cost) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
      adjLists[edge[1]].add(edge[0]);
    }

    return search(cost, adjLists, -1, 0).increaseNum();
  }

  Outcome search(int[] cost, List<Integer>[] adjLists, int parent, int node) {
    int increaseNum = 0;
    List<Long> scores = new ArrayList<>();
    for (int adj : adjLists[node]) {
      if (adj != parent) {
        Outcome outcome = search(cost, adjLists, node, adj);
        increaseNum += outcome.increaseNum();
        scores.add(outcome.maxScore());
      }
    }

    long maxScore = cost[node];
    if (!scores.isEmpty()) {
      long largestScore = scores.stream().mapToLong(Long::valueOf).max().getAsLong();
      maxScore += largestScore;
      increaseNum += scores.stream().filter(score -> score != largestScore).count();
    }

    return new Outcome(increaseNum, maxScore);
  }
}

record Outcome(int increaseNum, long maxScore) {}
