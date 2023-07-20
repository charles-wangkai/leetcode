// https://leetcode.com/problems/number-of-visible-people-in-a-queue/discuss/1359707

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
  public int[] canSeePersonsCount(int[] heights) {
    int[] result = new int[heights.length];
    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = 0; i < heights.length; ++i) {
      while (!stack.isEmpty() && heights[stack.peek()] < heights[i]) {
        ++result[stack.pop()];
      }
      if (!stack.isEmpty()) {
        ++result[stack.peek()];
      }

      stack.push(i);
    }

    return result;
  }
}
