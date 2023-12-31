// https://leetcode.com/problems/palindrome-rearrangement-queries/solutions/4481115/prefix-sum-and-substring-equal-index/

import java.util.stream.IntStream;

class Solution {
  static final int ALPHABET_SIZE = 26;

  public boolean[] canMakePalindromeQueries(String s, int[][] queries) {
    int n = s.length() / 2;
    String s1 = s.substring(0, n);
    String s2 = new StringBuilder(s.substring(n)).reverse().toString();

    int[] maxSameLengths = new int[n];
    int beginIndex = 0;
    for (int i = 0; i <= n; ++i) {
      if (i == n || s1.charAt(i) != s2.charAt(i)) {
        for (int j = beginIndex; j < i; ++j) {
          maxSameLengths[j] = i - j;
        }

        beginIndex = i + 1;
      }
    }

    int[][] prefixSums1 = buildPrefixSums(s1);
    int[][] prefixSums2 = buildPrefixSums(s2);

    boolean[] result = new boolean[queries.length];
    for (int i = 0; i < result.length; ++i) {
      result[i] =
          canBePalindrome(
              s1,
              s2,
              maxSameLengths,
              prefixSums1,
              prefixSums2,
              queries[i][0],
              queries[i][1],
              2 * n - 1 - queries[i][3],
              2 * n - 1 - queries[i][2]);
    }

    return result;
  }

  boolean canBePalindrome(
      String s1,
      String s2,
      int[] maxSameLengths,
      int[][] prefixSums1,
      int[][] prefixSums2,
      int beginIndex1,
      int endIndex1,
      int beginIndex2,
      int endIndex2) {
    if (beginIndex1 > beginIndex2) {
      return canBePalindrome(
          s2,
          s1,
          maxSameLengths,
          prefixSums2,
          prefixSums1,
          beginIndex2,
          endIndex2,
          beginIndex1,
          endIndex1);
    }

    int n = s1.length();

    if (endIndex1 < beginIndex2) {
      return isSame(maxSameLengths, 0, beginIndex1 - 1)
          && isSame(maxSameLengths, endIndex1 + 1, beginIndex2 - 1)
          && isSame(maxSameLengths, endIndex2 + 1, n - 1)
          && isRearranged(s1, s2, prefixSums1, prefixSums2, beginIndex1, endIndex1)
          && isRearranged(s1, s2, prefixSums1, prefixSums2, beginIndex2, endIndex2);
    }
    if (endIndex1 >= endIndex2) {
      return isSame(maxSameLengths, 0, beginIndex1 - 1)
          && isSame(maxSameLengths, endIndex1 + 1, n - 1)
          && canCover(
              s1,
              s2,
              prefixSums1,
              prefixSums2,
              beginIndex1,
              endIndex1,
              beginIndex1,
              beginIndex2 - 1)
          && canCover(
              s1, s2, prefixSums1, prefixSums2, beginIndex1, endIndex1, endIndex2 + 1, endIndex1)
          && isRearranged(s1, s2, prefixSums1, prefixSums2, beginIndex1, endIndex1);
    }

    return isSame(maxSameLengths, 0, beginIndex1 - 1)
        && isSame(maxSameLengths, endIndex2 + 1, n - 1)
        && canCover(
            s1, s2, prefixSums1, prefixSums2, beginIndex1, endIndex1, endIndex2 + 1, endIndex1)
        && canCover(
            s2, s1, prefixSums2, prefixSums1, beginIndex2, endIndex2, endIndex1 + 1, endIndex2)
        && isRearranged(s1, s2, prefixSums1, prefixSums2, beginIndex1, endIndex2);
  }

  boolean isSame(int[] maxSameLengths, int beginIndex, int endIndex) {
    return beginIndex > endIndex || endIndex - beginIndex + 1 <= maxSameLengths[beginIndex];
  }

  boolean isRearranged(
      String s1,
      String s2,
      int[][] prefixSums1,
      int[][] prefixSums2,
      int beginIndex,
      int endIndex) {
    return canCover(s1, s2, prefixSums1, prefixSums2, beginIndex, endIndex, beginIndex, endIndex);
  }

  boolean canCover(
      String s1,
      String s2,
      int[][] prefixSums1,
      int[][] prefixSums2,
      int beginIndex1,
      int endIndex1,
      int beginIndex2,
      int endIndex2) {
    return IntStream.range(0, ALPHABET_SIZE)
        .allMatch(
            i ->
                computeRangeSum(prefixSums1[i], beginIndex1, endIndex1)
                    >= computeRangeSum(prefixSums2[i], beginIndex2, endIndex2));
  }

  int computeRangeSum(int[] prefixSums, int beginIndex, int endIndex) {
    return (beginIndex > endIndex)
        ? 0
        : (prefixSums[endIndex] - ((beginIndex == 0) ? 0 : prefixSums[beginIndex - 1]));
  }

  int[][] buildPrefixSums(String str) {
    int[][] result = new int[ALPHABET_SIZE][str.length()];
    for (int i = 0; i < ALPHABET_SIZE; ++i) {
      for (int j = 0; j < str.length(); ++j) {
        result[i][j] = ((j == 0) ? 0 : result[i][j - 1]) + ((str.charAt(j) - 'a' == i) ? 1 : 0);
      }
    }

    return result;
  }
}