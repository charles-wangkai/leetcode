import java.util.stream.IntStream;

class Solution {
  static final int BIT_NUM = 30;

  public int minimumDifference(int[] nums, int k) {
    int[][] prefixBitCounts = new int[nums.length + 1][BIT_NUM];
    for (int i = 1; i < prefixBitCounts.length; ++i) {
      for (int b = 0; b < BIT_NUM; ++b) {
        prefixBitCounts[i][b] = prefixBitCounts[i - 1][b] + ((nums[i - 1] >> b) & 1);
      }
    }

    return IntStream.range(0, nums.length)
        .map(beginIndex -> computeMinDiff(prefixBitCounts, k, beginIndex))
        .min()
        .getAsInt();
  }

  int computeMinDiff(int[][] prefixBitCounts, int k, int beginIndex) {
    return Math.min(
        findLessEqualDiff(prefixBitCounts, k, beginIndex),
        findGreaterEqualDiff(prefixBitCounts, k, beginIndex));
  }

  int findLessEqualDiff(int[][] prefixBitCounts, int k, int beginIndex) {
    int result = Integer.MAX_VALUE;
    int lower = beginIndex;
    int upper = prefixBitCounts.length - 2;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      int and = computeAnd(prefixBitCounts, beginIndex, middle);
      if (and <= k) {
        result = k - and;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  int findGreaterEqualDiff(int[][] prefixBitCounts, int k, int beginIndex) {
    int result = Integer.MAX_VALUE;
    int lower = beginIndex;
    int upper = prefixBitCounts.length - 2;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      int and = computeAnd(prefixBitCounts, beginIndex, middle);
      if (and >= k) {
        result = and - k;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  int computeAnd(int[][] prefixBitCounts, int beginIndex, int endIndex) {
    int result = 0;
    for (int b = 0; b < BIT_NUM; ++b) {
      if (prefixBitCounts[endIndex + 1][b] - prefixBitCounts[beginIndex][b]
          == endIndex - beginIndex + 1) {
        result += 1 << b;
      }
    }

    return result;
  }
}