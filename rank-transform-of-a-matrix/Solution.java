import java.util.ArrayList;
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

    SortedMap<Integer, List<Location>> valueToLocations = new TreeMap<>();
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if (!valueToLocations.containsKey(matrix[r][c])) {
          valueToLocations.put(matrix[r][c], new ArrayList<>());
        }

        valueToLocations.get(matrix[r][c]).add(new Location(r, c));
      }
    }

    int[] lastColInRows = IntStream.range(0, m).map(i -> -1).toArray();
    int[] lastRowInCols = IntStream.range(0, n).map(i -> -1).toArray();
    int[][] result = new int[m][n];
    for (int value : valueToLocations.keySet()) {
      List<Location> locations = valueToLocations.get(value);

      int[] parents = IntStream.range(0, m + n).map(i -> -1).toArray();
      for (Location location : locations) {
        int rootR = findRoot(parents, location.r);
        int rootC = findRoot(parents, location.c + m);
        if (rootR != rootC) {
          parents[rootC] = rootR;
        }
      }

      Map<Integer, List<Integer>> rootToIndices = new HashMap<>();
      for (int i = 0; i < locations.size(); ++i) {
        int root = findRoot(parents, locations.get(i).r);
        if (!rootToIndices.containsKey(root)) {
          rootToIndices.put(root, new ArrayList<>());
        }
        rootToIndices.get(root).add(i);
      }

      for (List<Integer> indices : rootToIndices.values()) {
        int rank = 1;
        for (int index : indices) {
          Location location = locations.get(index);

          if (lastColInRows[location.r] != -1) {
            rank = Math.max(rank, result[location.r][lastColInRows[location.r]] + 1);
          }
          if (lastRowInCols[location.c] != -1) {
            rank = Math.max(rank, result[lastRowInCols[location.c]][location.c] + 1);
          }
        }

        for (int index : indices) {
          Location location = locations.get(index);

          result[location.r][location.c] = rank;

          lastColInRows[location.r] = location.c;
          lastRowInCols[location.c] = location.r;
        }
      }
    }

    return result;
  }

  int findRoot(int[] parents, int node) {
    int root = node;
    while (parents[root] != -1) {
      root = parents[root];
    }

    int p = node;
    while (p != root) {
      int next = parents[p];
      parents[p] = root;

      p = next;
    }

    return root;
  }
}

class Location {
  int r;
  int c;

  Location(int r, int c) {
    this.r = r;
    this.c = c;
  }
}
