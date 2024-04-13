import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution {
  public int maximalRectangle(char[][] matrix) {
    int row = matrix.length;
    int col = matrix[0].length;

    int result = 0;
    int[] heights = new int[col];
    for (int r = 0; r < row; ++r) {
      for (int c = 0; c < heights.length; ++c) {
        if (matrix[r][c] == '1') {
          ++heights[c];
        } else {
          heights[c] = 0;
        }
      }

      result = Math.max(result, computeLargestRectangleInHistogram(heights));
    }

    return result;
  }

  int computeLargestRectangleInHistogram(int[] heights) {
    heights = Arrays.copyOf(heights, heights.length + 1);

    int result = 0;
    Deque<Integer> indices = new ArrayDeque<>();
    for (int i = 0; i < heights.length; ++i) {
      while (!indices.isEmpty() && heights[i] <= heights[indices.peek()]) {
        int h = heights[indices.pop()];
        result = Math.max(result, h * (i - (indices.isEmpty() ? -1 : indices.peek()) - 1));
      }

      indices.push(i);
    }

    return result;
  }
}
