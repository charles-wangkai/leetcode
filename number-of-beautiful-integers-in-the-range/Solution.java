import java.util.stream.IntStream;

class Solution {
  public int numberOfBeautifulIntegers(int low, int high, int k) {
    return computeValidNum(k, high + 1) - computeValidNum(k, low);
  }

  int computeValidNum(int k, int limit) {
    int[] digits = String.valueOf(limit).chars().map(c -> c - '0').toArray();

    int[][][] tightDp = new int[digits.length + 1][digits.length + 1][k];
    int[][][] freeDp = new int[digits.length + 1][digits.length + 1][k];
    for (int d = 1; d <= digits[0]; ++d) {
      int evenCount = (d % 2 == 0) ? 1 : 0;
      int oddCount = (d % 2 == 1) ? 1 : 0;

      if (d == digits[0]) {
        tightDp[evenCount][oddCount][d % k] += 1;
      } else {
        freeDp[evenCount][oddCount][d % k] += 1;
      }
    }

    for (int i = 1; i < digits.length; ++i) {
      for (int evenCount = 0; evenCount <= i; ++evenCount) {
        int oddCount = i - evenCount;
        for (int r = 0; r < k; ++r) {
          for (int d = 0; d <= digits[i]; ++d) {
            int nextEvenCount = evenCount + ((d % 2 == 0) ? 1 : 0);
            int nextOddCount = oddCount + ((d % 2 == 1) ? 1 : 0);
            int nextR = (r * 10 + d) % k;

            if (d == digits[i]) {
              tightDp[nextEvenCount][nextOddCount][nextR] += tightDp[evenCount][oddCount][r];
            } else {
              freeDp[nextEvenCount][nextOddCount][nextR] += tightDp[evenCount][oddCount][r];
            }
          }

          for (int d = 0; d <= 9; ++d) {
            int nextEvenCount = evenCount + ((d % 2 == 0) ? 1 : 0);
            int nextOddCount = oddCount + ((d % 2 == 1) ? 1 : 0);
            int nextR = (r * 10 + d) % k;

            freeDp[nextEvenCount][nextOddCount][nextR] += freeDp[evenCount][oddCount][r];
          }
        }
      }
    }

    return IntStream.range(1, digits.length).map(length -> computeAllNum(k, length)).sum()
        + ((digits.length % 2 == 0) ? freeDp[digits.length / 2][digits.length / 2][0] : 0);
  }

  int computeAllNum(int k, int length) {
    int[][][] dp = new int[length + 1][length + 1][k];
    dp[0][0][0] = 1;
    for (int i = 0; i < length; ++i) {
      for (int evenCount = 0; evenCount <= i; ++evenCount) {
        int oddCount = i - evenCount;
        for (int r = 0; r < k; ++r) {
          for (int d = (i == 0) ? 1 : 0; d <= 9; ++d) {
            int nextEvenCount = evenCount + ((d % 2 == 0) ? 1 : 0);
            int nextOddCount = oddCount + ((d % 2 == 1) ? 1 : 0);
            int nextR = (r * 10 + d) % k;

            dp[nextEvenCount][nextOddCount][nextR] += dp[evenCount][oddCount][r];
          }
        }
      }
    }

    return (length % 2 == 0) ? dp[length / 2][length / 2][0] : 0;
  }
}
