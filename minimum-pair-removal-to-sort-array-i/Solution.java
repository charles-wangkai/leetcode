import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int minimumPairRemoval(int[] nums) {
    int result = 0;
    while (true) {
      int[] nums_ = nums;
      if (IntStream.range(0, nums.length - 1).allMatch(i -> nums_[i] <= nums_[i + 1])) {
        break;
      }

      int index =
          IntStream.range(0, nums.length - 1)
              .boxed()
              .min(
                  Comparator.<Integer, Integer>comparing(i -> nums_[i] + nums_[i + 1])
                      .thenComparing(i -> i))
              .get();

      nums =
          IntStream.concat(
                  IntStream.concat(
                      IntStream.range(0, index).map(i -> nums_[i]),
                      IntStream.of(nums[index] + nums[index + 1])),
                  IntStream.range(index + 2, nums.length).map(i -> nums_[i]))
              .toArray();
      ++result;
    }

    return result;
  }
}