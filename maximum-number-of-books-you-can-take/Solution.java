// https://leetcode.com/problems/maximum-number-of-books-you-can-take/solutions/2508360/java-o-n-dp-monotonic-stack-beats-73-time-and-space-explanation-with-comments/

import java.util.Arrays;
import java.util.Stack;

class Solution {
  public long maximumBooks(int[] books) {
    Stack<Integer> prevIndices = new Stack<>();
    long[] dp = new long[books.length];
    for (int i = 0; i < dp.length; ++i) {
      while (!prevIndices.empty()
          && books[prevIndices.peek()] - prevIndices.peek() >= books[i] - i) {
        prevIndices.pop();
      }

      dp[i] =
          computeRangeSum(
                  books[i], Math.min(books[i], i - (prevIndices.empty() ? -1 : prevIndices.peek())))
              + (prevIndices.empty() ? 0 : dp[prevIndices.peek()]);

      prevIndices.push(i);
    }

    return Arrays.stream(dp).max().getAsLong();
  }

  long computeRangeSum(int a0, int n) {
    return (a0 + (a0 - n + 1L)) * n / 2;
  }
}
