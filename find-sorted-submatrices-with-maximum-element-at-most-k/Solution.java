// https://leetcode.com/problems/find-sorted-submatrices-with-maximum-element-at-most-k/solutions/6066625/python3-count-submatrices-with-all-ones/

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.IntStream;

class Solution {
  public long countSubmatrices(int[][] grid, int k) {
    int m = grid.length;
    int n = grid[0].length;

    int[][] leftLengths = new int[m][n];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if (grid[r][c] <= k) {
          leftLengths[r][c] =
              (c != 0 && grid[r][c] <= grid[r][c - 1]) ? (leftLengths[r][c - 1] + 1) : 1;
        }
      }
    }

    return IntStream.range(0, n)
        .map(c -> computeMatrixNum(Arrays.stream(leftLengths).mapToInt(line -> line[c]).toArray()))
        .asLongStream()
        .sum();
  }

  int computeMatrixNum(int[] heights) {
    int result = 0;
    Deque<Integer> stack = new ArrayDeque<>();
    int contribution = 0;
    for (int i = 0; i < heights.length; ++i) {
      while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
        int rightIndex = stack.pop();
        int leftIndex = stack.isEmpty() ? -1 : stack.peek();

        contribution -= (heights[rightIndex] - heights[i]) * (rightIndex - leftIndex);
      }

      stack.push(i);
      contribution += heights[i];
      result += contribution;
    }

    return result;
  }
}
