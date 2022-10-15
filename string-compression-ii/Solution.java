import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  static final int ALPHABET_SIZE = 26;

  public int getLengthOfOptimalCompression(String s, int k) {
    int[][][] dp = new int[k + 1][ALPHABET_SIZE + 1][s.length() + 1];
    for (int i = 0; i <= k; ++i) {
      for (int j = 0; j <= ALPHABET_SIZE; ++j) {
        Arrays.fill(dp[i][j], Integer.MAX_VALUE);
      }
    }
    dp[k][0][0] = 0;

    for (char ch : s.toCharArray()) {
      int[][][] nextDp = new int[k + 1][ALPHABET_SIZE + 1][s.length() + 1];
      for (int i = 0; i <= k; ++i) {
        for (int j = 0; j <= ALPHABET_SIZE; ++j) {
          Arrays.fill(nextDp[i][j], Integer.MAX_VALUE);
        }
      }

      int value = ch - 'a' + 1;
      for (int i = 0; i <= k; ++i) {
        for (int j = 0; j <= ALPHABET_SIZE; ++j) {
          for (int c = 0; c <= s.length(); ++c) {
            if (dp[i][j][c] != Integer.MAX_VALUE) {
              if (i != 0) {
                nextDp[i - 1][j][c] = Math.min(nextDp[i - 1][j][c], dp[i][j][c]);
              }

              if (j == value) {
                if (c != s.length()) {
                  nextDp[i][j][c + 1] =
                      Math.min(
                          nextDp[i][j][c + 1],
                          dp[i][j][c] + computeCountLength(c + 1) - computeCountLength(c));
                }
              } else {
                nextDp[i][value][1] = Math.min(nextDp[i][value][1], dp[i][j][c] + 1);
              }
            }
          }
        }
      }

      dp = nextDp;
    }

    int[][][] dp_ = dp;
    return IntStream.rangeClosed(0, ALPHABET_SIZE)
        .map(i -> Arrays.stream(dp_[0][i]).min().getAsInt())
        .min()
        .getAsInt();
  }

  int computeCountLength(int count) {
    return (count == 1) ? 0 : String.valueOf(count).length();
  }
}
