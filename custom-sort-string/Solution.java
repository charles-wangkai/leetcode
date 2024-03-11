import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public String customSortString(String order, String s) {
    Map<Character, Integer> letterToIndex =
        IntStream.range(0, order.length()).boxed().collect(Collectors.toMap(order::charAt, i -> i));

    return s.chars()
        .mapToObj(c -> (char) c)
        .sorted(Comparator.comparing(c -> letterToIndex.getOrDefault(c, Integer.MAX_VALUE)))
        .map(String::valueOf)
        .collect(Collectors.joining());
  }
}
