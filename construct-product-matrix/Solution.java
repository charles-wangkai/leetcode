import java.math.BigInteger;

class Solution {
  static final ModInt MOD_INT = new ModInt(12345);

  public int[][] constructProductMatrix(int[][] grid) {
    int n = grid.length;
    int m = grid[0].length;

    int[][] leftProducts = new int[n][m];
    for (int r = 0; r < n; ++r) {
      for (int c = 0; c < m; ++c) {
        leftProducts[r][c] = MOD_INT.multiplyMod((c == 0) ? 1 : leftProducts[r][c - 1], grid[r][c]);
      }
    }

    int[][] rightProducts = new int[n][m];
    for (int r = 0; r < n; ++r) {
      for (int c = m - 1; c >= 0; --c) {
        rightProducts[r][c] =
            MOD_INT.multiplyMod((c == m - 1) ? 1 : rightProducts[r][c + 1], grid[r][c]);
      }
    }

    int[] topProducts = new int[n];
    for (int r = 0; r < n; ++r) {
      topProducts[r] =
          MOD_INT.multiplyMod((r == 0) ? 1 : topProducts[r - 1], leftProducts[r][m - 1]);
    }

    int[] bottomProducts = new int[n];
    for (int r = n - 1; r >= 0; --r) {
      bottomProducts[r] =
          MOD_INT.multiplyMod((r == n - 1) ? 1 : bottomProducts[r + 1], leftProducts[r][m - 1]);
    }

    int[][] result = new int[n][m];
    for (int r = 0; r < n; ++r) {
      for (int c = 0; c < m; ++c) {
        result[r][c] =
            MOD_INT.multiplyMod(
                MOD_INT.multiplyMod(
                    (r == 0) ? 1 : topProducts[r - 1], (r == n - 1) ? 1 : bottomProducts[r + 1]),
                MOD_INT.multiplyMod(
                    (c == 0) ? 1 : leftProducts[r][c - 1],
                    (c == m - 1) ? 1 : rightProducts[r][c + 1]));
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
