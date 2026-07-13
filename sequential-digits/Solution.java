import java.util.List;
import java.util.stream.IntStream;

class Solution {
  static final String DIGITS = "123456789";

  public List<Integer> sequentialDigits(int low, int high) {
    return IntStream.range(0, DIGITS.length())
        .flatMap(
            beginIndex ->
                IntStream.range(beginIndex, DIGITS.length())
                    .map(endIndex -> Integer.parseInt(DIGITS.substring(beginIndex, endIndex + 1))))
        .filter(x -> x >= low && x <= high)
        .sorted()
        .boxed()
        .toList();
  }
}
