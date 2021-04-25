import java.util.Arrays;
import java.util.Comparator;

class Solution {
  public int maxBuilding(int n, int[][] restrictions) {
    restrictions = Arrays.copyOf(restrictions, restrictions.length + 1);
    restrictions[restrictions.length - 1] = new int[] {1, 0};
    Arrays.sort(restrictions, Comparator.comparing(r -> r[0]));
    if (restrictions[restrictions.length - 1][0] != n) {
      restrictions = Arrays.copyOf(restrictions, restrictions.length + 1);
      restrictions[restrictions.length - 1] = new int[] {n, Integer.MAX_VALUE};
    }

    int[] heights = Arrays.stream(restrictions).mapToInt(r -> r[1]).toArray();
    for (int i = 1; i < heights.length; ++i) {
      heights[i] =
          Math.min(heights[i], heights[i - 1] + (restrictions[i][0] - restrictions[i - 1][0]));
    }
    for (int i = heights.length - 2; i >= 0; --i) {
      heights[i] =
          Math.min(heights[i], heights[i + 1] + (restrictions[i + 1][0] - restrictions[i][0]));
    }

    int result = Arrays.stream(heights).max().getAsInt();
    for (int i = 0; i < heights.length - 1; ++i) {
      int heightDiff = Math.abs(heights[i] - heights[i + 1]);
      int distance = restrictions[i + 1][0] - restrictions[i][0];
      int freeDistance = distance - heightDiff;

      result = Math.max(result, Math.max(heights[i], heights[i + 1]) + freeDistance / 2);
    }

    return result;
  }
}
