class Solution {
  static final int MODULUS = 1_000_000_007;
  static final int HASH_BASE_1 = 31;
  static final int HASH_BASE_2 = 37;

  public int numberOfWays(String s, String t, long k) {
    int sameCount = computeSameCount(s, t);
    int differentCount = s.length() - sameCount;

    int[] initialState = s.equals(t) ? new int[] {1, 0} : new int[] {0, 1};
    int[][] transition = {{sameCount - 1, differentCount}, {sameCount, differentCount - 1}};

    return multiply(initialState, pow(transition, k))[0];
  }

  int computeSameCount(String s, String t) {
    long targetHash1 = 0;
    long targetHash2 = 0;
    for (char c : t.toCharArray()) {
      targetHash1 = targetHash1 * HASH_BASE_1 + (c - 'a');
      targetHash2 = targetHash2 * HASH_BASE_2 + (c - 'a');
    }

    long maxPlaceValue1 = 1;
    long maxPlaceValue2 = 1;
    for (int i = 0; i < s.length() - 1; ++i) {
      maxPlaceValue1 *= HASH_BASE_1;
      maxPlaceValue2 *= HASH_BASE_2;
    }

    long hash1 = 0;
    long hash2 = 0;
    for (char c : s.toCharArray()) {
      hash1 = hash1 * HASH_BASE_1 + (c - 'a');
      hash2 = hash2 * HASH_BASE_2 + (c - 'a');
    }

    int result = 0;
    for (int i = 0; i < s.length(); ++i) {
      if (hash1 == targetHash1 && hash2 == targetHash2) {
        ++result;
      }

      int value = s.charAt(i) - 'a';
      hash1 = (hash1 - maxPlaceValue1 * value) * HASH_BASE_1 + value;
      hash2 = (hash2 - maxPlaceValue2 * value) * HASH_BASE_2 + value;
    }

    return result;
  }

  int[][] pow(int[][] m, long exponent) {
    int size = m.length;

    int[][] result = new int[size][size];
    for (int i = 0; i < size; ++i) {
      result[i][i] = 1;
    }

    while (exponent != 0) {
      if ((exponent & 1) == 1) {
        result = multiply(result, m);
      }

      m = multiply(m, m);
      exponent >>= 1;
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

  int[] multiply(int[] v, int[][] m) {
    int[] result = new int[v.length];
    for (int i = 0; i < result.length; ++i) {
      for (int j = 0; j < v.length; ++j) {
        result[i] = addMod(result[i], multiplyMod(v[j], m[j][i]));
      }
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
