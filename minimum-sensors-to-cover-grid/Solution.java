class Solution {
  public int minSensors(int n, int m, int k) {
    return computeSideNum(n, k) * computeSideNum(m, k);
  }

  int computeSideNum(int length, int k) {
    return (length + 2 * k) / (2 * k + 1);
  }
}