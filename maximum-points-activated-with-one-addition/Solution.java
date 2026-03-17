import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int maxActivated(int[][] points) {
    Map<Integer, Integer> xToCompressed =
        buildValueToCompressed(Arrays.stream(points).mapToInt(point -> point[0]).toArray());
    Map<Integer, Integer> yToCompressed =
        buildValueToCompressed(Arrays.stream(points).mapToInt(point -> point[1]).toArray());

    Dsu dsu = new Dsu(points.length + xToCompressed.size() + yToCompressed.size());
    for (int i = 0; i < points.length; ++i) {
      int xIndex = points.length + xToCompressed.get(points[i][0]);
      int yIndex = points.length + xToCompressed.size() + yToCompressed.get(points[i][1]);

      dsu.union(i, xIndex);
      dsu.union(i, yIndex);
      dsu.union(xIndex, yIndex);
    }

    return 1
        + dsu.buildLeaderToGroup().values().stream()
            .mapToInt(group -> (int) group.stream().filter(index -> index < points.length).count())
            .boxed()
            .sorted(Comparator.reverseOrder())
            .limit(2)
            .mapToInt(Integer::intValue)
            .sum();
  }

  Map<Integer, Integer> buildValueToCompressed(int[] values) {
    int[] sorted = Arrays.stream(values).distinct().sorted().toArray();

    return IntStream.range(0, sorted.length)
        .boxed()
        .collect(Collectors.toMap(i -> sorted[i], i -> i));
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
