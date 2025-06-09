class Solution {
  public int findKthNumber(int n, int k) {
    int result = 1;
    --k;

    while (k != 0) {
      int current = computeNumForPrefix(n, result);
      if (current <= k) {
        ++result;
        k -= current;
      } else {
        result *= 10;
        --k;
      }
    }

    return result;
  }

  int computeNumForPrefix(int n, int prefix) {
    int result = 0;
    for (long first = prefix, last = prefix + 1; first <= n; first *= 10, last *= 10) {
      result += Math.min(n + 1, last) - first;
    }

    return result;
  }
}
