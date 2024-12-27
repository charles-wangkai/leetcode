class Solution {
  public int maxScoreSightseeingPair(int[] values) {
    int result = Integer.MIN_VALUE;
    int maxBefore = values[0];
    for (int i = 1; i < values.length; ++i) {
      result = Math.max(result, maxBefore + (values[i] - i));
      maxBefore = Math.max(maxBefore, values[i] + i);
    }

    return result;
  }
}
