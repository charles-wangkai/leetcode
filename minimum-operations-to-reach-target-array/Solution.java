import java.util.stream.IntStream;

class Solution {
  public int minOperations(int[] nums, int[] target) {
    return (int)
        IntStream.range(0, nums.length)
            .filter(i -> nums[i] != target[i])
            .map(i -> nums[i])
            .distinct()
            .count();
  }
}