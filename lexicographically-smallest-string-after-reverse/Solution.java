import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Solution {
  public String lexSmallest(String s) {
    return IntStream.rangeClosed(1, s.length())
        .boxed()
        .flatMap(
            k ->
                Stream.of(
                    new StringBuilder(s.substring(0, k)).reverse().toString() + s.substring(k),
                    s.substring(0, k) + new StringBuilder(s.substring(k)).reverse().toString()))
        .min(Comparator.naturalOrder())
        .get();
  }
}