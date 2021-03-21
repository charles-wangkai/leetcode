class Solution {
  public int maxValue(int n, int index, int maxSum) {
    int result = -1;
    int lower = 1;
    int upper = maxSum;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(n, index, maxSum, middle)) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  boolean check(int n, int index, int maxSum, int value) {
    return computeMinSum(value, index) + value + computeMinSum(value, n - 1 - index) <= maxSum;
  }

  long computeMinSum(int value, int length) {
    int begin = Math.max(1, value - 1);

    if (length >= begin) {
      return begin * (begin + 1L) / 2 + (length - begin);
    }

    return (begin + (begin - length + 1L)) * length / 2;
  }
}
