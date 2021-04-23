import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int makeStringSorted(String s) {
    int[] factorials = new int[s.length() + 1];
    factorials[0] = 1;
    for (int i = 1; i < factorials.length; ++i) {
      factorials[i] = multiplyMod(i, factorials[i - 1]);
    }
    int[] factorialInvs = Arrays.stream(factorials).map(this::computeFactorialInv).toArray();

    int result = 0;
    int[] counts = new int[26];
    for (int i = s.length() - 1; i >= 0; --i) {
      char ch = s.charAt(i);
      ++counts[ch - 'a'];

      int term =
          multiplyMod(
              IntStream.range(0, ch - 'a').map(j -> counts[j]).reduce(0, this::addMod),
              factorials[s.length() - 1 - i]);
      for (int j = 0; j < counts.length; ++j) {
        term = multiplyMod(term, factorialInvs[counts[j]]);
      }

      result = addMod(result, term);
    }

    return result;
  }

  int addMod(int x, int y) {
    return (x + y) % MODULUS;
  }

  int multiplyMod(int x, int y) {
    return (int) ((long) x * y % MODULUS);
  }

  int computeFactorialInv(int x) {
    return BigInteger.valueOf(x).modInverse(BigInteger.valueOf(MODULUS)).intValue();
  }
}
