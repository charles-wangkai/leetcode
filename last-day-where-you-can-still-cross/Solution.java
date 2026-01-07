import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int latestDayToCross(int row, int col, int[][] cells) {
    Dsu dsu = new Dsu(row * col);
    for (int c = 0; c < col - 1; ++c) {
      dsu.union(c, c + 1);
    }
    for (int c = 0; c < col - 1; ++c) {
      dsu.union((row - 1) * col + c, (row - 1) * col + c + 1);
    }

    int[][] matrix = new int[row][col];
    for (int r = 0; r < matrix.length; ++r) {
      Arrays.fill(matrix[r], 1);
    }

    for (int i = cells.length - 1; ; --i) {
      int r = cells[i][0] - 1;
      int c = cells[i][1] - 1;

      matrix[r][c] = 0;

      for (int j = 0; j < R_OFFSETS.length; ++j) {
        int adjR = r + R_OFFSETS[j];
        int adjC = c + C_OFFSETS[j];
        if (adjR >= 0 && adjR < row && adjC >= 0 && adjC < col && matrix[adjR][adjC] == 0) {
          dsu.union(r * col + c, adjR * col + adjC);
        }
      }

      if (dsu.find(0) == dsu.find(row * col - 1)) {
        return i;
      }
    }
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
