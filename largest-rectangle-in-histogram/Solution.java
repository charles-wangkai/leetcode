import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution {
  public int largestRectangleArea(int[] heights) {
    heights = Arrays.copyOf(heights, heights.length + 1);
    Deque<Integer> indices = new ArrayDeque<>();
    int result = 0;
    for (int i = 0; i < heights.length; ++i) {
      while (!indices.isEmpty() && heights[i] <= heights[indices.peek()]) {
        int h = heights[indices.pop()];
        result = Math.max(result, h * (i - (indices.isEmpty() ? 0 : (indices.peek() + 1))));
      }

      indices.push(i);
    }

    return result;
  }
}
