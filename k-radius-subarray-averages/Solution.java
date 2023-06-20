import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int[] getAverages(int[] nums, int k) {
    int[] result = new int[nums.length];
    Arrays.fill(result, -1);

    long sum =
        IntStream.range(0, 2 * k)
            .filter(i -> i < nums.length)
            .map(i -> nums[i])
            .asLongStream()
            .sum();
    for (int rightIndex = 2 * k; rightIndex < nums.length; ++rightIndex) {
      sum += nums[rightIndex];
      result[rightIndex - k] = (int) (sum / (2 * k + 1));

      sum -= nums[rightIndex - 2 * k];
    }

    return result;
  }
}
