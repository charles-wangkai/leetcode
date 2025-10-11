import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int makeArrayPositive(int[] nums) {
    for (int i = 0; i < nums.length; ++i) {
      int i_ = i;
      if (IntStream.rangeClosed(3, 5)
          .anyMatch(
              length ->
                  i_ + 1 >= length
                      && IntStream.range(0, length).allMatch(d -> nums[i_ - d] != Integer.MAX_VALUE)
                      && IntStream.range(0, length).map(d -> nums[i_ - d]).asLongStream().sum()
                          <= 0)) {
        nums[i] = Integer.MAX_VALUE;
      }
    }

    return (int) Arrays.stream(nums).filter(x -> x == Integer.MAX_VALUE).count();
  }
}