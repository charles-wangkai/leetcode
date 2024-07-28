import java.util.Arrays;

class Solution {
  public boolean canAliceWin(int[] nums) {
    return Arrays.stream(nums).filter(num -> num <= 9).sum()
        != Arrays.stream(nums).filter(num -> num >= 10).sum();
  }
}