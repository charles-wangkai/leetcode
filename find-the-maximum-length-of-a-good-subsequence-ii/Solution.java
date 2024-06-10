import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public int maximumLength(int[] nums, int k) {
    int[][] dp = new int[nums.length][k + 1];
    for (int i = 0; i < dp.length; ++i) {
      Arrays.fill(dp[i], -1);
    }

    Reporter[] reporters = new Reporter[k + 1];
    for (int i = 0; i < reporters.length; ++i) {
      reporters[i] = new Reporter();
    }

    for (int i = 0; i < dp.length; ++i) {
      dp[i][0] = 1;

      for (int j = 0; j < dp[i].length; ++j) {
        dp[i][j] =
            Math.max(
                dp[i][j],
                Math.max(
                        reporters[j].getDpFor(nums[i]),
                        (j == 0) ? -1 : reporters[j - 1].getMaxDpOtherThan(nums[i]))
                    + 1);
      }

      for (int j = 0; j < dp[i].length; ++j) {
        reporters[j].update(nums[i], dp[i][j]);
      }
    }

    int result = 0;
    for (int i = 0; i < dp.length; ++i) {
      for (int j = 0; j < dp[i].length; ++j) {
        result = Math.max(result, dp[i][j]);
      }
    }

    return result;
  }
}

class Reporter {
  Map<Integer, Integer> valueToDp = new HashMap<>();
  int value1 = -1;
  int value2 = -1;

  void update(int value, int dp) {
    valueToDp.put(value, Math.max(valueToDp.getOrDefault(value, -1), dp));

    if (value1 == -1 || valueToDp.get(value) > valueToDp.get(value1)) {
      value2 = value1;
      value1 = value;
    } else if (value2 == -1 || valueToDp.get(value) > valueToDp.get(value2)) {
      value2 = value;
    }
  }

  int getDpFor(int value) {
    return valueToDp.getOrDefault(value, -1);
  }

  int getMaxDpOtherThan(int value) {
    if (value1 != -1 && value1 != value) {
      return valueToDp.get(value1);
    }
    if (value2 != -1) {
      return valueToDp.get(value2);
    }

    return -1;
  }
}