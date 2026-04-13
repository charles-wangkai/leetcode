import java.util.Arrays;

class Solution {
  public int countDigitOccurrences(int[] nums, int digit) {
    return Arrays.stream(nums)
        .map(x -> (int) String.valueOf(x).chars().filter(c -> c - '0' == digit).count())
        .sum();
  }
}