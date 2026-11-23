import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int minOperations(int[] nums) {
    int minLimit = Math.max(1, Arrays.stream(nums).min().getAsInt() - nums.length);
    int maxLimit = Arrays.stream(nums).max().getAsInt() + nums.length;

    int[][] dp = new int[maxLimit + 1][2];
    for (int last = 0; last < dp.length; ++last) {
      Arrays.fill(dp[last], Integer.MAX_VALUE);
    }
    for (int last = minLimit; last <= maxLimit; ++last) {
      dp[last][0] = Math.abs(last - nums[0]);
    }

    for (int i = 1; i < nums.length; ++i) {
      int[] lastToUpMinOperationNum = new int[maxLimit + 1];
      Arrays.fill(lastToUpMinOperationNum, Integer.MAX_VALUE);

      int upMinOperationNum = Integer.MAX_VALUE;
      for (int last = minLimit; last <= maxLimit; ++last) {
        if (dp[last][0] != Integer.MAX_VALUE) {
          upMinOperationNum = Math.min(upMinOperationNum, dp[last][0]);
        }

        if (upMinOperationNum != Integer.MAX_VALUE) {
          lastToUpMinOperationNum[last] = upMinOperationNum;
        }
      }

      int[] lastToDownMinOperationNum = new int[maxLimit + 2];
      Arrays.fill(lastToDownMinOperationNum, Integer.MAX_VALUE);

      int downMinOperationNum = Integer.MAX_VALUE;
      for (int last = maxLimit; last >= minLimit; --last) {
        if (dp[last][0] != Integer.MAX_VALUE) {
          downMinOperationNum = Math.min(downMinOperationNum, dp[last][0]);
        }
        if (dp[last][1] != Integer.MAX_VALUE) {
          downMinOperationNum = Math.min(downMinOperationNum, dp[last][1]);
        }

        if (downMinOperationNum != Integer.MAX_VALUE) {
          lastToDownMinOperationNum[last] = downMinOperationNum;
        }
      }

      int[][] nextDp = new int[maxLimit + 1][2];
      for (int last = 0; last < nextDp.length; ++last) {
        Arrays.fill(nextDp[last], Integer.MAX_VALUE);
      }

      for (int last = minLimit; last <= maxLimit; ++last) {
        if (lastToUpMinOperationNum[last - 1] != Integer.MAX_VALUE) {
          nextDp[last][0] = lastToUpMinOperationNum[last - 1] + Math.abs(last - nums[i]);
        }

        if (i != 1 && lastToDownMinOperationNum[last + 1] != Integer.MAX_VALUE) {
          nextDp[last][1] = lastToDownMinOperationNum[last + 1] + Math.abs(last - nums[i]);
        }
      }

      dp = nextDp;
    }

    int[][] dp_ = dp;
    int result =
        IntStream.rangeClosed(minLimit, maxLimit).map(last -> dp_[last][1]).min().getAsInt();

    return (result == Integer.MAX_VALUE) ? -1 : result;
  }
}
