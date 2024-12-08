import java.util.Arrays;

class Solution {
  public int minOperations(int[] nums, int k) {
    return Arrays.stream(nums).allMatch(x -> x >= k)
        ? (int) Arrays.stream(nums).filter(x -> x > k).distinct().count()
        : -1;
  }
}