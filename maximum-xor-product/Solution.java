class Solution {
  static final int BIT_NUM = 50;
  static final int MODULUS = 1_000_000_007;

  public int maximumXorProduct(long a, long b, int n) {
    long value1 = 0;
    long value2 = 0;
    for (int i = BIT_NUM; i >= 0; --i) {
      if (i >= n) {
        value1 |= a & (1L << i);
        value2 |= b & (1L << i);
      } else if (((a >> i) & 1) == ((b >> i) & 1)) {
        value1 |= 1L << i;
        value2 |= 1L << i;
      } else if (value1 <= value2) {
        value1 |= 1L << i;
      } else {
        value2 |= 1L << i;
      }
    }

    return (int) ((value1 % MODULUS) * (value2 % MODULUS) % MODULUS);
  }
}
