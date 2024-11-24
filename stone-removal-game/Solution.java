class Solution {
  public boolean canAliceWin(int n) {
    for (int i = 0; ; ++i) {
      int needed = 10 - i;
      if (n < needed) {
        return i % 2 == 1;
      }

      n -= needed;
    }
  }
}