class Solution {
  static final int BIT_LIMIT = 30;

  public int minimizeXor(int num1, int num2) {
    int result = 0;
    int rest = Integer.bitCount(num2);
    for (int i = BIT_LIMIT; i >= 0; --i) {
      if (rest != 0 && (num1 & (1 << i)) != 0) {
        result += 1 << i;
        --rest;
      }
    }
    for (int i = 0; i <= BIT_LIMIT; ++i) {
      if (rest != 0 && (num1 & (1 << i)) == 0) {
        result += 1 << i;
        --rest;
      }
    }

    return result;
  }
}
