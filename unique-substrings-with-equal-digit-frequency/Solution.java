import java.util.HashSet;
import java.util.Set;

class Solution {
  static final int DIGIT_NUM = 10;
  static final int PLACE_VALUE = 11;

  public int equalDigitFrequency(String s) {
    int[][] prefixCounts = new int[s.length() + 1][DIGIT_NUM];
    for (int i = 1; i < prefixCounts.length; ++i) {
      for (int j = 0; j < DIGIT_NUM; ++j) {
        prefixCounts[i][j] = prefixCounts[i - 1][j] + ((s.charAt(i - 1) - '0' == j) ? 1 : 0);
      }
    }

    Set<Integer> hashs = new HashSet<>();
    for (int i = 0; i < s.length(); ++i) {
      int hash = 0;
      for (int j = i; j < s.length(); ++j) {
        hash = hash * PLACE_VALUE + s.charAt(j) - '0' + 1;
        if (check(prefixCounts, i, j)) {
          hashs.add(hash);
        }
      }
    }

    return hashs.size();
  }

  boolean check(int[][] prefixCounts, int beginIndex, int endIndex) {
    int nonZeroDiff = -1;
    for (int i = 0; i < DIGIT_NUM; ++i) {
      int diff = prefixCounts[endIndex + 1][i] - prefixCounts[beginIndex][i];
      if (diff != 0) {
        if (nonZeroDiff != -1 && diff != nonZeroDiff) {
          return false;
        }

        nonZeroDiff = diff;
      }
    }

    return true;
  }
}