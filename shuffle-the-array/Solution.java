import java.util.stream.IntStream;

class Solution {
  public int[] shuffle(int[] nums, int n) {
    return IntStream.range(0, nums.length)
        .map(i -> nums[i / 2 + ((i % 2 == 0) ? 0 : (nums.length / 2))])
        .toArray();
  }
}
