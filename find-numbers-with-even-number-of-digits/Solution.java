import java.util.Arrays;

class Solution {
  public int findNumbers(int[] nums) {
    return (int) Arrays.stream(nums).filter(num -> String.valueOf(num).length() % 2 == 0).count();
  }
}
