import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public String replaceDigits(String s) {
    return IntStream.range(0, s.length())
        .mapToObj(
            i ->
                String.valueOf(
                    (i % 2 == 0) ? s.charAt(i) : (char) (s.charAt(i - 1) + s.charAt(i) - '0')))
        .collect(Collectors.joining());
  }
}
