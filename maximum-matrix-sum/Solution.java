class Solution {
  public long maxMatrixSum(int[][] matrix) {
    long absSum = 0;
    int negCount = 0;
    int minAbs = Integer.MAX_VALUE;
    for (int[] line : matrix) {
      for (int x : line) {
        absSum += Math.abs(x);
        if (x < 0) {
          ++negCount;
        }
        minAbs = Math.min(minAbs, Math.abs(x));
      }
    }

    return absSum - ((negCount % 2 == 0) ? 0 : minAbs) * 2;
  }
}
