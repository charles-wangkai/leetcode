// https://leetcode.com/problems/color-the-triangle-red/solutions/3436676/i-kotlin-with-illustrated-explanation/

import java.util.ArrayList;
import java.util.List;

class Solution {
  public int[][] colorRed(int n) {
    List<int[]> result = new ArrayList<>();
    for (int maxR = n; maxR >= 1; maxR -= 4) {
      if (maxR < 4) {
        for (int r = 1; r <= maxR; ++r) {
          result.add(new int[] {r, 1});
          if (r != 1) {
            result.add(new int[] {r, 2 * r - 1});
          }
        }
      } else {
        result.add(new int[] {maxR - 3, 1});

        for (int c = 3; c <= (maxR - 2) * 2 - 1; c += 2) {
          result.add(new int[] {maxR - 2, c});
        }

        result.add(new int[] {maxR - 1, 2});

        for (int c = 1; c <= maxR * 2 - 1; c += 2) {
          result.add(new int[] {maxR, c});
        }
      }
    }

    return result.toArray(int[][]::new);
  }
}
