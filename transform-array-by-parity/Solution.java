import java.util.Arrays;

class Solution {
  public int[] transformArray(int[] nums) {
    return Arrays.stream(nums).map(x -> x % 2).sorted().toArray();
  }
}