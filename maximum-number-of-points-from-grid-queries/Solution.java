import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int[] maxPoints(int[][] grid, int[] queries) {
    int m = grid.length;
    int n = grid[0].length;

    Dsu dsu = new Dsu(m * n);

    int[] sortedPoints =
        IntStream.range(0, m * n)
            .boxed()
            .sorted(Comparator.comparing(i -> grid[i / n][i % n]))
            .mapToInt(Integer::intValue)
            .toArray();
    int sortedPointIndex = 0;

    int[] sortedQueryIndices =
        IntStream.range(0, queries.length)
            .boxed()
            .sorted(Comparator.comparing(i -> queries[i]))
            .mapToInt(Integer::intValue)
            .toArray();

    int[] result = new int[queries.length];
    Set<Integer> seen = new HashSet<>();
    for (int queryIndex : sortedQueryIndices) {
      while (sortedPointIndex != sortedPoints.length
          && grid[sortedPoints[sortedPointIndex] / n][sortedPoints[sortedPointIndex] % n]
              < queries[queryIndex]) {
        int point = sortedPoints[sortedPointIndex];
        seen.add(point);

        for (int i = 0; i < R_OFFSETS.length; ++i) {
          int adjR = point / n + R_OFFSETS[i];
          int adjC = point % n + C_OFFSETS[i];
          int adj = adjR * n + adjC;
          if (adjR >= 0 && adjR < m && adjC >= 0 && adjC < n && seen.contains(adj)) {
            dsu.union(point, adj);
          }
        }

        ++sortedPointIndex;
      }

      if (seen.contains(0)) {
        result[queryIndex] = dsu.getSize(0);
      }
    }

    return result;
  }
}

class Dsu {
  int[] parentOrSizes;

  Dsu(int n) {
    parentOrSizes = new int[n];
    Arrays.fill(parentOrSizes, -1);
  }

  int find(int a) {
    if (parentOrSizes[a] < 0) {
      return a;
    }

    parentOrSizes[a] = find(parentOrSizes[a]);

    return parentOrSizes[a];
  }

  void union(int a, int b) {
    int aLeader = find(a);
    int bLeader = find(b);
    if (aLeader != bLeader) {
      parentOrSizes[aLeader] += parentOrSizes[bLeader];
      parentOrSizes[bLeader] = aLeader;
    }
  }

  int getSize(int a) {
    return -parentOrSizes[find(a)];
  }

  Map<Integer, List<Integer>> buildLeaderToGroup() {
    Map<Integer, List<Integer>> leaderToGroup = new HashMap<>();
    for (int i = 0; i < parentOrSizes.length; ++i) {
      int leader = find(i);
      leaderToGroup.putIfAbsent(leader, new ArrayList<>());
      leaderToGroup.get(leader).add(i);
    }

    return leaderToGroup;
  }
}
