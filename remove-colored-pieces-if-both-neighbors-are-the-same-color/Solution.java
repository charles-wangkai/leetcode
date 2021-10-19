class Solution {
  public boolean winnerOfGame(String colors) {
    int aliceNum = 0;
    int bobNum = 0;
    char current = 0;
    int count = 0;
    for (int i = 0; i <= colors.length(); ++i) {
      if (i != colors.length() && colors.charAt(i) == current) {
        ++count;
      } else {
        if (current == 'A') {
          aliceNum += Math.max(0, count - 2);
        } else if (current == 'B') {
          bobNum += Math.max(0, count - 2);
        }

        if (i != colors.length()) {
          current = colors.charAt(i);
          count = 1;
        }
      }
    }

    return aliceNum > bobNum;
  }
}
