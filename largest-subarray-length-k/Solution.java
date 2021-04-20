import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int[] largestSubarray(int[] nums, int k) {
    int fromIndex =
        IntStream.rangeClosed(0, nums.length - k)
            .boxed()
            .max(Comparator.comparing(i -> nums[i]))
            .get();

    return Arrays.copyOfRange(nums, fromIndex, fromIndex + k);
  }
}
