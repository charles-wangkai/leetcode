class Solution {
  public int rangeBitwiseAnd(int left, int right) {
    int base = 1;
    while (left != right) {
      left >>= 1;
      right >>= 1;
      base <<= 1;
    }

    return left * base;
  }
}
