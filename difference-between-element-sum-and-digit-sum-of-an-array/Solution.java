import java.util.Arrays;

class Solution {
  public int differenceOfSum(int[] nums) {
    return Math.abs(
        Arrays.stream(nums).sum()
            - Arrays.stream(nums)
                .map(x -> String.valueOf(x).chars().map(c -> c - '0').sum())
                .sum());
  }
}
