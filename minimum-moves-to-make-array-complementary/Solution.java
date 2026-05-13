import java.util.stream.IntStream;

class Solution {
  public int minMoves(int[] nums, int limit) {
    int sumLimit = limit * 2 + 1;

    int[] counts = new int[sumLimit + 1];
    int[] lowers = new int[sumLimit + 1];
    int[] uppers = new int[sumLimit + 1];
    for (int i = 0; i < nums.length / 2; ++i) {
      ++counts[nums[i] + nums[nums.length - 1 - i]];

      ++lowers[Math.max(nums[i], nums[nums.length - 1 - i]) + limit + 1];
      ++uppers[Math.min(nums[i], nums[nums.length - 1 - i])];
    }

    for (int i = 1; i < lowers.length; ++i) {
      lowers[i] += lowers[i - 1];
    }
    for (int i = uppers.length - 2; i >= 0; --i) {
      uppers[i] += uppers[i + 1];
    }

    return IntStream.rangeClosed(0, sumLimit)
        .map(i -> nums.length / 2 - counts[i] + lowers[i] + uppers[i])
        .min()
        .getAsInt();
  }
}
