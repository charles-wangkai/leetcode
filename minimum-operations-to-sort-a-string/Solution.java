import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int minOperations(String s) {
    if (s.length() == 1) {
      return 0;
    }
    if (s.length() == 2) {
      return (s.charAt(0) <= s.charAt(1)) ? 0 : -1;
    }

    return Math.min(computeOperationNum(s, true), computeOperationNum(s, false));
  }

  int computeOperationNum(String s, boolean leftOrRight) {
    int result = 0;
    while (true) {
      String s_ = s;
      if (IntStream.range(0, s.length() - 1).allMatch(i -> s_.charAt(i) <= s_.charAt(i + 1))) {
        return result;
      }

      if (leftOrRight) {
        s =
            "%s%c"
                .formatted(
                    s.substring(0, s.length() - 1)
                        .chars()
                        .sorted()
                        .mapToObj(c -> (char) c)
                        .map(String::valueOf)
                        .collect(Collectors.joining()),
                    s.charAt(s.length() - 1));
      } else {
        s =
            "%c%s"
                .formatted(
                    s.charAt(0),
                    s.substring(1)
                        .chars()
                        .sorted()
                        .mapToObj(c -> (char) c)
                        .map(String::valueOf)
                        .collect(Collectors.joining()));
      }

      ++result;
      leftOrRight ^= true;
    }
  }
}