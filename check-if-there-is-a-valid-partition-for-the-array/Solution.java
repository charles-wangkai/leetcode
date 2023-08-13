class Solution {
  public boolean validPartition(int[] nums) {
    boolean[] dp = new boolean[nums.length + 1];
    dp[0] = true;
    for (int i = 1; i < dp.length; ++i) {
      dp[i] =
          (i >= 2 && dp[i - 2] && nums[i - 1] == nums[i - 2])
              || (i >= 3
                  && dp[i - 3]
                  && ((nums[i - 1] == nums[i - 2] && nums[i - 2] == nums[i - 3])
                      || (nums[i - 1] == nums[i - 2] + 1 && nums[i - 2] == nums[i - 3] + 1)));
    }

    return dp[dp.length - 1];
  }
}
