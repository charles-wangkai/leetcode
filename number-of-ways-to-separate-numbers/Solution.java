// https://leetcode.com/problems/number-of-ways-to-separate-numbers/discuss/1417709/Accepted-oror-O(n2)-oror-DP-based-solution-oror-Explanation-oror-C%2B%2B-code-with-comments

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int numberOfCombinations(String num) {
    int n = num.length();

    if (num.charAt(0) == '0') {
      return 0;
    }

    int[][] sames = new int[n + 1][n + 1];
    for (int i = n - 1; i >= 0; --i) {
      for (int j = n - 1; j >= 0; --j) {
        if (num.charAt(i) == num.charAt(j)) {
          sames[i][j] = sames[i + 1][j + 1] + 1;
        }
      }
    }

    int[][] prefixSums = new int[n][n];
    for (int i = 0; i < n; ++i) {
      prefixSums[0][i] = 1;
    }

    for (int i = 1; i < n; ++i) {
      for (int j = i; j < n; ++j) {
        if (num.charAt(i) == '0') {
          prefixSums[i][j] = prefixSums[i - 1][j];
        } else {
          int length = j - i + 1;
          int prevStart = i - 1 - length + 1;
          int count = 0;
          if (prevStart < 0) {
            count = prefixSums[i - 1][i - 1];
          } else {
            count = subtractMod(prefixSums[i - 1][i - 1], prefixSums[prevStart][i - 1]);

            if (isNonDecreasing(prevStart, i, length, sames, num)) {
              count =
                  addMod(
                      count,
                      subtractMod(
                          prefixSums[prevStart][i - 1],
                          (prevStart == 0) ? 0 : prefixSums[prevStart - 1][i - 1]));
            }
          }

          prefixSums[i][j] = addMod(prefixSums[i - 1][j], count);
        }
      }
    }

    return prefixSums[n - 1][n - 1];
  }

  static boolean isNonDecreasing(int start1, int start2, int length, int[][] sames, String num) {
    return sames[start1][start2] >= length
        || num.charAt(start1 + sames[start1][start2]) < num.charAt(start2 + sames[start1][start2]);
  }

  static int addMod(int x, int y) {
    return (x + y) % MODULUS;
  }

  static int subtractMod(int x, int y) {
    return (x - y + MODULUS) % MODULUS;
  }
}
