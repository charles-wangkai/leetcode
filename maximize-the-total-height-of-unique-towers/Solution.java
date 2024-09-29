import java.util.Arrays;

class Solution {
  public long maximumTotalSum(int[] maximumHeight) {
    Arrays.sort(maximumHeight);

    long result = 0;
    int limit = Integer.MAX_VALUE;
    for (int i = maximumHeight.length - 1; i >= 0; --i) {
      int height = Math.min(limit, maximumHeight[i]);
      if (height == 0) {
        return -1;
      }

      result += height;
      limit = height - 1;
    }

    return result;
  }
}