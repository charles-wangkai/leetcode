import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int largestSubmatrix(int[][] matrix) {
    int n = matrix[0].length;

    int result = 0;
    int[] heights = new int[n];
    for (int[] row : matrix) {
      for (int i = 0; i < heights.length; ++i) {
        if (row[i] == 1) {
          ++heights[i];
        } else {
          heights[i] = 0;
        }
      }

      int[] sortedHeights = Arrays.stream(heights).sorted().toArray();
      result =
          Math.max(
              result,
              IntStream.range(0, n)
                  .map(i -> (sortedHeights.length - i) * sortedHeights[i])
                  .max()
                  .getAsInt());
    }

    return result;
  }
}
