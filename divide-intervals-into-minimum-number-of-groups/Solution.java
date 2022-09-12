class Solution {
  static final int LIMIT = 1_000_000;

  public int minGroups(int[][] intervals) {
    int[] deltas = new int[LIMIT + 2];
    for (int[] interval : intervals) {
      ++deltas[interval[0]];
      --deltas[interval[1] + 1];
    }

    int result = 0;
    int sum = 0;
    for (int delta : deltas) {
      sum += delta;
      result = Math.max(result, sum);
    }

    return result;
  }
}