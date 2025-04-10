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
          IntStream.range(0, nums.length)
              .filter(i -> i != index + 1)
              .map(i -> (i == index) ? (nums_[i] + nums_[i + 1]) : nums_[i])
              .toArray();
      ++result;
    }

    return result;
  }
}