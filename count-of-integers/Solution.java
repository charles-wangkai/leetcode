import java.math.BigInteger;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int count(String num1, String num2, int minSum, int maxSum) {
    return addMod(
        computeNum(num2, minSum, maxSum),
        -computeNum(new BigInteger(num1).subtract(BigInteger.ONE).toString(), minSum, maxSum));
  }

  int computeNum(String num, int minSum, int maxSum) {
    int[][][] dp = new int[num.length() + 1][maxSum + 1][2];
    dp[0][0][0] = 1;
    for (int i = 1; i <= num.length(); ++i) {
      int digit = num.charAt(i - 1) - '0';

      for (int sum = 0; sum <= maxSum; ++sum) {
        if (sum >= digit) {
          dp[i][sum][0] = dp[i - 1][sum - digit][0];
        }

        for (int d = 0; d <= 9 && d <= sum; ++d) {
          dp[i][sum][1] = addMod(dp[i][sum][1], dp[i - 1][sum - d][1]);

          if (d < digit) {
            dp[i][sum][1] = addMod(dp[i][sum][1], dp[i - 1][sum - d][0]);
          }
        }
      }
    }

    int result = 0;
    for (int sum = minSum; sum <= maxSum; ++sum) {
      result = addMod(result, addMod(dp[num.length()][sum][0], dp[num.length()][sum][1]));
    }

    return result;
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}
