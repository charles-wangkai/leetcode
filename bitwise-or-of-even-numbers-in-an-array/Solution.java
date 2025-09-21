import java.util.Arrays;

class Solution {
  public int evenNumberBitwiseORs(int[] nums) {
    return Arrays.stream(nums).filter(x -> x % 2 == 0).reduce((acc, x) -> acc | x).orElse(0);
  }
}