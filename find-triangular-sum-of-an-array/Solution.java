import java.util.stream.IntStream;

class Solution {
  public int triangularSum(int[] nums) {
    while (nums.length != 1) {
      int[] nums_ = nums;
      nums = IntStream.range(0, nums.length - 1).map(i -> (nums_[i] + nums_[i + 1]) % 10).toArray();
    }

    return nums[0];
  }
}