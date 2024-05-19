import java.util.stream.IntStream;

class Solution {
  public boolean isArraySpecial(int[] nums) {
    return IntStream.range(0, nums.length - 1).allMatch(i -> nums[i] % 2 != nums[i + 1] % 2);
  }
}