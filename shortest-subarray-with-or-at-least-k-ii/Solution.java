import java.util.stream.IntStream;

class Solution {
  static final int BIT_NUM = 30;

  public int minimumSubarrayLength(int[] nums, int k) {
    int[] bitCounts = new int[BIT_NUM];

    int result = Integer.MAX_VALUE;
    int endIndex = -1;
    for (int beginIndex = 0; beginIndex < nums.length; ++beginIndex) {
      while (endIndex + 1 != nums.length && (endIndex < beginIndex || computeOr(bitCounts) < k)) {
        ++endIndex;

        for (int i = 0; i < bitCounts.length; ++i) {
          bitCounts[i] += (nums[endIndex] >> i) & 1;
        }
      }

      if (endIndex >= beginIndex && computeOr(bitCounts) >= k) {
        result = Math.min(result, endIndex - beginIndex + 1);
      }

      for (int i = 0; i < bitCounts.length; ++i) {
        bitCounts[i] -= (nums[beginIndex] >> i) & 1;
      }
    }

    return (result == Integer.MAX_VALUE) ? -1 : result;
  }

  int computeOr(int[] bitCounts) {
    return IntStream.range(0, bitCounts.length)
        .filter(i -> bitCounts[i] != 0)
        .map(i -> 1 << i)
        .sum();
  }
}