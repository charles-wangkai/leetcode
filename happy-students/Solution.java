import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public int countWays(List<Integer> nums) {
    Collections.sort(nums);

    return (int)
        IntStream.rangeClosed(0, nums.size())
            .filter(i -> (i == 0 || i > nums.get(i - 1)) && (i == nums.size() || i < nums.get(i)))
            .count();
  }
}
