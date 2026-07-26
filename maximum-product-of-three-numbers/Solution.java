import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int maximumProduct(int[] nums) {
    Arrays.sort(nums);

    int[] candidates =
        IntStream.range(0, nums.length)
            .filter(i -> i < 3 || i >= nums.length - 3)
            .map(i -> nums[i])
            .toArray();

    int result = Integer.MIN_VALUE;
    for (int i = 0; i < candidates.length; ++i) {
      for (int j = i + 1; j < candidates.length; ++j) {
        for (int k = j + 1; k < candidates.length; ++k) {
          result = Math.max(result, candidates[i] * candidates[j] * candidates[k]);
        }
      }
    }

    return result;
  }
}
