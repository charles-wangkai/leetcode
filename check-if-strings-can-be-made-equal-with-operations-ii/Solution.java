import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public boolean checkStrings(String s1, String s2) {
    return buildSorted(s1, 0).equals(buildSorted(s2, 0))
        && buildSorted(s1, 1).equals(buildSorted(s2, 1));
  }

  String buildSorted(String s, int parity) {
    return IntStream.range(0, s.length())
        .filter(i -> i % 2 == parity)
        .mapToObj(s::charAt)
        .sorted()
        .map(String::valueOf)
        .collect(Collectors.joining());
  }
}
