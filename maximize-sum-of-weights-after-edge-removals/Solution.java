import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public long maximizeSumOfWeights(int[][] edges, int k) {
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

    return search(edges, k, edgeLists, -1, 0).includingWeightSum();
  }

  Outcome search(int[][] edges, int k, List<Integer>[] edgeLists, int parent, int node) {
    long total = 0;
    List<Long> diffs = new ArrayList<>();
    for (int edge : edgeLists[node]) {
      int other = (edges[edge][0] == node) ? edges[edge][1] : edges[edge][0];
      if (other != parent) {
        Outcome subResult = search(edges, k, edgeLists, node, other);

        long max =
            Math.max(
                subResult.includingWeightSum(), edges[edge][2] + subResult.excludingWeightSum());
        total += max;
        diffs.add(max - subResult.includingWeightSum());
      }
    }
    Collections.sort(diffs);

    return new Outcome(computeMaxSum(total, diffs, k), computeMaxSum(total, diffs, k - 1));
  }

  long computeMaxSum(long total, List<Long> diffs, int maxSize) {
    return total
        - IntStream.range(0, Math.max(0, diffs.size() - maxSize)).mapToLong(diffs::get).sum();
  }
}

record Outcome(long includingWeightSum, long excludingWeightSum) {}
