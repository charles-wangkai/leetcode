class Solution {
  public boolean winnerOfGame(String colors) {
    int aliceNum = 0;
    int bobNum = 0;
    for (int i = 1; i < colors.length() - 1; ++i) {
      if (colors.charAt(i) == colors.charAt(i - 1) && colors.charAt(i) == colors.charAt(i + 1)) {
        if (colors.charAt(i) == 'A') {
          ++aliceNum;
        } else {
          ++bobNum;
        }
      }
    }

    return aliceNum > bobNum;
  }
}
