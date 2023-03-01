import java.util.Arrays;

class Solution {
  public int[] sortArray(int[] nums) {
    return Arrays.stream(nums).sorted().toArray();
  }
}
