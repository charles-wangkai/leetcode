import java.util.Arrays;

class Solution {
  public int[] separateDigits(int[] nums) {
    return Arrays.stream(nums)
        .flatMap(num -> String.valueOf(num).chars().map(c -> c - '0'))
        .toArray();
  }
}
