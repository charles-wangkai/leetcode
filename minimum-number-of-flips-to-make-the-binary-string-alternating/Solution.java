class Solution {
  public int minFlips(String s) {
    int n = s.length();

    int[][] leftEvenCounts = new int[n][2];
    int[][] leftOddCounts = new int[n][2];
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < 2; ++j) {
        leftEvenCounts[i][j] = getValue(leftEvenCounts, i - 1, j);
        leftOddCounts[i][j] = getValue(leftOddCounts, i - 1, j);
      }

      if (i % 2 == 0) {
        ++leftEvenCounts[i][s.charAt(i) - '0'];
      } else {
        ++leftOddCounts[i][s.charAt(i) - '0'];
      }
    }

    int[][] rightEvenCounts = new int[n][2];
    int[][] rightOddCounts = new int[n][2];
    for (int i = n - 1; i >= 0; --i) {
      for (int j = 0; j < 2; ++j) {
        rightEvenCounts[i][j] = getValue(rightEvenCounts, i + 1, j);
        rightOddCounts[i][j] = getValue(rightOddCounts, i + 1, j);
      }

      if (i % 2 == 0) {
        ++rightEvenCounts[i][s.charAt(i) - '0'];
      } else {
        ++rightOddCounts[i][s.charAt(i) - '0'];
      }
    }

    if (n % 2 == 0) {
      return Math.min(
          leftEvenCounts[n - 1][1] + leftOddCounts[n - 1][0],
          leftEvenCounts[n - 1][0] + leftOddCounts[n - 1][1]);
    }

    int result = Integer.MAX_VALUE;
    for (int i = 0; i < n; ++i) {
      if (i % 2 == 0) {
        result =
            Math.min(
                result,
                Math.min(
                    ((s.charAt(i) == '0') ? 0 : 1)
                        + getValue(rightEvenCounts, i + 1, 1)
                        + getValue(rightOddCounts, i + 1, 0)
                        + getValue(leftEvenCounts, i - 1, 0)
                        + getValue(leftOddCounts, i - 1, 1),
                    ((s.charAt(i) == '1') ? 0 : 1)
                        + getValue(rightEvenCounts, i + 1, 0)
                        + getValue(rightOddCounts, i + 1, 1)
                        + getValue(leftEvenCounts, i - 1, 1)
                        + getValue(leftOddCounts, i - 1, 0)));
      } else {
        result =
            Math.min(
                result,
                Math.min(
                    ((s.charAt(i) == '0') ? 0 : 1)
                        + getValue(rightEvenCounts, i + 1, 0)
                        + getValue(rightOddCounts, i + 1, 1)
                        + getValue(leftEvenCounts, i - 1, 1)
                        + getValue(leftOddCounts, i - 1, 0),
                    ((s.charAt(i) == '1') ? 0 : 1)
                        + getValue(rightEvenCounts, i + 1, 1)
                        + getValue(rightOddCounts, i + 1, 0)
                        + getValue(leftEvenCounts, i - 1, 0)
                        + getValue(leftOddCounts, i - 1, 1)));
      }
    }

    return result;
  }

  int getValue(int[][] values, int i, int j) {
    return (i >= 0 && i < values.length) ? values[i][j] : 0;
  }
}
