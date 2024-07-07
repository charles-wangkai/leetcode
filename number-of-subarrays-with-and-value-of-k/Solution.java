import java.util.stream.IntStream;

class Solution {
  static final int BIT_NUM = 30;

  public long countSubarrays(int[] nums, int k) {
    int[][] lastSameBitIndices = buildLastSameBitIndices(nums);

    return IntStream.range(0, nums.length)
        .map(beginIndex -> computeRangeLength(nums, k, lastSameBitIndices, beginIndex))
        .asLongStream()
        .sum();
  }

  int computeRangeLength(int[] nums, int k, int[][] lastSameBitIndices, int beginIndex) {
    int minLength = beginIndex;
    int maxLength = nums.length - 1;
    for (int b = 0; b < BIT_NUM; ++b) {
      int currentBit = (nums[beginIndex] >> b) & 1;

      if (((k >> b) & 1) == 0) {
        if (currentBit == 1) {
          minLength = Math.max(minLength, lastSameBitIndices[beginIndex][b] + 1);
        }
      } else if (currentBit == 0) {
        return 0;
      } else {
        maxLength = Math.min(maxLength, lastSameBitIndices[beginIndex][b]);
      }
    }

    return Math.max(0, maxLength - minLength + 1);
  }

  int[][] buildLastSameBitIndices(int[] nums) {
    int[][] result = new int[nums.length][BIT_NUM];
    for (int b = 0; b < BIT_NUM; ++b) {
      for (int i = result.length - 1; i >= 0; --i) {
        result[i][b] =
            (i != result.length - 1 && ((nums[i] >> b) & 1) == ((nums[i + 1] >> b) & 1))
                ? result[i + 1][b]
                : i;
      }
    }

    return result;
  }
}