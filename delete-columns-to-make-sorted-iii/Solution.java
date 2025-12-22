import java.util.Arrays;

class Solution {
  public int minDeletionSize(String[] strs) {
    int[] dp = new int[strs[0].length()];
    for (int i = 0; i < dp.length; ++i) {
      dp[i] = 1;
      for (int j = 0; j < i; ++j) {
        if (canConcat(strs, j, i)) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
    }

    return dp.length - Arrays.stream(dp).max().getAsInt();
  }

  boolean canConcat(String[] strs, int index1, int index2) {
    return Arrays.stream(strs).allMatch(s -> s.charAt(index1) <= s.charAt(index2));
  }
}
