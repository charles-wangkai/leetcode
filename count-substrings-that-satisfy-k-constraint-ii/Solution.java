// https://leetcode.com/problems/count-substrings-that-satisfy-k-constraint-ii/solutions/5653148/o-n-sliding-window/

import java.util.Arrays;

class Solution {
  public long[] countKConstraintSubstrings(String s, int k, int[][] queries) {
    int[] leftIndices = buildLeftIndices(s, k);
    int[] rightIndices = buildRightIndices(s, k);

    long[] prefixSums = new long[s.length()];
    for (int i = 0; i < prefixSums.length; ++i) {
      prefixSums[i] = ((i == 0) ? 0 : prefixSums[i - 1]) + (i - leftIndices[i] + 1);
    }

    return Arrays.stream(queries)
        .mapToLong(
            query -> {
              int middleIndex = Math.min(query[1], rightIndices[query[0]]);
              int length = middleIndex - query[0] + 1;

              return length * (length + 1L) / 2 + (prefixSums[query[1]] - prefixSums[middleIndex]);
            })
        .toArray();
  }

  int[] buildLeftIndices(String s, int k) {
    int[] result = new int[s.length()];
    int leftIndex = 0;
    int[] counts = new int[2];
    for (int i = 0; i < result.length; ++i) {
      ++counts[s.charAt(i) - '0'];
      while (counts[0] > k && counts[1] > k) {
        --counts[s.charAt(leftIndex) - '0'];
        ++leftIndex;
      }

      result[i] = leftIndex;
    }

    return result;
  }

  int[] buildRightIndices(String s, int k) {
    int[] result = new int[s.length()];
    int rightIndex = s.length() - 1;
    int[] counts = new int[2];
    for (int i = result.length - 1; i >= 0; --i) {
      ++counts[s.charAt(i) - '0'];
      while (counts[0] > k && counts[1] > k) {
        --counts[s.charAt(rightIndex) - '0'];
        --rightIndex;
      }

      result[i] = rightIndex;
    }

    return result;
  }
}