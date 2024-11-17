class Solution {
  public boolean isZeroArray(int[] nums, int[][] queries) {
    int[] deltas = new int[nums.length + 1];
    for (int[] query : queries) {
      ++deltas[query[0]];
      --deltas[query[1] + 1];
    }

    int upper = 0;
    for (int i = 0; i < nums.length; ++i) {
      upper += deltas[i];
      if (nums[i] > upper) {
        return false;
      }
    }

    return true;
  }
}