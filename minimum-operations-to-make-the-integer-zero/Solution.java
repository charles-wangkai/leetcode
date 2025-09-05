class Solution {
  public int makeTheIntegerZero(int num1, int num2) {
    long rest = num1;
    for (int result = 1; ; ++result) {
      rest -= num2;
      if (rest < 0) {
        return -1;
      }

      if (result >= Long.bitCount(rest) && result <= rest) {
        return result;
      }
    }
  }
}
