import java.util.stream.IntStream;

class Solution {
  public int[] getConcatenation(int[] nums) {
    return IntStream.range(0, nums.length * 2).map(i -> nums[i % nums.length]).toArray();
  }
}
