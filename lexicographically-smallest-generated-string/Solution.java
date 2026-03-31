// https://leetcode.com/problems/lexicographically-smallest-generated-string/solutions/6485228/c-o-nm-greedy-w-rolling-hash-explanation/

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.stream.IntStream;

class Solution {
  static final ModInt MOD_INT = new ModInt(1_000_000_007);
  static final int BASE = 31;
  static final int LIMIT = 500;

  static int[] basePowers;

  static {
    basePowers = new int[LIMIT];
    basePowers[0] = 1;
    for (int i = 1; i < basePowers.length; ++i) {
      basePowers[i] = MOD_INT.multiplyMod(basePowers[i - 1], BASE);
    }
  }

  public String generateString(String str1, String str2) {
    char[] result = new char[str1.length() + str2.length() - 1];
    for (int i = 0; i < str1.length(); ++i) {
      if (str1.charAt(i) == 'T') {
        for (int j = 0; j < str2.length(); ++j) {
          result[i + j] = str2.charAt(j);
        }
      }
    }

    if (IntStream.range(0, str1.length())
        .anyMatch(
            i ->
                (str1.charAt(i) == 'T' && !String.valueOf(result, i, str2.length()).equals(str2))
                    || (str1.charAt(i) == 'F'
                        && String.valueOf(result, i, str2.length()).equals(str2)))) {
      return "";
    }

    int targetHash = computeHash(str2);
    int[] hashes = new int[str1.length()];
    Queue<Integer> falseIndices = new ArrayDeque<>();
    for (int i = 0; i < result.length; ++i) {
      if (i < str1.length() && str1.charAt(i) == 'F') {
        hashes[i] = computeHash(String.valueOf(result, i, str2.length()));
        falseIndices.offer(i);
      }

      while (!falseIndices.isEmpty() && falseIndices.peek() + str2.length() <= i) {
        falseIndices.poll();
      }

      if (result[i] == 0) {
        for (char c = 'a'; ; ++c) {
          char c_ = c;
          int i_ = i;
          if (falseIndices.stream()
              .allMatch(
                  falseIndex ->
                      updateHash(hashes[falseIndex], i_ - falseIndex, c_) != targetHash)) {
            for (int falseIndex : falseIndices) {
              hashes[falseIndex] = updateHash(hashes[falseIndex], i - falseIndex, c);
            }

            result[i] = c;

            break;
          }
        }
      }
    }

    return String.valueOf(result);
  }

  int updateHash(int oldHash, int index, char c) {
    return MOD_INT.addMod(oldHash, MOD_INT.multiplyMod(c - 'a' + 1, basePowers[index]));
  }

  int computeHash(String s) {
    int result = 0;
    for (int i = 0; i < s.length(); ++i) {
      if (s.charAt(i) != 0) {
        result = MOD_INT.addMod(result, MOD_INT.multiplyMod(s.charAt(i) - 'a' + 1, basePowers[i]));
      }
    }

    return result;
  }
}

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
