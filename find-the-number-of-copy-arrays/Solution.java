class Solution {
  public int countArrays(int[] original, int[][] bounds) {
    int minValue = bounds[0][0];
    int maxValue = bounds[0][1];
    for (int i = 1; i < bounds.length; ++i) {
      minValue = Math.max(minValue, bounds[i][0] - original[i] + original[0]);
      maxValue = Math.min(maxValue, bounds[i][1] - original[i] + original[0]);
    }

    return Math.max(0, maxValue - minValue + 1);
  }
}