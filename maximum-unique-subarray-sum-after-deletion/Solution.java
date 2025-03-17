import java.util.Arrays;

class Solution {
  public int maxSum(int[] nums) {
    int max = Arrays.stream(nums).max().getAsInt();
    if (max <= 0) {
      return max;
    }

    return Arrays.stream(nums).filter(x -> x > 0).distinct().sum();
  }
}