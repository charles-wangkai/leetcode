import java.util.stream.IntStream;

class Solution {
  static final int DIGIT_SUM_LIMIT = 9 * 15;

  public long countFancy(long l, long r) {
    return computeFancyNum(r) - computeFancyNum(l - 1);
  }

  long computeFancyNum(long limit) {
    int[] digits = String.valueOf(limit).chars().map(c -> c - '0').toArray();

    long[][][][] dp = new long[2][11][4][DIGIT_SUM_LIMIT + 1];
    dp[1][10][0][0] = 1;

    for (int digit : digits) {
      long[][][][] nextDp = new long[2][11][4][DIGIT_SUM_LIMIT + 1];
      for (int strict = 0; strict <= 1; ++strict) {
        for (int lastDigit = 0; lastDigit <= 10; ++lastDigit) {
          for (int direction = 0; direction <= 3; ++direction) {
            for (int d = 0; d <= ((strict == 1) ? digit : 9); ++d) {
              for (int digitSum = 0; digitSum + d <= DIGIT_SUM_LIMIT; ++digitSum) {
                int nextDirection;
                if (lastDigit == 10) {
                  nextDirection = 0;
                } else {
                  int signum = Integer.signum(lastDigit - d);

                  nextDirection =
                      (direction != 0 && (direction == 2 || direction != signum + 2))
                          ? 2
                          : (signum + 2);
                }

                nextDp[(strict == 1 && d == digit) ? 1 : 0][
                        (lastDigit == 10) ? ((d == 0) ? 10 : d) : d][nextDirection][digitSum + d] +=
                    dp[strict][lastDigit][direction][digitSum];
              }
            }
          }
        }
      }

      dp = nextDp;
    }

    long result = 0;
    for (int strict = 0; strict <= 1; ++strict) {
      for (int lastDigit = 0; lastDigit <= 10; ++lastDigit) {
        for (int direction = 0; direction <= 3; ++direction) {
          for (int digitSum = 0; digitSum <= DIGIT_SUM_LIMIT; ++digitSum) {
            if (direction == 0 || direction != 2 || isGood(digitSum)) {
              result += dp[strict][lastDigit][direction][digitSum];
            }
          }
        }
      }
    }

    return result;
  }

  boolean isGood(int x) {
    String s = String.valueOf(x);

    return IntStream.range(0, s.length() - 1).allMatch(i -> s.charAt(i) < s.charAt(i + 1))
        || IntStream.range(0, s.length() - 1).allMatch(i -> s.charAt(i) > s.charAt(i + 1));
  }
}
