import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public String removeDigit(String number, char digit) {
    return IntStream.range(0, number.length())
        .filter(i -> number.charAt(i) == digit)
        .mapToObj(i -> number.substring(0, i) + number.substring(i + 1))
        .max(Comparator.naturalOrder())
        .get();
  }
}