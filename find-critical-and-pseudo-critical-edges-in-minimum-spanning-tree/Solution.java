import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
    int mstWeight = computeMSTWeight(n, edges, -1, -1);

    List<Integer> criticals = new ArrayList<>();
    List<Integer> pseudos = new ArrayList<>();
    for (int i = 0; i < edges.length; ++i) {
      if (isCritical(n, edges, mstWeight, i)) {
        criticals.add(i);
      } else if (isPseudo(n, edges, mstWeight, i)) {
        pseudos.add(i);
      }
    }

    return List.of(criticals, pseudos);
  }

  Integer computeMSTWeight(int n, int[][] edges, int removedEdgeIndex, int requiredEdgeIndex) {
    int[] sortedIndices =
        IntStream.range(0, edges.length)
            .filter(i -> i != removedEdgeIndex)
            .boxed()
            .sorted(Comparator.comparing(i -> edges[i][2]))
            .mapToInt(Integer::intValue)
            .toArray();

    int result = 0;
    Dsu dsu = new Dsu(n);
    if (requiredEdgeIndex != -1) {
      result += union(dsu, edges[requiredEdgeIndex]);
    }
    for (int sortedIndex : sortedIndices) {
      result += union(dsu, edges[sortedIndex]);
    }

    return (dsu.buildLeaderToGroup().size() == 1) ? result : null;
  }

  int union(Dsu dsu, int[] edge) {
    int leader1 = dsu.find(edge[0]);
    int leader2 = dsu.find(edge[1]);
    if (leader1 == leader2) {
      return 0;
    }

    dsu.union(leader1, leader2);

    return edge[2];
  }

  boolean isCritical(int n, int[][] edges, int mstWeight, int edgeIndex) {
    Integer weight = computeMSTWeight(n, edges, edgeIndex, -1);

    return weight == null || weight != mstWeight;
  }

  boolean isPseudo(int n, int[][] edges, int mstWeight, int edgeIndex) {
    return computeMSTWeight(n, edges, -1, edgeIndex) == mstWeight;
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
