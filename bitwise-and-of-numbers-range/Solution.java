class Solution {
  public int rangeBitwiseAnd(int left, int right) {
    int base = 1;
    int result = 0;
    while (right != 0) {
      if (left == right && (left & 1) != 0) {
        result += base;
      }

      left >>= 1;
      right >>= 1;
      base <<= 1;
    }

    return result;
  }
}
