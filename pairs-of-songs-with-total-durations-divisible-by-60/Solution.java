class Solution {
  static final int MODULUS = 60;

  public int numPairsDivisibleBy60(int[] time) {
    int[] remainders = new int[MODULUS];
    for (int x : time) {
      ++remainders[x % MODULUS];
    }

    int result = 0;
    for (int i = 0; i * 2 <= MODULUS; ++i) {
      int other = (MODULUS - i) % MODULUS;
      if (i == other) {
        result += remainders[i] * (remainders[i] - 1L) / 2;
      } else {
        result += remainders[i] * remainders[other];
      }
    }

    return result;
  }
}
