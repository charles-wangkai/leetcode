// https://leetcode.com/problems/maximum-total-reward-using-operations-ii/solutions/5282181/quadratic-dp-array-vs-bitset/

import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int maxTotalReward(int[] rewardValues) {
    Arrays.sort(rewardValues);

    boolean[] dp = new boolean[rewardValues[rewardValues.length - 1]];
    dp[0] = true;

    for (int rewardValue : rewardValues) {
      for (int i = Math.min(rewardValue, rewardValues[rewardValues.length - 1] - rewardValue) - 1;
          i >= 0;
          --i) {
        if (dp[i]) {
          dp[i + rewardValue] = true;
        }
      }
    }

    return rewardValues[rewardValues.length - 1]
        + IntStream.range(0, dp.length).filter(i -> dp[i]).max().getAsInt();
  }
}