class Solution {
  static final int MODULUS = 1_000_000_007;
  static final String[] PRESSES = {
    "2", "22", "222", //
    "3", "33", "333", //
    "4", "44", "444", //
    "5", "55", "555", //
    "6", "66", "666", //
    "7", "77", "777", "7777", //
    "8", "88", "888", //
    "9", "99", "999", "9999"
  };

  public int countTexts(String pressedKeys) {
    int[] dp = new int[pressedKeys.length() + 1];
    dp[0] = 1;
    for (int i = 0; i < dp.length; ++i) {
      for (String press : PRESSES) {
        if (pressedKeys.startsWith(press, i)) {
          dp[i + press.length()] = addMod(dp[i + press.length()], dp[i]);
        }
      }
    }

    return dp[dp.length - 1];
  }

  static int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}