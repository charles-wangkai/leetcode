import java.util.Arrays;

class Solution {
  public int lateFee(int[] daysLate) {
    return Arrays.stream(daysLate)
        .map(
            x -> {
              if (x == 1) {
                return 1;
              }
              if (x <= 5) {
                return 2 * x;
              }

              return 3 * x;
            })
        .sum();
  }
}