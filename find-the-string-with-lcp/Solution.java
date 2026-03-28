import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;

class Solution {
  public String findTheString(int[][] lcp) {
    int n = lcp.length;

    char[] letters = new char[n];
    char next = 'a';
    for (int i = 0; i < letters.length; ++i) {
      int i_ = i;
      OptionalInt index = IntStream.range(0, i).filter(j -> lcp[i_][j] != 0).findAny();
      if (index.isPresent()) {
        letters[i] = letters[index.getAsInt()];
      } else {
        if (next == 'z' + 1) {
          return "";
        }

        letters[i] = next;
        ++next;
      }
    }

    return Arrays.deepEquals(lcp, buildExpectedLcp(letters)) ? String.valueOf(letters) : "";
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
