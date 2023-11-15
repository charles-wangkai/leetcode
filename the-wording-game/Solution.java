class Solution {
  public boolean canAliceWin(String[] a, String[] b) {
    boolean[] aWins = new boolean[a.length];
    boolean[] bWins = new boolean[b.length];
    int aWinCount = 0;
    int bWinCount = 0;
    int aBeginIndex = a.length;
    int bBeginIndex = b.length;
    int aEndIndex = a.length - 1;
    int bEndIndex = b.length - 1;
    while (aBeginIndex != 0 || bBeginIndex != 0) {
      if (bBeginIndex == 0
          || (aBeginIndex != 0 && a[aBeginIndex - 1].compareTo(b[bBeginIndex - 1]) > 0)) {
        --aBeginIndex;

        while (bEndIndex >= bBeginIndex && b[bEndIndex].charAt(0) - a[aBeginIndex].charAt(0) > 1) {
          if (bWins[bEndIndex]) {
            --bWinCount;
          }

          --bEndIndex;
        }

        aWins[aBeginIndex] = bWinCount == 0;
        if (aWins[aBeginIndex]) {
          ++aWinCount;
        }
      } else {
        --bBeginIndex;

        while (aEndIndex >= aBeginIndex && a[aEndIndex].charAt(0) - b[bBeginIndex].charAt(0) > 1) {
          if (aWins[aEndIndex]) {
            --aWinCount;
          }

          --aEndIndex;
        }

        bWins[bBeginIndex] = aWinCount == 0;
        if (bWins[bBeginIndex]) {
          ++bWinCount;
        }
      }
    }

    return aWins[0];
  }
}
