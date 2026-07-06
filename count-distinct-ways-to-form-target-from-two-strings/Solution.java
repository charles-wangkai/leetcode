import java.math.BigInteger;

class Solution {
  static final ModInt MOD_INT = new ModInt(1_000_000_007);

  public int interleaveCharacters(String word1, String word2, String target) {
    int length1 = word1.length();
    int length2 = word2.length();

    int[][] dp = new int[length1 + 1][length2 + 1];
    dp[0][0] = 1;

    for (char c : target.toCharArray()) {
      int[][] nextDp = new int[length1 + 1][length2 + 1];

      for (int count1 = 0; count1 <= length1; ++count1) {
        for (int count2 = 0; count2 <= length2; ++count2) {
          for (int nextCount1 = count1 + 1; nextCount1 <= length1; ++nextCount1) {
            if (word1.charAt(nextCount1 - 1) == c) {
              nextDp[nextCount1][count2] =
                  MOD_INT.addMod(nextDp[nextCount1][count2], dp[count1][count2]);
            }
          }

          for (int nextCount2 = count2 + 1; nextCount2 <= length2; ++nextCount2) {
            if (word2.charAt(nextCount2 - 1) == c) {
              nextDp[count1][nextCount2] =
                  MOD_INT.addMod(nextDp[count1][nextCount2], dp[count1][count2]);
            }
          }
        }
      }

      dp = nextDp;
    }

    int result = 0;
    for (int count1 = 1; count1 <= length1; ++count1) {
      for (int count2 = 1; count2 <= length2; ++count2) {
        result = MOD_INT.addMod(result, dp[count1][count2]);
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
