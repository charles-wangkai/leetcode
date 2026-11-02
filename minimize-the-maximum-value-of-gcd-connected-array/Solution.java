class Solution {
  public long minimumMaxValue(int n, int index, int value) {
    if (value == 1) {
      return -1;
    }

    return (value > computeDiff(value, index)) ? (value + computeDiff(value, n - 1 - index)) : -1;
  }

  long computeDiff(int value, int step) {
    if (step == 0) {
      return 0;
    }
    if (value % 2 == 0) {
      return step * 2;
    }

    return findMinFactor(value) + (step - 1L) * 2;
  }

  int findMinFactor(int value) {
    for (int i = 2; i * i <= value; ++i) {
      if (value % i == 0) {
        return i;
      }
    }

    return value;
  }
}