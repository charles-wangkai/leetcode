import java.util.Arrays;

class Solution {
  static final int LIMIT = 1000;

  public int convertArray(int[] nums) {
    return Math.min(
        computeOperationNum(nums),
        computeOperationNum(Arrays.stream(nums).map(x -> LIMIT - x).toArray()));
  }

  int computeOperationNum(int[] a) {
    int[] dp = new int[LIMIT + 1];
    for (int ai : a) {
      int[] nextDp = new int[dp.length];

      int prevMin = Integer.MAX_VALUE;
      for (int i = 0; i < dp.length; ++i) {
        prevMin = Math.min(prevMin, dp[i]);
        nextDp[i] = prevMin + Math.abs(ai - i);
      }

      dp = nextDp;
    }

    return Arrays.stream(dp).min().getAsInt();
  }
}
