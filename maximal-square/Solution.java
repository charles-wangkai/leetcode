import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution {
  public int maximalSquare(char[][] matrix) {
    int row = matrix.length;
    int col = matrix[0].length;

    int maxArea = 0;
    int[] heights = new int[col];
    for (int i = 0; i < row; ++i) {
      updateHeights(heights, matrix[i]);
      maxArea = Math.max(maxArea, computeLargestSquareInHistogram(heights));
    }

    return maxArea;
  }

  void updateHeights(int[] heights, char[] line) {
    for (int i = 0; i < heights.length; ++i) {
      if (line[i] == '1') {
        ++heights[i];
      } else {
        heights[i] = 0;
      }
    }
  }

  int computeLargestSquareInHistogram(int[] heights) {
    heights = Arrays.copyOf(heights, heights.length + 1);

    Deque<Integer> indices = new ArrayDeque<>();
    int maxArea = 0;
    for (int i = 0; i < heights.length; ++i) {
      while (!indices.isEmpty() && heights[i] <= heights[indices.peek()]) {
        int h = heights[indices.pop()];
        maxArea =
            Math.max(
                maxArea,
                computeMaxSquareArea(h, i - (indices.isEmpty() ? 0 : (indices.peek() + 1))));
      }

      indices.push(i);
    }

    return maxArea;
  }

  int computeMaxSquareArea(int height, int width) {
    int size = Math.min(height, width);

    return size * size;
  }
}
