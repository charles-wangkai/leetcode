import java.util.stream.IntStream;

class Solution {
  public boolean isTrionic(int[] nums) {
    for (int p = 1; p < nums.length; ++p) {
      for (int q = p + 1; q < nums.length - 1; ++q) {
        if (IntStream.range(0, p).allMatch(i -> nums[i] < nums[i + 1])
            && IntStream.range(p, q).allMatch(i -> nums[i] > nums[i + 1])
            && IntStream.range(q, nums.length - 1).allMatch(i -> nums[i] < nums[i + 1])) {
          return true;
        }
      }
    }

    return false;
  }
}