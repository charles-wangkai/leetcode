import java.util.Arrays;

class Solution {
  static final char MAX_LETTER = 'z';

  public String findTheString(int[][] lcp) {
    int n = lcp.length;

    char[] letters = new char[n];
    char next = 'a';
    for (int i = 0; i < letters.length; ++i) {
      boolean seen = false;
      for (int j = 0; j < i; ++j) {
        if (lcp[i][j] != 0) {
          letters[i] = letters[j];
          seen = true;

          break;
        }
      }

      if (!seen) {
        if (next == MAX_LETTER + 1) {
          return "";
        }

        letters[i] = next;
        ++next;
      }
    }

    return Arrays.deepEquals(lcp, buildExpectedLcp(letters)) ? new String(letters) : "";
  }

  int[][] buildExpectedLcp(char[] letters) {
    int n = letters.length;

    int[][] result = new int[n][n];
    for (int diff = 0; diff < n; ++diff) {
      int length = 0;
      for (int leftIndex = n - 1 - diff, rightIndex = n - 1;
          leftIndex >= 0;
          --leftIndex, --rightIndex) {
        length = (letters[leftIndex] == letters[rightIndex]) ? (length + 1) : 0;

        result[leftIndex][rightIndex] = length;
        result[rightIndex][leftIndex] = length;
      }
    }

    return result;
  }
}
