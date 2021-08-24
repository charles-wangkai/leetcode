class Solution {
  public long maxMatrixSum(int[][] matrix) {
    long sum = 0;
    int negCount = 0;
    int minAbs = Integer.MAX_VALUE;
    for (int[] line : matrix) {
      for (int x : line) {
        sum += Math.abs(x);
        if (x < 0) {
          ++negCount;
        }
        minAbs = Math.min(minAbs, Math.abs(x));
      }
    }

    return sum - ((negCount % 2 == 0) ? 0 : minAbs) * 2;
  }
}
