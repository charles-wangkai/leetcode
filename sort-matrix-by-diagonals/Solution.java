import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Solution {
  public int[][] sortMatrix(int[][] grid) {
    int n = grid.length;

    for (int r = 0; r < n; ++r) {
      sortDiagonal(grid, r, 0, Comparator.reverseOrder());
    }
    for (int c = 1; c < n; ++c) {
      sortDiagonal(grid, 0, c, Comparator.naturalOrder());
    }

    return grid;
  }

  void sortDiagonal(int[][] grid, int beginR, int beginC, Comparator<Integer> comparator) {
    int n = grid.length;

    List<Integer> sorted = new ArrayList<>();
    for (int r = beginR, c = beginC; r < n && c < n; ++r, ++c) {
      sorted.add(grid[r][c]);
    }
    Collections.sort(sorted, comparator);

    for (int i = 0; i < sorted.size(); ++i) {
      grid[beginR + i][beginC + i] = sorted.get(i);
    }
  }
}