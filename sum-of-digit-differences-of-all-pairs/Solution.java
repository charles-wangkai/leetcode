import java.util.Arrays;

class Solution {
  public long sumDigitDifferences(int[] nums) {
    long result = 0;
    while (Arrays.stream(nums).anyMatch(x -> x != 0)) {
      int[] counts = new int[10];
      for (int i = 0; i < nums.length; ++i) {
        ++counts[nums[i] % 10];
        nums[i] /= 10;
      }

      for (int i = 0; i < counts.length; ++i) {
        for (int j = i + 1; j < counts.length; ++j) {
          result += (long) counts[i] * counts[j];
        }
      }
    }

    return result;
  }
}