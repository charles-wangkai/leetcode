import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Solution {
  public String getSmallestString(String s) {
    return Stream.concat(
            Stream.of(s),
            IntStream.range(0, s.length() - 1)
                .filter(i -> s.charAt(i) % 2 == s.charAt(i + 1) % 2)
                .mapToObj(
                    i ->
                        "%s%c%c%s"
                            .formatted(
                                s.substring(0, i),
                                s.charAt(i + 1),
                                s.charAt(i),
                                s.substring(i + 2))))
        .min(Comparator.naturalOrder())
        .get();
  }
}