import java.util.Arrays;

class Solution {
  public int maximizeSum(int[] nums, int k) {
    int max = Arrays.stream(nums).max().getAsInt();

    return (max + max + k - 1) * k / 2;
  }
}
