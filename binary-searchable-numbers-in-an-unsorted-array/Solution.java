import java.util.stream.IntStream;

class Solution {
  public int binarySearchableNumbers(int[] nums) {
    boolean[] largerThanLefts = new boolean[nums.length];
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < largerThanLefts.length; ++i) {
      largerThanLefts[i] = nums[i] > max;
      max = Math.max(max, nums[i]);
    }

    boolean[] lessThanRights = new boolean[nums.length];
    int min = Integer.MAX_VALUE;
    for (int i = lessThanRights.length - 1; i >= 0; --i) {
      lessThanRights[i] = nums[i] < min;
      min = Math.min(min, nums[i]);
    }

    return (int)
        IntStream.range(0, nums.length)
            .filter(i -> largerThanLefts[i] && lessThanRights[i])
            .count();
  }
}