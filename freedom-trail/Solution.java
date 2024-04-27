import java.util.Arrays;

class Solution {
  public int findRotateSteps(String ring, String key) {
    int[] dp = new int[ring.length()];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;

    for (char c : key.toCharArray()) {
      int[] nextDp = new int[dp.length];
      Arrays.fill(nextDp, Integer.MAX_VALUE);

      for (int i = 0; i < nextDp.length; ++i) {
        if (ring.charAt(i) == c) {
          for (int k = 0; k < dp.length; ++k) {
            if (dp[k] != Integer.MAX_VALUE) {
              nextDp[i] =
                  Math.min(
                      nextDp[i],
                      dp[k] + Math.min(Math.abs(i - k), ring.length() - Math.abs(i - k)) + 1);
            }
          }
        }
      }

      dp = nextDp;
    }

    return Arrays.stream(dp).min().getAsInt();
  }
}
