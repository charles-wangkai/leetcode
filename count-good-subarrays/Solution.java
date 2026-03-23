import java.util.HashMap;
import java.util.Map;

class Solution {
  static final int BIT_NUM = 30;

  public long countGoodSubarrays(int[] nums) {
    int[][] prefixBitCounts = new int[nums.length + 1][BIT_NUM];
    for (int i = 1; i < prefixBitCounts.length; ++i) {
      for (int b = 0; b < BIT_NUM; ++b) {
        prefixBitCounts[i][b] = prefixBitCounts[i - 1][b] + ((nums[i - 1] >> b) & 1);
      }
    }

    long result = 0;
    Map<Integer, Integer> valueToLastIndex = new HashMap<>();
    for (int i = 0; i < nums.length; ++i) {
      result +=
          (computeLeftLength(nums, prefixBitCounts, valueToLastIndex, i) + 1L)
              * (computeRightLength(nums, prefixBitCounts, i) + 1);

      valueToLastIndex.put(nums[i], i);
    }

    return result;
  }

  int computeLeftLength(
      int[] nums, int[][] prefixBitCounts, Map<Integer, Integer> valueToLastIndex, int index) {
    int beginIndex = -1;
    int lower = valueToLastIndex.getOrDefault(nums[index], -1) + 1;
    int upper = index;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (computeRangeOr(prefixBitCounts, middle, index) == nums[index]) {
        beginIndex = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return index - beginIndex;
  }

  int computeRightLength(int[] nums, int[][] prefixBitCounts, int index) {
    int endIndex = -1;
    int lower = index;
    int upper = nums.length - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (computeRangeOr(prefixBitCounts, index, middle) == nums[index]) {
        endIndex = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return endIndex - index;
  }

  int computeRangeOr(int[][] prefixBitCounts, int beginIndex, int endIndex) {
    int result = 0;
    for (int b = 0; b < BIT_NUM; ++b) {
      if (prefixBitCounts[endIndex + 1][b] != prefixBitCounts[beginIndex][b]) {
        result += 1 << b;
      }
    }

    return result;
  }
}