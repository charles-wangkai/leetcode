import java.util.Arrays;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int zigZagArrays(int n, int l, int r) {
    int index = 0;

    int[][][] dp = new int[2][r + 1][2];
    for (int value = l; value <= r; ++value) {
      dp[index][value][0] = 1;
      dp[index][value][1] = 1;
    }

    for (int i = 0; i < n - 1; ++i) {
      int nextIndex = 1 - index;

      for (int value = 0; value < dp[nextIndex].length; ++value) {
        Arrays.fill(dp[nextIndex][value], 0);
      }

      int upSum = 0;
      for (int nextValue = l; nextValue <= r; ++nextValue) {
        dp[nextIndex][nextValue][1] = addMod(dp[nextIndex][nextValue][1], upSum);
        upSum = addMod(upSum, dp[index][nextValue][0]);
      }

      int downSum = 0;
      for (int nextValue = r; nextValue >= l; --nextValue) {
        dp[nextIndex][nextValue][0] = addMod(dp[nextIndex][nextValue][0], downSum);
        downSum = addMod(downSum, dp[index][nextValue][1]);
      }

      index = nextIndex;
    }

    int result = 0;
    for (int value = 0; value < dp[index].length; ++value) {
      for (int d = 0; d < dp[index][value].length; ++d) {
        result = addMod(result, dp[index][value][d]);
      }
    }

    return result;
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}
