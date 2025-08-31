// https://leetcode.com/problems/minimum-operations-to-equalize-binary-string/solutions/7138938/python-math/

class Solution {
  public int minOperations(String s, int k) {
    int n = s.length();
    int z = (int) s.chars().filter(c -> c == '0').count();

    if (k == n) {
      if (z == 0) {
        return 0;
      }
      if (z == n) {
        return 1;
      }

      return -1;
    }

    int result = Integer.MAX_VALUE;
    if (z % 2 == 0) {
      int m = Math.max(Math.ceilDiv(z, k), Math.ceilDiv(z, n - k));
      m += m & 1;

      result = Math.min(result, m);
    }
    if (z % 2 == k % 2) {
      int m = Math.max(Math.ceilDiv(z, k), Math.ceilDiv(n - z, n - k));
      m += 1 - (m & 1);

      result = Math.min(result, m);
    }

    return (result == Integer.MAX_VALUE) ? -1 : result;
  }
}
