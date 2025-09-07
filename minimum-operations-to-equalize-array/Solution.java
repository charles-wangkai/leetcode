import java.util.Arrays;

class Solution {
  public int minOperations(int[] nums) {
    return (Arrays.stream(nums).distinct().count() == 1) ? 0 : 1;
  }
}