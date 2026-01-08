import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public int maxNumEdgesToRemove(int n, int[][] edges) {
    Map<Integer, List<int[]>> typeToEdges = new HashMap<>();
    for (int type = 1; type <= 3; ++type) {
      typeToEdges.put(type, new ArrayList<>());
    }
    for (int[] edge : edges) {
      typeToEdges.get(edge[0]).add(edge);
    }

    int removedCommonEdgeNum = computeRemovedCommonEdgeNum(n, typeToEdges.get(3));
    int removedEdgeNum1 = computeRemovedEdgeNum(n, typeToEdges.get(3), typeToEdges.get(1));
    int removedEdgeNum2 = computeRemovedEdgeNum(n, typeToEdges.get(3), typeToEdges.get(2));

    return (removedEdgeNum1 == -1 || removedEdgeNum2 == -1)
        ? -1
        : (removedCommonEdgeNum + removedEdgeNum1 + removedEdgeNum2);
  }

  int computeRemovedCommonEdgeNum(int n, List<int[]> commonEdges) {
    int result = 0;
    Dsu dsu = new Dsu(n);
    for (int[] edge : commonEdges) {
      int leader1 = dsu.find(edge[1] - 1);
      int leader2 = dsu.find(edge[2] - 1);
      if (leader1 == leader2) {
        ++result;
      } else {
        dsu.union(leader1, leader2);
      }
    }

    return result;
  }

  int computeRemovedEdgeNum(int n, List<int[]> commonEdges, List<int[]> onlyEdges) {
    Dsu dsu = new Dsu(n);

    for (int[] edge : commonEdges) {
      dsu.union(edge[1] - 1, edge[2] - 1);
    }

    int result = 0;
    for (int[] edge : onlyEdges) {
      int leader1 = dsu.find(edge[1] - 1);
      int leader2 = dsu.find(edge[2] - 1);
      if (leader1 == leader2) {
        ++result;
      } else {
        dsu.union(leader1, leader2);
      }
    }

    return (dsu.buildLeaderToGroup().size() == 1) ? result : -1;
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
