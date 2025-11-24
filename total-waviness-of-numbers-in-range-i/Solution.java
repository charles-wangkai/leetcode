import java.util.stream.IntStream;

class Solution {
  public int totalWaviness(int num1, int num2) {
    return IntStream.rangeClosed(num1, num2)
        .map(
            x -> {
              int[] digits = String.valueOf(x).chars().map(c -> c - '0').toArray();

              return (int)
                  IntStream.range(1, digits.length - 1)
                      .filter(
                          i ->
                              Integer.signum(digits[i] - digits[i - 1])
                                      * Integer.signum(digits[i] - digits[i + 1])
                                  > 0)
                      .count();
            })
        .sum();
  }
}