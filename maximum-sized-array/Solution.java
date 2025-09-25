class Solution {
  public int maxSizedArray(long s) {
    int result = -1;
    int lower = 0;
    int upper = 1200;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(s, middle)) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  boolean check(long s, int n) {
    long sum = 0;
    for (int j = 0; j < n; ++j) {
      for (int k = 0; k < n; ++k) {
        sum += (n - 1) * n / 2 * (j | k);
      }
    }

    return sum <= s;
  }
}