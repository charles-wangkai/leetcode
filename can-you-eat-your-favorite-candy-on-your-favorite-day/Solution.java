class Solution {
  public boolean[] canEat(int[] candiesCount, int[][] queries) {
    long[] prefixSums = new long[candiesCount.length + 1];
    for (int i = 1; i < prefixSums.length; ++i) {
      prefixSums[i] = prefixSums[i - 1] + candiesCount[i - 1];
    }

    boolean[] result = new boolean[queries.length];
    for (int i = 0; i < result.length; ++i) {
      long minDay = prefixSums[queries[i][0]] / queries[i][2];
      long maxDay = prefixSums[queries[i][0] + 1] - 1;

      result[i] = queries[i][1] >= minDay && queries[i][1] <= maxDay;
    }

    return result;
  }
}
