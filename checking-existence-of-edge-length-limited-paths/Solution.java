import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
    Arrays.sort(edgeList, Comparator.comparing(edge -> edge[2]));
    int[] sortedQueryIndices =
        IntStream.range(0, queries.length)
            .boxed()
            .sorted(Comparator.comparing(i -> queries[i][2]))
            .mapToInt(Integer::intValue)
            .toArray();

    Dsu dsu = new Dsu(n);

    boolean[] result = new boolean[queries.length];
    int edgeIndex = 0;
    for (int queryIndex : sortedQueryIndices) {
      int[] query = queries[queryIndex];
      while (edgeIndex != edgeList.length && edgeList[edgeIndex][2] < query[2]) {
        dsu.union(edgeList[edgeIndex][0], edgeList[edgeIndex][1]);
        ++edgeIndex;
      }

      result[queryIndex] = dsu.find(query[0]) == dsu.find(query[1]);
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
