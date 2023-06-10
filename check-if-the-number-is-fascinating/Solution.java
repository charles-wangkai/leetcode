import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public boolean isFascinating(int n) {
    int[] digits =
        IntStream.rangeClosed(1, 3)
            .flatMap(i -> String.valueOf(i * n).chars().map(c -> c - '0'))
            .sorted()
            .toArray();

    return digits[0] == 1 && Arrays.stream(digits).distinct().count() == digits.length;
  }
}
