import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public long countSubarrays(int[] nums, int k) {
    int max = Arrays.stream(nums).max().getAsInt();
    int[] indices = IntStream.range(0, nums.length).filter(i -> nums[i] == max).toArray();

    return IntStream.rangeClosed(0, indices.length - k)
        .mapToLong(
            i ->
                (long) (indices[i] - ((i == 0) ? -1 : indices[i - 1]))
                    * (nums.length - indices[i + k - 1]))
        .sum();
  }
}