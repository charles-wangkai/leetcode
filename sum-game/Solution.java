class Solution {
  public boolean sumGame(String num) {
    int leftSum = 0;
    int leftEmptyCount = 0;
    for (int i = 0; i < num.length() / 2; ++i) {
      if (num.charAt(i) == '?') {
        ++leftEmptyCount;
      } else {
        leftSum += num.charAt(i) - '0';
      }
    }

    int rightSum = 0;
    int rightEmptyCount = 0;
    for (int i = num.length() / 2; i < num.length(); ++i) {
      if (num.charAt(i) == '?') {
        ++rightEmptyCount;
      } else {
        rightSum += num.charAt(i) - '0';
      }
    }

    return isAliceWin(leftSum, leftEmptyCount, rightSum, rightEmptyCount);
  }

  boolean isAliceWin(int sum1, int emptyCount1, int sum2, int emptyCount2) {
    if (emptyCount1 > emptyCount2) {
      return isAliceWin(sum2, emptyCount2, sum1, emptyCount1);
    }

    emptyCount2 -= emptyCount1;
    if (emptyCount2 % 2 != 0) {
      return true;
    }

    int half = emptyCount2 / 2;

    return !canEqual(sum1 - sum2, half) || !canEqual(sum1 - sum2 - half * 9, half);
  }

  boolean canEqual(int diff, int emptyCount) {
    return diff >= 0 && diff <= emptyCount * 9;
  }
}
