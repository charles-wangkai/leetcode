import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {
  public String maxSumOfSquares(int num, int sum) {
    int[] digits = new int[num];
    for (int i = 0; i < digits.length; ++i) {
      digits[i] = Math.min(9, sum);
      sum -= digits[i];
    }

    return (sum == 0)
        ? Arrays.stream(digits).mapToObj(String::valueOf).collect(Collectors.joining())
        : "";
  }
}