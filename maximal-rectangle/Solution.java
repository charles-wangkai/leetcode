import java.util.Arrays;
import java.util.Stack;

class Solution {
  public int maximalRectangle(char[][] matrix) {
    int row = matrix.length;
    if (row == 0) {
      return 0;
    }
    int col = matrix[0].length;

    int maxArea = 0;
    int[] heights = new int[col];
    for (int r = 0; r < row; ++r) {
      updateHeights(heights, matrix[r]);
      maxArea = Math.max(maxArea, largestRectangleInHistogram(heights));
    }

    return maxArea;
  }

  void updateHeights(int[] heights, char[] line) {
    for (int c = 0; c < heights.length; ++c) {
      if (line[c] == '1') {
        ++heights[c];
      } else {
        heights[c] = 0;
      }
    }
  }

  int largestRectangleInHistogram(int[] heights) {
    heights = Arrays.copyOf(heights, heights.length + 1);
    Stack<Integer> indices = new Stack<>();
    int result = 0;
    for (int i = 0; i < heights.length; ++i) {
      while (!indices.empty() && heights[i] <= heights[indices.peek()]) {
        int h = heights[indices.pop()];
        result = Math.max(result, h * (i - (indices.empty() ? 0 : (indices.peek() + 1))));
      }
      indices.push(i);
    }

    return result;
  }
}
