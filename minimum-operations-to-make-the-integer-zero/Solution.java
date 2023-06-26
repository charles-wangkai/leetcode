class Solution {
  public int makeTheIntegerZero(int num1, int num2) {
    long value = num1;
    for (int i = 1; ; ++i) {
      value -= num2;
      if (value < 0) {
        return -1;
      }

      if (i >= Long.bitCount(value) && i <= value) {
        return i;
      }
    }
  }
}
