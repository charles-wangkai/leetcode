import java.util.Arrays;

class Solution {
  public int countPartitions(int[] nums) {
    return (Arrays.stream(nums).sum() % 2 == 0) ? (nums.length - 1) : 0;
  }
}