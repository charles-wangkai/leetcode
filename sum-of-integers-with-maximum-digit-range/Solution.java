import java.util.Arrays;

class Solution {
  public int maxDigitRange(int[] nums) {
    int maxDigitRange = Arrays.stream(nums).map(this::computeDigitRange).max().getAsInt();

    return Arrays.stream(nums).filter(num -> computeDigitRange(num) == maxDigitRange).sum();
  }

  int computeDigitRange(int x) {
    int[] digits = String.valueOf(x).chars().map(c -> c - '0').toArray();

    return Arrays.stream(digits).max().getAsInt() - Arrays.stream(digits).min().getAsInt();
  }
}