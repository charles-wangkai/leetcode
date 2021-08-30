class Solution {
  public int maxCount(int m, int n, int[][] ops) {
    int minR = m;
    int minC = n;
    for (int[] op : ops) {
      minR = Math.min(minR, op[0]);
      minC = Math.min(minC, op[1]);
    }

    return minR * minC;
  }
}
