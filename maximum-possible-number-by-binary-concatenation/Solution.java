import java.util.stream.IntStream;

class Solution {
  public int maxGoodNumber(int[] nums) {
    return IntStream.of(
            combine(nums, 0, 1, 2),
            combine(nums, 0, 2, 1),
            combine(nums, 1, 0, 2),
            combine(nums, 1, 2, 0),
            combine(nums, 2, 0, 1),
            combine(nums, 2, 1, 0))
        .max()
        .getAsInt();
  }

  int combine(int[] nums, int index1, int index2, int index3) {
    return Integer.parseInt(
        Integer.toBinaryString(nums[index1])
            + Integer.toBinaryString(nums[index2])
            + Integer.toBinaryString(nums[index3]),
        2);
  }
}