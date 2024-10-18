import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int countMaxOrSubsets(int[] nums) {
    int maxOr = Arrays.stream(nums).reduce((acc, x) -> acc | x).getAsInt();

    return (int)
        IntStream.range(0, 1 << nums.length)
            .filter(
                mask ->
                    IntStream.range(0, nums.length)
                            .filter(i -> ((mask >> i) & 1) == 1)
                            .map(i -> nums[i])
                            .reduce((acc, x) -> acc | x)
                            .orElse(0)
                        == maxOr)
            .count();
  }
}
