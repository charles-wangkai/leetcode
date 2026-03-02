import java.util.Arrays;

class Solution {
  public int minSwaps(int[][] grid) {
    int n = grid.length;

    int[] trailingZeroNums = Arrays.stream(grid).mapToInt(this::computeTrailingZeroNum).toArray();

    int result = 0;
    for (int r = 0; r < n; ++r) {
      int lowerTrailingZeroNum = n - 1 - r;

      int goodIndex = r;
      while (goodIndex != n && trailingZeroNums[goodIndex] < lowerTrailingZeroNum) {
        ++goodIndex;
      }
      if (goodIndex == n) {
        return -1;
      }

      result += goodIndex - r;

      int temp = trailingZeroNums[goodIndex];
      for (int j = goodIndex; j >= r + 1; --j) {
        trailingZeroNums[j] = trailingZeroNums[j - 1];
      }
      trailingZeroNums[r] = temp;
    }

    return result;
  }

  int computeTrailingZeroNum(int[] line) {
    int result = 0;
    for (int c = line.length - 1; c >= 0 && line[c] == 0; --c) {
      ++result;
    }

    return result;
  }
}