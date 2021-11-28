import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int minimumDeletions(int[] nums) {
    if (nums.length == 1) {
      return 1;
    }

    int minValue = Arrays.stream(nums).min().getAsInt();
    int minIndex =
        IntStream.range(0, nums.length).filter(i -> nums[i] == minValue).findAny().getAsInt();

    int maxValue = Arrays.stream(nums).max().getAsInt();
    int maxIndex =
        IntStream.range(0, nums.length).filter(i -> nums[i] == maxValue).findAny().getAsInt();

    int leftIndex = Math.min(minIndex, maxIndex);
    int rightIndex = Math.max(minIndex, maxIndex);

    return Math.min(
        Math.min(rightIndex + 1, nums.length - leftIndex),
        (leftIndex + 1) + (nums.length - rightIndex));
  }
}