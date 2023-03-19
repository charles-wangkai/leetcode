class Solution {
  public int[] evenOddBit(int n) {
    int[] result = new int[2];
    for (int i = 0; n != 0; ++i) {
      if ((n & 1) == 1) {
        ++result[i & 1];
      }

      n >>= 1;
    }

    return result;
  }
}
