import java.util.Arrays;

class Solution {
  public int minimumChanges(String s, int k) {
    int[][] changeCounts = buildChangeCounts(s);

    int[][] dp = new int[s.length() + 1][k + 1];
    for (int i = 0; i < dp.length; ++i) {
      Arrays.fill(dp[i], Integer.MAX_VALUE);
    }
    dp[0][0] = 0;

    for (int i = 1; i <= s.length(); ++i) {
      for (int j = 1; j <= k; ++j) {
        for (int prevLength = 0; prevLength < i; ++prevLength) {
          if (dp[prevLength][j - 1] != Integer.MAX_VALUE
              && changeCounts[prevLength][i - 1] != Integer.MAX_VALUE) {
            dp[i][j] = Math.min(dp[i][j], dp[prevLength][j - 1] + changeCounts[prevLength][i - 1]);
          }
        }
      }
    }

    return dp[s.length()][k];
  }

  int[][] buildChangeCounts(String s) {
    int[][] changeCounts = new int[s.length()][s.length()];
    for (int i = 0; i < changeCounts.length; ++i) {
      Arrays.fill(changeCounts[i], Integer.MAX_VALUE);
    }

    for (int centerLeftIndex = 0; centerLeftIndex < s.length(); ++centerLeftIndex) {
      for (int size = 1; size <= s.length() - centerLeftIndex; ++size) {
        int changeCount = 0;
        for (int leftIndex = centerLeftIndex - size, rightIndex = centerLeftIndex + size * 2 - 1;
            leftIndex >= 0 && rightIndex < s.length();
            leftIndex -= size, rightIndex += size) {
          for (int i = 0; i < size; ++i) {
            if (s.charAt(leftIndex + i) != s.charAt(rightIndex - size + 1 + i)) {
              ++changeCount;
            }
          }

          changeCounts[leftIndex][rightIndex] =
              Math.min(changeCounts[leftIndex][rightIndex], changeCount);
        }
      }

      for (int size = 1; 2 * size <= s.length() - centerLeftIndex; ++size) {
        int changeCount = 0;
        for (int leftIndex = centerLeftIndex, rightIndex = centerLeftIndex + size * 2 - 1;
            leftIndex >= 0 && rightIndex < s.length();
            leftIndex -= size, rightIndex += size) {
          for (int i = 0; i < size; ++i) {
            if (s.charAt(leftIndex + i) != s.charAt(rightIndex - size + 1 + i)) {
              ++changeCount;
            }
          }

          changeCounts[leftIndex][rightIndex] =
              Math.min(changeCounts[leftIndex][rightIndex], changeCount);
        }
      }
    }

    return changeCounts;
  }
}
