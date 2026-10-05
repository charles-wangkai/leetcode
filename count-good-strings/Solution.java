import java.math.BigInteger;

class Solution {
  static final ModInt MOD_INT = new ModInt(1_000_000_007);

  public int countGoodStrings(long n) {
    int[] state = {2, 0};
    int[][] transition = {{1, 1}, {1, 0}};

    return multiply(state, pow(transition, n - 1))[0];
  }

  int[][] pow(int[][] base, long exponent) {
    int n = base.length;

    if (exponent == 0) {
      return buildEntity(n);
    }

    return multiply(
        (exponent % 2 == 0) ? buildEntity(n) : base, pow(multiply(base, base), exponent / 2));
  }

  int[] multiply(int[] v, int[][] m) {
    int n = v.length;

    int[] result = new int[n];
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < n; ++j) {
        result[i] = MOD_INT.addMod(result[i], MOD_INT.multiplyMod(v[j], m[j][i]));
      }
    }

    return result;
  }

  int[][] multiply(int[][] m1, int[][] m2) {
    int n = m1.length;

    int[][] result = new int[n][n];
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < n; ++j) {
        for (int k = 0; k < n; ++k) {
          result[i][j] = MOD_INT.addMod(result[i][j], MOD_INT.multiplyMod(m1[i][k], m2[k][j]));
        }
      }
    }

    return result;
  }

  int[][] buildEntity(int n) {
    int[][] result = new int[n][n];
    for (int i = 0; i < n; ++i) {
      result[i][i] = 1;
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
