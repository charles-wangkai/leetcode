import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int[] hitBricks(int[][] grid, int[][] hits) {
    int m = grid.length;
    int n = grid[0].length;

    Dsu dsu = new Dsu((m + 1) * n);

    for (int c = 0; c < n - 1; ++c) {
      dsu.union(toIndex(n, 0, c), toIndex(n, 0, c + 1));
    }

    Set<Integer> hitIndices =
        Arrays.stream(hits).map(hit -> toIndex(n, hit[0] + 1, hit[1])).collect(Collectors.toSet());
    boolean[][] visited = new boolean[m + 1][n];
    Arrays.fill(visited[0], true);
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if (grid[r][c] == 1 && !hitIndices.contains(toIndex(n, r + 1, c))) {
          addBrick(dsu, visited, r + 1, c);
        }
      }
    }

    int[] result = new int[hits.length];
    for (int i = result.length - 1; i >= 0; --i) {
      int hitR = hits[i][0];
      int hitC = hits[i][1];

      if (grid[hitR][hitC] == 1) {
        result[i] = addBrick(dsu, visited, hitR + 1, hitC);
      }
    }

    return result;
  }

  int toIndex(int n, int r, int c) {
    return r * n + c;
  }

  int addBrick(Dsu dsu, boolean[][] visited, int r, int c) {
    int row = visited.length;
    int col = visited[0].length;
    int n = col;

    visited[r][c] = true;

    int oldTopSize = dsu.getSize(0);

    for (int i = 0; i < R_OFFSETS.length; ++i) {
      int adjR = r + R_OFFSETS[i];
      int adjC = c + C_OFFSETS[i];
      if (adjR >= 0 && adjR < row && adjC >= 0 && adjC < col && visited[adjR][adjC]) {
        dsu.union(toIndex(n, r, c), toIndex(n, adjR, adjC));
      }
    }

    return Math.max(0, dsu.getSize(0) - oldTopSize - 1);
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
