import java.util.stream.IntStream;

class Solution {
  public int minMaxGame(int[] nums) {
    return (nums.length == 1)
        ? nums[0]
        : minMaxGame(
            IntStream.range(0, nums.length / 2)
                .map(
                    i ->
                        (i % 2 == 0)
                            ? Math.min(nums[2 * i], nums[2 * i + 1])
                            : Math.max(nums[2 * i], nums[2 * i + 1]))
                .toArray());
  }
}