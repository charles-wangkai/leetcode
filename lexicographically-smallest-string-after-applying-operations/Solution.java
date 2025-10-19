import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public String findLexSmallestString(String s, int a, int b) {
    Set<String> reaches = new HashSet<>();
    search(reaches, a, b, s);

    return reaches.stream().min(Comparator.naturalOrder()).get();
  }

  void search(Set<String> reaches, int a, int b, String current) {
    if (!reaches.contains(current)) {
      reaches.add(current);

      search(reaches, a, b, add(current, a));
      search(reaches, a, b, rotate(current, b));
    }
  }

  String add(String x, int a) {
    return IntStream.range(0, x.length())
        .map(i -> (x.charAt(i) - '0' + ((i % 2 == 0) ? 0 : a)) % 10)
        .mapToObj(String::valueOf)
        .collect(Collectors.joining());
  }

  String rotate(String x, int b) {
    return IntStream.range(0, x.length())
        .mapToObj(i -> x.charAt(Math.floorMod(i - b, x.length())))
        .map(String::valueOf)
        .collect(Collectors.joining());
  }
}
