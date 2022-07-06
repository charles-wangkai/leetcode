class Solution {
  public int fib(int n) {
    int prev = 1;
    int curr = 0;
    for (int i = 0; i < n; ++i) {
      int next = prev + curr;

      prev = curr;
      curr = next;
    }

    return curr;
  }
}
