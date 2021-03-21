import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public boolean reorderedPowerOf2(int N) {
    return IntStream.range(0, 31).anyMatch(i -> check(N, 1 << i));
  }

  boolean check(int N, int power) {
    return buildKey(N).equals(buildKey(power));
  }

  String buildKey(int x) {
    return String.valueOf(x)
        .chars()
        .sorted()
        .mapToObj(ch -> String.valueOf((char) ch))
        .collect(Collectors.joining());
  }
}
