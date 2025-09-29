import java.util.Arrays;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int zigZagArrays(int n, int l, int r) {
    int size = r - l + 1;

    int[][] transition = new int[size * 2][size * 2];
    for (int curr = 0; curr < size; ++curr) {
      for (int prev = 0; prev < curr; ++prev) {
        transition[prev + size][curr] = 1;
      }

      for (int prev = curr + 1; prev < size; ++prev) {
        transition[prev][curr + size] = 1;
      }
    }

    int[] initial = new int[size * 2];
    Arrays.fill(initial, 1);

    return Arrays.stream(multiply(initial, pow(transition, n - 1))).reduce(this::addMod).getAsInt();
  }

  int[] multiply(int[] v, int[][] m) {
    int size = v.length;

    int[] result = new int[size];
    for (int i = 0; i < result.length; ++i) {
      for (int j = 0; j < size; ++j) {
        result[i] = addMod(result[i], multiplyMod(v[j], m[j][i]));
      }
    }

    return result;
  }

  int[][] multiply(int[][] m1, int[][] m2) {
    int size = m1.length;

    int[][] result = new int[size][size];
    for (int i = 0; i < size; ++i) {
      for (int j = 0; j < size; ++j) {
        for (int k = 0; k < size; ++k) {
          result[i][j] = addMod(result[i][j], multiplyMod(m1[i][k], m2[k][j]));
        }
      }
    }

    return result;
  }

  int[][] pow(int[][] base, int exponent) {
    int size = base.length;

    if (exponent == 0) {
      return buildIdentity(size);
    }

    return multiply(
        (exponent % 2 == 0) ? buildIdentity(size) : base, pow(multiply(base, base), exponent / 2));
  }

  int[][] buildIdentity(int size) {
    int[][] result = new int[size][size];
    for (int i = 0; i < size; ++i) {
      result[i][i] = 1;
    }

    return result;
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}