import java.util.Arrays;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int[] waysToFillArray(int[][] queries) {
    int[][] c = new int[10050][15];
    c[0][0] = 1;
    for (int i = 1; i < c.length; ++i) {
      c[i][0] = 1;
      for (int j = 1; j < c[i].length; ++j) {
        c[i][j] = addMod(c[i - 1][j], c[i - 1][j - 1]);
      }
    }

    return Arrays.stream(queries)
        .mapToInt(
            query -> {
              int n = query[0];
              int k = query[1];

              int result = 1;
              while (k != 1) {
                for (int i = 2; i <= k; ++i) {
                  int exponent = 0;
                  while (k % i == 0) {
                    k /= i;
                    ++exponent;
                  }

                  result = multiplyMod(result, c[exponent + n - 1][exponent]);
                }
              }

              return result;
            })
        .toArray();
  }

  int addMod(int x, int y) {
    return (x + y) % MODULUS;
  }

  int multiplyMod(int x, int y) {
    return (int) ((long) x * y % MODULUS);
  }
}
