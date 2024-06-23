import java.util.Arrays;

class Solution {
  public int minimumOperations(int[] nums) {
    return (int) Arrays.stream(nums).filter(x -> x % 3 != 0).count();
  }
}