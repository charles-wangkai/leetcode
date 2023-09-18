import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public int sumIndicesWithKSetBits(List<Integer> nums, int k) {
    return IntStream.range(0, nums.size())
        .filter(i -> Integer.bitCount(i) == k)
        .map(nums::get)
        .sum();
  }
}
