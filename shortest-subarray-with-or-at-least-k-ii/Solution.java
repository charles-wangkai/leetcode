class Solution {
  static final int BIT_NUM = 30;

  public int minimumSubarrayLength(int[] nums, int k) {
    int[][] bitPrefixCounts = new int[nums.length + 1][BIT_NUM];
    for (int i = 1; i < bitPrefixCounts.length; ++i) {
      for (int b = 0; b < BIT_NUM; ++b) {
        bitPrefixCounts[i][b] =
            bitPrefixCounts[i - 1][b] + ((((nums[i - 1] >> b) & 1) == 1) ? 1 : 0);
      }
    }

    int result = Integer.MAX_VALUE;
    for (int beginIndex = 0; beginIndex < nums.length; ++beginIndex) {
      int endIndex = -1;
      int lower = beginIndex;
      int upper = nums.length - 1;
      while (lower <= upper) {
        int middle = (lower + upper) / 2;
        if (computeOr(bitPrefixCounts, beginIndex, middle) >= k) {
          endIndex = middle;
          upper = middle - 1;
        } else {
          lower = middle + 1;
        }
      }

      if (endIndex != -1) {
        result = Math.min(result, endIndex - beginIndex + 1);
      }
    }

    return (result == Integer.MAX_VALUE) ? -1 : result;
  }

  int computeOr(int[][] bitPrefixCounts, int from, int to) {
    int result = 0;
    for (int b = 0; b < BIT_NUM; ++b) {
      if (bitPrefixCounts[to + 1][b] - bitPrefixCounts[from][b] >= 1) {
        result += 1 << b;
      }
    }

    return result;
  }
}