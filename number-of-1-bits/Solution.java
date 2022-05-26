class Solution {
  public int hammingWeight(int n) {
    long number = (long) n;
    if (number < 0) {
      number += 1L << 32;
    }

    return Long.bitCount(number);
  }
}
