class Solution {
  public boolean threeFibonacciSum(int n) {
    int prev = 0;
    int curr = 1;
    while (true) {
      if (curr * 2 > n) {
        return false;
      }
      if (curr * 2 == n) {
        return true;
      }

      int next = prev + curr;
      prev = curr;
      curr = next;
    }
  }
}