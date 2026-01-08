import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.IntStream;

class Solution {
  public int[][] matrixRankTransform(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;

    SortedMap<Integer, List<Point>> valueToPoints = new TreeMap<>();
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        valueToPoints.putIfAbsent(matrix[r][c], new ArrayList<>());
        valueToPoints.get(matrix[r][c]).add(new Point(r, c));
      }
    }

    int[] lastColInRows = IntStream.range(0, m).map(i -> -1).toArray();
    int[] lastRowInCols = IntStream.range(0, n).map(i -> -1).toArray();
    int[][] result = new int[m][n];
    for (int value : valueToPoints.keySet()) {
      List<Point> points = valueToPoints.get(value);

      Dsu dsu = new Dsu(m + n);
      for (Point point : points) {
        dsu.union(point.r(), point.c() + m);
      }

      Map<Integer, List<Integer>> leaderToIndices = new HashMap<>();
      for (int i = 0; i < points.size(); ++i) {
        int leader = dsu.find(points.get(i).r());
        leaderToIndices.putIfAbsent(leader, new ArrayList<>());
        leaderToIndices.get(leader).add(i);
      }

      for (List<Integer> indices : leaderToIndices.values()) {
        int rank = 1;
        for (int index : indices) {
          Point point = points.get(index);

          if (lastColInRows[point.r()] != -1) {
            rank = Math.max(rank, result[point.r()][lastColInRows[point.r()]] + 1);
          }
          if (lastRowInCols[point.c()] != -1) {
            rank = Math.max(rank, result[lastRowInCols[point.c()]][point.c()] + 1);
          }
        }

        for (int index : indices) {
          Point point = points.get(index);

          result[point.r()][point.c()] = rank;

          lastColInRows[point.r()] = point.c();
          lastRowInCols[point.c()] = point.r();
        }
      }
    }

    return result;
  }
}

record Point(int r, int c) {}

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
