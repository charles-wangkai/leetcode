import java.util.NavigableSet;
import java.util.TreeSet;

class Solution {
  public int maxSumSubmatrix(int[][] matrix, int k) {
    int m = matrix.length;
    int n = matrix[0].length;

    int result = Integer.MIN_VALUE;
    for (int beginR = 0; beginR < m; ++beginR) {
      int[] columnSums = new int[n];
      for (int endR = beginR; endR < m; ++endR) {
        for (int c = 0; c < n; ++c) {
          columnSums[c] += matrix[endR][c];
        }

        result = Math.max(result, findMaxSum(columnSums, k));
      }
    }

    return result;
  }

  private int findMaxSum(int[] a, int k) {
    int sum = 0;
    NavigableSet<Integer> sums = new TreeSet<>();
    sums.add(0);

    int result = Integer.MIN_VALUE;
    for (int ai : a) {
      sum += ai;

      Integer prefixSum = sums.ceiling(sum - k);
      if (prefixSum != null) {
        result = Math.max(result, sum - prefixSum);
      }

      sums.add(sum);
    }

    return result;
  }
}
