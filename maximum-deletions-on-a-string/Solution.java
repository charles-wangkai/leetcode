import java.util.Arrays;

class Solution {
  static final int BASE = 31;
  static final int MODULUS = 1_000_000_007;

  public int deleteString(String s) {
    int[] dp = new int[s.length() + 1];
    Arrays.fill(dp, 1);
    dp[s.length()] = 0;
    for (int i = dp.length - 1; i >= 0; --i) {
      int leftHash = 0;
      int rightHash = 0;
      int placeValue = 1;
      for (int length = 1; i + length * 2 < dp.length; ++length) {
        leftHash = addMod(multiplyMod(leftHash, BASE), s.charAt(i + length - 1) - 'a');

        if (length != 1) {
          rightHash = addMod(multiplyMod(rightHash, BASE), s.charAt(i + length * 2 - 2) - 'a');
        }
        rightHash = addMod(multiplyMod(rightHash, BASE), s.charAt(i + length * 2 - 1) - 'a');

        if (leftHash == rightHash) {
          dp[i] = Math.max(dp[i], 1 + dp[i + length]);
        }

        rightHash = addMod(rightHash, -multiplyMod(s.charAt(i + length) - 'a', placeValue));
        placeValue = multiplyMod(placeValue, BASE);
      }
    }

    return dp[0];
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}
