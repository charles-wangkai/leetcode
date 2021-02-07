import java.util.stream.IntStream;

class Solution {
  public boolean check(int[] nums) {
    return IntStream.range(0, nums.length)
            .filter(i -> nums[i] > nums[(i + 1) % nums.length])
            .count()
        <= 1;
  }
}
