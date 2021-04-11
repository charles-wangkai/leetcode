import java.util.Arrays;

class Solution {
  public int arraySign(int[] nums) {
    if (Arrays.stream(nums).anyMatch(num -> num == 0)) {
      return 0;
    }

    return (Arrays.stream(nums).filter(num -> num < 0).count() % 2 == 0) ? 1 : -1;
  }
}
