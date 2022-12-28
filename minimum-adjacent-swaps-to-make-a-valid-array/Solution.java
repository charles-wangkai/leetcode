import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int minimumSwaps(int[] nums) {
    int minValue = Arrays.stream(nums).min().getAsInt();
    int minIndex =
        IntStream.range(0, nums.length).filter(i -> nums[i] == minValue).findFirst().getAsInt();

    int maxValue = Arrays.stream(nums).max().getAsInt();
    int maxIndex =
        IntStream.range(0, nums.length)
            .filter(i -> nums[i] == maxValue)
            .reduce((x, y) -> y)
            .getAsInt();

    return minIndex + (nums.length - 1 - maxIndex) - ((minIndex > maxIndex) ? 1 : 0);
  }
}
