class Solution {
  public int smallestRepunitDivByK(int k) {
    boolean[] visited = new boolean[k];
    int remainder = 0;
    for (int length = 1; ; ++length) {
      remainder = (remainder * 10 + 1) % k;
      if (remainder == 0) {
        return length;
      }
      if (visited[remainder]) {
        return -1;
      }

      visited[remainder] = true;
    }
  }
}
