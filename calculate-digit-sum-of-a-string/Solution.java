import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public String digitSum(String s, int k) {
    while (s.length() > k) {
      String s_ = s;
      s =
          IntStream.iterate(0, i -> i < s_.length(), i -> i + k)
              .map(
                  i ->
                      s_.substring(i, Math.min(s_.length(), i + k)).chars().map(c -> c - '0').sum())
              .mapToObj(String::valueOf)
              .collect(Collectors.joining());
    }

    return s;
  }
}