import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
  public int minimumMoves(int[][] grid) {
    List<Integer> targets = new ArrayList<>();
    List<Integer> stones = new ArrayList<>();
    for (int r = 0; r < grid.length; ++r) {
      for (int c = 0; c < grid[r].length; ++c) {
        if (grid[r][c] == 0) {
          targets.add(r * 3 + c);
        }

        for (int i = 0; i < grid[r][c] - 1; ++i) {
          stones.add(r * 3 + c);
        }
      }
    }

    return search(targets, stones, 0);
  }

  int search(List<Integer> targets, List<Integer> stones, int index) {
    if (index == targets.size()) {
      int result = 0;
      for (int i = 0; i < targets.size(); ++i) {
        result +=
            Math.abs(stones.get(i) / 3 - targets.get(i) / 3)
                + Math.abs(stones.get(i) % 3 - targets.get(i) % 3);
      }

      return result;
    }

    int result = Integer.MAX_VALUE;
    for (int i = index; i < stones.size(); ++i) {
      Collections.swap(stones, i, index);
      result = Math.min(result, search(stones, targets, index + 1));
      Collections.swap(stones, i, index);
    }

    return result;
  }
}
