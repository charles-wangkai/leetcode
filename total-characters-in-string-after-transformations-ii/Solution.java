import java.util.Arrays;
import java.util.List;

class Solution {
  static final int MODULUS = 1_000_000_007;
  static final int SIZE = 26;

  public int lengthAfterTransformations(String s, int t, List<Integer> nums) {
    int[][] transition = new int[SIZE][SIZE];
    for (int i = 0; i < nums.size(); ++i) {
      for (int j = 0; j < nums.get(i); ++j) {
        transition[i][(i + j + 1) % SIZE] = 1;
      }
    }

    int[] initial = new int[SIZE];
    for (char c : s.toCharArray()) {
      ++initial[c - 'a'];
    }

    return Arrays.stream(multiply(initial, pow(transition, t))).reduce(this::addMod).getAsInt();
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