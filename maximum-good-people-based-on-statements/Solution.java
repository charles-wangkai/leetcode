class Solution {
  public int maximumGood(int[][] statements) {
    int n = statements.length;

    int result = -1;
    for (int mask = 0; mask < 1 << n; ++mask) {
      if (check(statements, mask)) {
        result = Math.max(result, Integer.bitCount(mask));
      }
    }

    return result;
  }

  boolean check(int[][] statements, int mask) {
    for (int i = 0; i < statements.length; ++i) {
      if ((mask & (1 << i)) != 0) {
        for (int j = 0; j < statements[i].length; ++j) {
          if ((statements[i][j] == 0 && (mask & (1 << j)) != 0)
              || (statements[i][j] == 1 && (mask & (1 << j)) == 0)) {
            return false;
          }
        }
      }
    }

    return true;
  }
}