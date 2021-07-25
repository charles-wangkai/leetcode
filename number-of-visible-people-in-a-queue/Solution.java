// https://leetcode.com/problems/number-of-visible-people-in-a-queue/discuss/1359707

import java.util.Stack;

class Solution {
  public int[] canSeePersonsCount(int[] heights) {
    int[] result = new int[heights.length];
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < heights.length; ++i) {
      while (!stack.empty() && heights[stack.peek()] < heights[i]) {
        ++result[stack.pop()];
      }
      if (!stack.empty()) {
        ++result[stack.peek()];
      }

      stack.push(i);
    }

    return result;
  }
}
