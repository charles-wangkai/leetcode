import java.util.Comparator;
import java.util.stream.Collectors;

class Solution {
  public long smallestNumber(long num) {
    String s = String.valueOf(num);

    if (num < 0) {
      return -computeMax(s.substring(1));
    } else if (num > 0) {
      return computeMin(s);
    }

    return 0;
  }

  static long computeMax(String s) {
    return Long.parseLong(
        s.chars()
            .boxed()
            .sorted(Comparator.reverseOrder())
            .map(c -> String.valueOf((char) c.intValue()))
            .collect(Collectors.joining()));
  }

  static long computeMin(String s) {
    int min = s.chars().filter(c -> c != '0').min().getAsInt();
    int index = s.indexOf(min);

    return Long.parseLong(
        String.format(
            "%c%s",
            min,
            (s.substring(0, index) + s.substring(index + 1))
                .chars()
                .sorted()
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining())));
  }
}