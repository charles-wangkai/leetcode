import java.util.stream.IntStream;

class Solution {
  public int alternateDigitSum(int n) {
    String s = String.valueOf(n);

    return IntStream.range(0, s.length())
        .map(i -> ((i % 2 == 0) ? 1 : -1) * (s.charAt(i) - '0'))
        .sum();
  }
}
