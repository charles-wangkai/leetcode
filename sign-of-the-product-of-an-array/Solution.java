import java.util.Arrays;

class Solution {
  public int arraySign(int[] nums) {
    return Arrays.stream(nums).map(Integer::signum).reduce((acc, x) -> acc * x).getAsInt();
  }
}
