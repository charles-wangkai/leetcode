class Solution {
  public int[] countBits(int n) {
    int[] oneNums = new int[n + 1];
    for (int i = 0; i <= n; ++i) {
      oneNums[i] = oneNums[i / 2] + i % 2;
    }

    return oneNums;
  }
}
