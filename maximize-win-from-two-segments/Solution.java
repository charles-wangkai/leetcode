class Solution {
  public int maximizeWin(int[] prizePositions, int k) {
    int[] prizeNums = new int[prizePositions.length];
    int endIndex = 0;
    for (int i = 0; i < prizeNums.length; ++i) {
      while (endIndex != prizePositions.length - 1
          && prizePositions[endIndex + 1] - prizePositions[i] <= k) {
        ++endIndex;
      }

      prizeNums[i] = endIndex - i + 1;
    }

    int[] rightMaxPrizeNums = new int[prizeNums.length];
    int rightMaxPrizeNum = 0;
    for (int i = rightMaxPrizeNums.length - 1; i >= 0; --i) {
      rightMaxPrizeNum = Math.max(rightMaxPrizeNum, prizeNums[i]);
      rightMaxPrizeNums[i] = rightMaxPrizeNum;
    }

    int result = 0;
    int nextIndex = 0;
    for (int i = 0; i < prizeNums.length; ++i) {
      while (nextIndex != prizePositions.length
          && prizePositions[nextIndex] - prizePositions[i] <= k) {
        ++nextIndex;
      }

      result =
          Math.max(
              result,
              prizeNums[i]
                  + ((nextIndex == prizePositions.length) ? 0 : rightMaxPrizeNums[nextIndex]));
    }

    return result;
  }
}
