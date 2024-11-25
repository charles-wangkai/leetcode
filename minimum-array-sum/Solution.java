import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public int minArraySum(int[] nums, int k, int op1, int op2) {
    Arrays.sort(nums);

    int[][] dp = new int[op1 + 1][op2 + 1];
    for (int i = nums.length - 1; i >= 0; --i) {
      for (int p = op1; p >= 0; --p) {
        for (int q = op2; q >= 0; --q) {
          List<Integer> candidates = new ArrayList<>();
          candidates.add(dp[p][q] + nums[i]);
          if (p != 0) {
            candidates.add(dp[p - 1][q] + (nums[i] + 1) / 2);
          }
          if (q != 0 && nums[i] >= k) {
            candidates.add(dp[p][q - 1] + (nums[i] - k));
          }
          if (p != 0 && q != 0) {
            if ((nums[i] + 1) / 2 >= k) {
              candidates.add(dp[p - 1][q - 1] + ((nums[i] + 1) / 2 - k));
            }
            if (nums[i] >= k) {
              candidates.add(dp[p - 1][q - 1] + ((nums[i] - k) + 1) / 2);
            }
          }

          dp[p][q] = candidates.stream().mapToInt(Integer::intValue).min().getAsInt();
        }
      }
    }

    int result = Integer.MAX_VALUE;
    for (int[] dpi : dp) {
      for (int x : dpi) {
        result = Math.min(result, x);
      }
    }

    return result;
  }
}