import java.util.stream.IntStream;

class Solution {
  public int sumOfGoodNumbers(int[] nums, int k) {
    return IntStream.range(0, nums.length)
        .filter(
            i ->
                (i - k < 0 || nums[i] > nums[i - k])
                    && (i + k >= nums.length || nums[i] > nums[i + k]))
        .map(i -> nums[i])
        .sum();
  }
}