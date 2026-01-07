import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public long countPaths(int n, int[][] edges) {
    boolean[] centers = new boolean[n];
    for (int i = 0; i < centers.length; ++i) {
      centers[i] = isPrime(i + 1);
    }

    Dsu dsu = new Dsu(n);
    for (int[] edge : edges) {
      if (!centers[edge[0] - 1] && !centers[edge[1] - 1]) {
        dsu.union(edge[0] - 1, edge[1] - 1);
      }
    }

    @SuppressWarnings("unchecked")
    List<Integer>[] adjSizeLists = new List[n];
    for (int i = 0; i < adjSizeLists.length; ++i) {
      adjSizeLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      if (centers[edge[0] - 1] && !centers[edge[1] - 1]) {
        adjSizeLists[edge[0] - 1].add(dsu.getSize(edge[1] - 1));
      } else if (!centers[edge[0] - 1] && centers[edge[1] - 1]) {
        adjSizeLists[edge[1] - 1].add(dsu.getSize(edge[0] - 1));
      }
    }

    long result = 0;
    for (int i = 0; i < centers.length; ++i) {
      if (centers[i]) {
        int adjSizeSum = adjSizeLists[i].stream().mapToInt(Integer::intValue).sum();
        long adjSizeSquareSum =
            adjSizeLists[i].stream().mapToLong(size -> (long) size * size).sum();

        result += adjSizeSum + ((long) adjSizeSum * adjSizeSum - adjSizeSquareSum) / 2;
      }
    }

    return result;
  }

  boolean isPrime(int x) {
    if (x == 1) {
      return false;
    }

    for (int i = 2; i * i <= x; ++i) {
      if (x % i == 0) {
        return false;
      }
    }

    return true;
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
