import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public boolean canSplitArray(List<Integer> nums, int m) {
    return nums.size() <= 2
        || IntStream.range(0, nums.size() - 1).anyMatch(i -> nums.get(i) + nums.get(i + 1) >= m);
  }
}
