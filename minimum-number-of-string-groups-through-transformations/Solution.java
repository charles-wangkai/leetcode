import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  static final int BASE = 31;
  static final ModInt MOD_INT = new ModInt(1_000_000_007);

  public int minimumGroups(String[] words) {
    return (int) Arrays.stream(words).map(this::buildKey).distinct().count();
  }

  Key buildKey(String word) {
    return new Key(
        word.length(),
        computeMinHash(
            IntStream.range(0, word.length())
                .filter(i -> i % 2 == 0)
                .mapToObj(word::charAt)
                .map(String::valueOf)
                .collect(Collectors.joining())),
        computeMinHash(
            IntStream.range(0, word.length())
                .filter(i -> i % 2 == 1)
                .mapToObj(word::charAt)
                .map(String::valueOf)
                .collect(Collectors.joining())));
  }

  int computeMinHash(String s) {
    if (s.isEmpty()) {
      return 0;
    }

    int n = s.length();

    int basePower = MOD_INT.powMod(BASE, n - 1);

    int result = Integer.MAX_VALUE;
    int hash = 0;
    for (int i = 0; i < n - 1; ++i) {
      hash = MOD_INT.addMod(MOD_INT.multiplyMod(hash, BASE), s.charAt(i) - 'a' + 1);
    }
    for (int i = 0; i < n; ++i) {
      hash = MOD_INT.addMod(MOD_INT.multiplyMod(hash, BASE), s.charAt((i + n - 1) % n) - 'a' + 1);

      result = Math.min(result, hash);

      hash = MOD_INT.addMod(hash, -MOD_INT.multiplyMod(s.charAt(i) - 'a' + 1, basePower));
    }

    return result;
  }
}

record Key(int length, int minEvenHash, int minOddHash) {}

class ModInt {
  int modulus;

  ModInt(int modulus) {
    this.modulus = modulus;
  }

  int mod(long x) {
    return Math.floorMod(x, modulus);
  }

  int modInv(int x) {
    return BigInteger.valueOf(x).modInverse(BigInteger.valueOf(modulus)).intValue();
  }

  int addMod(int x, int y) {
    return mod(x + y);
  }

  int multiplyMod(int x, int y) {
    return mod((long) x * y);
  }

  int divideMod(int x, int y) {
    return multiplyMod(x, modInv(y));
  }

  int powMod(int base, long exponent) {
    if (exponent == 0) {
      return 1;
    }

    return multiplyMod(
        (exponent % 2 == 0) ? 1 : base, powMod(multiplyMod(base, base), exponent / 2));
  }
}
