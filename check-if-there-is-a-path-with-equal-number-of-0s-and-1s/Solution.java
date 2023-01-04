import java.util.HashSet;
import java.util.Set;

class Solution {
  public boolean isThereAPath(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    @SuppressWarnings("unchecked")
    Set<Integer>[][] states = new Set[m][n];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        Set<Integer> prevStates = new HashSet<>();
        if (r != 0) {
          prevStates.addAll(states[r - 1][c]);
        }
        if (c != 0) {
          prevStates.addAll(states[r][c - 1]);
        }
        if (prevStates.isEmpty()) {
          prevStates.add(0);
        }

        states[r][c] = new HashSet<>();
        for (int prevState : prevStates) {
          states[r][c].add(prevState + ((grid[r][c] == 0) ? 1 : -1));
        }
      }
    }

    return states[m - 1][n - 1].contains(0);
  }
}
