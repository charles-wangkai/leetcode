import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public boolean reorderedPowerOf2(int n) {
    String key = buildKey(n);

    return IntStream.range(0, 30).anyMatch(i -> buildKey(1 << i).equals(key));
  }

  String buildKey(int x) {
    return String.valueOf(x)
        .chars()
        .sorted()
        .mapToObj(c -> (char) c)
        .map(String::valueOf)
        .collect(Collectors.joining());
  }
}
