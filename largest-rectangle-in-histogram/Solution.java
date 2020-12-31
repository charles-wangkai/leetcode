import java.util.Arrays;
import java.util.Stack;

class Solution {
  public int largestRectangleArea(int[] heights) {
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
