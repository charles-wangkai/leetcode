class Solution {
  public int maxArea(int[][] mat) {
    int m = mat.length;
    int n = mat[0].length;

    int[][] prefixSums = new int[m + 1][n + 1];
    for (int r = 1; r <= m; ++r) {
      for (int c = 1; c <= n; ++c) {
        prefixSums[r][c] =
            prefixSums[r - 1][c]
                + prefixSums[r][c - 1]
                - prefixSums[r - 1][c - 1]
                + mat[r - 1][c - 1];
      }
    }

    int result = 0;
    int lower = 1;
    int upper = Math.min(m, n);
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(prefixSums, middle)) {
        result = middle * middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  boolean check(int[][] prefixSums, int k) {
    int m = prefixSums.length - 1;
    int n = prefixSums[0].length - 1;

    int minBeginR = Integer.MAX_VALUE;
    int maxBeginR = Integer.MIN_VALUE;
    int minBeginC = Integer.MAX_VALUE;
    int maxBeginC = Integer.MIN_VALUE;
    for (int beginR = 0; beginR + k <= m; ++beginR) {
      for (int beginC = 0; beginC + k <= n; ++beginC) {
        if (computeRangeSum(prefixSums, beginR, beginC, k) == k * k) {
          minBeginR = Math.min(minBeginR, beginR);
          maxBeginR = Math.max(maxBeginR, beginR);
          minBeginC = Math.min(minBeginC, beginC);
          maxBeginC = Math.max(maxBeginC, beginC);
        }
      }
    }

    return minBeginR != Integer.MAX_VALUE
        && (minBeginR + k <= maxBeginR || minBeginC + k <= maxBeginC);
  }

  int computeRangeSum(int[][] prefixSums, int beginR, int beginC, int k) {
    return prefixSums[beginR + k][beginC + k]
        - prefixSums[beginR][beginC + k]
        - prefixSums[beginR + k][beginC]
        + prefixSums[beginR][beginC];
  }
}