import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int minPairSum(int[] nums) {
    nums = Arrays.stream(nums).boxed().sorted().mapToInt(x -> x).toArray();

    int[] nums_ = nums;
    return IntStream.range(0, nums.length / 2)
        .map(i -> nums_[i] + nums_[nums_.length - 1 - i])
        .max()
        .getAsInt();
  }
}
