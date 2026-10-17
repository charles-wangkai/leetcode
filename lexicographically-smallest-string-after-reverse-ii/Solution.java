import java.math.BigInteger;

class Solution {
  static final int BASE = 31;
  static final ModInt MOD_INT = new ModInt(1_000_000_007);

  public String lexSmallest(String s) {
    int n = s.length();

    int[] basePowers = buildBasePowers(n);

    int[] prefixHashes = buildPrefixHashes(s);
    int[] suffixHashes = buildSuffixHashes(s);

    Operation bestOperation = new Operation(true, 1);
    for (boolean firstOrLast : new boolean[] {false, true}) {
      for (int k = 1; k <= n; ++k) {
        Operation operation = new Operation(firstOrLast, k);
        if (isSmaller(s, basePowers, prefixHashes, suffixHashes, operation, bestOperation)) {
          bestOperation = operation;
        }
      }
    }

    return buildString(s, bestOperation);
  }

  int[] buildBasePowers(int n) {
    int[] result = new int[n + 1];
    result[0] = 1;

    for (int i = 1; i < result.length; ++i) {
      result[i] = MOD_INT.multiplyMod(result[i - 1], BASE);
    }

    return result;
  }

  boolean isSmaller(
      String s,
      int[] basePowers,
      int[] prefixHashes,
      int[] suffixHashes,
      Operation operation1,
      Operation operation2) {
    int n = prefixHashes.length;

    int commonLength =
        findCommonLength(basePowers, prefixHashes, suffixHashes, operation1, operation2);

    return commonLength != n
        && findLetter(s, operation1, commonLength) < findLetter(s, operation2, commonLength);
  }

  int findCommonLength(
      int[] basePowers,
      int[] prefixHashes,
      int[] suffixHashes,
      Operation operation1,
      Operation operation2) {
    int n = prefixHashes.length;

    int result = 0;
    int lower = 1;
    int upper = n;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (computeLeftHash(basePowers, prefixHashes, suffixHashes, operation1, middle)
          == computeLeftHash(basePowers, prefixHashes, suffixHashes, operation2, middle)) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  int computeLeftHash(
      int[] basePowers, int[] prefixHashes, int[] suffixHashes, Operation operation, int length) {
    int n = prefixHashes.length;

    if (operation.firstOrLast()) {
      if (length <= operation.k()) {
        return computeSuffixRangeHash(
            basePowers, suffixHashes, operation.k() - 1, operation.k() - length);
      }

      return MOD_INT.addMod(
          MOD_INT.multiplyMod(
              computeSuffixRangeHash(basePowers, suffixHashes, operation.k() - 1, 0),
              basePowers[length - operation.k()]),
          computePrefixRangeHash(basePowers, prefixHashes, operation.k(), length - 1));
    }

    if (length <= n - operation.k()) {
      return computePrefixRangeHash(basePowers, prefixHashes, 0, length - 1);
    }

    return MOD_INT.addMod(
        MOD_INT.multiplyMod(
            computePrefixRangeHash(basePowers, prefixHashes, 0, n - operation.k() - 1),
            basePowers[length - (n - operation.k())]),
        computeSuffixRangeHash(
            basePowers, suffixHashes, n - 1, n - (length - (n - operation.k()))));
  }

  int computePrefixRangeHash(int[] basePowers, int[] prefixHashes, int fromIndex, int toIndex) {
    if (toIndex < fromIndex) {
      return 0;
    }

    return MOD_INT.addMod(
        prefixHashes[toIndex],
        -MOD_INT.multiplyMod(
            (fromIndex == 0) ? 0 : prefixHashes[fromIndex - 1],
            basePowers[toIndex - fromIndex + 1]));
  }

  int computeSuffixRangeHash(int[] basePowers, int[] suffixHashes, int fromIndex, int toIndex) {
    if (toIndex > fromIndex) {
      return 0;
    }

    return MOD_INT.addMod(
        suffixHashes[toIndex],
        -MOD_INT.multiplyMod(
            (fromIndex == suffixHashes.length - 1) ? 0 : suffixHashes[fromIndex + 1],
            basePowers[fromIndex - toIndex + 1]));
  }

  char findLetter(String s, Operation operation, int index) {
    int originalIndex;
    if (operation.firstOrLast()) {
      originalIndex = (index < operation.k()) ? (operation.k() - 1 - index) : index;
    } else {
      originalIndex =
          (index >= s.length() - operation.k())
              ? ((s.length() - 1) + (s.length() - operation.k()) - index)
              : index;
    }

    return s.charAt(originalIndex);
  }

  String buildString(String s, Operation operation) {
    return operation.firstOrLast()
        ? (reverse(s.substring(0, operation.k())) + s.substring(operation.k()))
        : (s.substring(0, s.length() - operation.k())
            + reverse(s.substring(s.length() - operation.k())));
  }

  String reverse(String str) {
    return new StringBuilder(str).reverse().toString();
  }

  int[] buildPrefixHashes(String s) {
    int[] result = new int[s.length()];
    for (int i = 0; i < result.length; ++i) {
      result[i] =
          MOD_INT.addMod(
              MOD_INT.multiplyMod((i == 0) ? 0 : result[i - 1], BASE), s.charAt(i) - 'a');
    }

    return result;
  }

  int[] buildSuffixHashes(String s) {
    int[] result = new int[s.length()];
    for (int i = result.length - 1; i >= 0; --i) {
      result[i] =
          MOD_INT.addMod(
              MOD_INT.multiplyMod((i == result.length - 1) ? 0 : result[i + 1], BASE),
              s.charAt(i) - 'a');
    }

    return result;
  }
}

record Operation(boolean firstOrLast, int k) {}

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
