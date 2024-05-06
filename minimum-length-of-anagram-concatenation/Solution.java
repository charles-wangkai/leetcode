import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int minAnagramLength(String s) {
    for (int unitLength = 1; ; ++unitLength) {
      if (s.length() % unitLength == 0 && check(s, unitLength)) {
        return unitLength;
      }
    }
  }

  boolean check(String s, int unitLength) {
    return IntStream.range(0, s.length() / unitLength)
            .mapToObj(i -> generateKey(s.substring(i * unitLength, (i + 1) * unitLength)))
            .distinct()
            .count()
        == 1;
  }

  String generateKey(String s) {
    return s.chars()
        .sorted()
        .mapToObj(c -> (char) c)
        .map(String::valueOf)
        .collect(Collectors.joining());
  }
}