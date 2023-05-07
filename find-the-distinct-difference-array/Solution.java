import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int[] distinctDifferenceArray(int[] nums) {
    return IntStream.range(0, nums.length)
        .map(
            i ->
                (int) Arrays.stream(nums, 0, i + 1).distinct().count()
                    - (int) Arrays.stream(nums, i + 1, nums.length).distinct().count())
        .toArray();
  }
}
