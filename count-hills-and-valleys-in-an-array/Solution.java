import java.util.stream.IntStream;

class Solution {
  public int countHillValley(int[] nums) {
    int[] groups =
        IntStream.range(0, nums.length)
            .filter(i -> i == 0 || nums[i] != nums[i - 1])
            .map(i -> nums[i])
            .toArray();

    return (int)
        IntStream.range(1, groups.length - 1)
            .filter(
                i ->
                    groups[i] > Math.max(groups[i - 1], groups[i + 1])
                        || groups[i] < Math.min(groups[i - 1], groups[i + 1]))
            .count();
  }
}