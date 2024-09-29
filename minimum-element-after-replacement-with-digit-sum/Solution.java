import java.util.Arrays;

class Solution {
  public int minElement(int[] nums) {
    return Arrays.stream(nums)
        .map(x -> String.valueOf(x).chars().map(c -> c - '0').sum())
        .min()
        .getAsInt();
  }
}