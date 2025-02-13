import java.util.Arrays;

class Solution {
  public int maxDifference(String s, int k) {
    int result = Integer.MIN_VALUE;
    for (char oddLetter = '0'; oddLetter <= '4'; ++oddLetter) {
      for (char evenLetter = '0'; evenLetter <= '4'; ++evenLetter) {
        if (evenLetter != oddLetter) {
          result = Math.max(result, computeMaxDiff(s, k, oddLetter, evenLetter));
        }
      }
    }

    return result;
  }

  int computeMaxDiff(String s, int k, char oddLetter, char evenLetter) {
    int[] oddLetterPrefixCounts = buildPrefixCounts(s, oddLetter);
    int[] evenLetterPrefixCounts = buildPrefixCounts(s, evenLetter);

    int[] prefixDiffs = new int[s.length()];
    for (int i = 0; i < prefixDiffs.length; ++i) {
      prefixDiffs[i] = (i == 0) ? 0 : prefixDiffs[i - 1];
      if (s.charAt(i) == oddLetter) {
        ++prefixDiffs[i];
      } else if (s.charAt(i) == evenLetter) {
        --prefixDiffs[i];
      }
    }

    int[][] minDiffs = new int[2][2];
    for (int i = 0; i < minDiffs.length; ++i) {
      Arrays.fill(minDiffs[i], Integer.MAX_VALUE);
    }

    int result = Integer.MIN_VALUE;
    int beginIndex = -1;
    for (int endIndex = 0; endIndex < s.length(); ++endIndex) {
      if (endIndex == k - 1) {
        minDiffs[0][0] = Math.min(minDiffs[0][0], 0);
      }

      while (oddLetterPrefixCounts[beginIndex + 1] != oddLetterPrefixCounts[endIndex]
          && evenLetterPrefixCounts[beginIndex + 1] != evenLetterPrefixCounts[endIndex]
          && endIndex - (beginIndex + 1) >= k) {
        ++beginIndex;
        minDiffs[oddLetterPrefixCounts[beginIndex] % 2][evenLetterPrefixCounts[beginIndex] % 2] =
            Math.min(
                minDiffs[oddLetterPrefixCounts[beginIndex] % 2][
                    evenLetterPrefixCounts[beginIndex] % 2],
                prefixDiffs[beginIndex]);
      }

      if (evenLetterPrefixCounts[endIndex] != 0
          && minDiffs[1 - oddLetterPrefixCounts[endIndex] % 2][evenLetterPrefixCounts[endIndex] % 2]
              != Integer.MAX_VALUE) {
        result =
            Math.max(
                result,
                prefixDiffs[endIndex]
                    - minDiffs[1 - oddLetterPrefixCounts[endIndex] % 2][
                        evenLetterPrefixCounts[endIndex] % 2]);
      }
    }

    return result;
  }

  int[] buildPrefixCounts(String s, char letter) {
    int[] result = new int[s.length()];
    for (int i = 0; i < result.length; ++i) {
      result[i] = ((i == 0) ? 0 : result[i - 1]) + ((s.charAt(i) == letter) ? 1 : 0);
    }

    return result;
  }
}