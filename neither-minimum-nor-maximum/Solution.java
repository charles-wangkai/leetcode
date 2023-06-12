import java.util.Arrays;

class Solution {
  public int findNonMinOrMax(int[] nums) {
    return (nums.length <= 2) ? -1 : Arrays.stream(nums).sorted().skip(1).findFirst().getAsInt();
  }
}
