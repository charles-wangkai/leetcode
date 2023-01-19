import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  static final int ALPHABET_NUM = 26;
  static final int MODULUS = 1_000_000_007;
  static final int LIMIT = 10000;

  int[] factorials;

  public int countGoodSubsequences(String s) {
    factorials = new int[LIMIT + 1];
    factorials[0] = 1;
    for (int i = 1; i < factorials.length; ++i) {
      factorials[i] = multiplyMod(factorials[i - 1], i);
    }

    int[] counts = new int[ALPHABET_NUM];
    for (char c : s.toCharArray()) {
      ++counts[c - 'a'];
    }

    return IntStream.rangeClosed(1, Arrays.stream(counts).max().getAsInt())
        .map(freq -> computeNum(counts, freq))
        .reduce(this::addMod)
        .getAsInt();
  }

  int computeNum(int[] counts, int freq) {
    return addMod(
        Arrays.stream(counts)
            .map(count -> (count < freq) ? 1 : addMod(CMod(count, freq), 1))
            .reduce(this::multiplyMod)
            .getAsInt(),
        -1);
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }

  int divideMod(int x, int y) {
    return multiplyMod(x, BigInteger.valueOf(y).modInverse(BigInteger.valueOf(MODULUS)).intValue());
  }

  int CMod(int n, int r) {
    return divideMod(factorials[n], multiplyMod(factorials[r], factorials[n - r]));
  }
}
