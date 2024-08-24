import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Stream;

class Solution {
  public String nearestPalindromic(String n) {
    return String.valueOf(
        Stream.of(
                buildLessLength(n),
                buildMoreLength(n),
                buildFromHalf(n, -1),
                buildFromHalf(n, 0),
                buildFromHalf(n, 1))
            .filter(Objects::nonNull)
            .filter(x -> x != Long.parseLong(n))
            .min(
                Comparator.<Long, Long>comparing(x -> Math.abs(x - Long.parseLong(n)))
                    .thenComparing(x -> x))
            .get());
  }

  Long buildFromHalf(String n, int delta) {
    String half = n.substring(0, (n.length() + 1) / 2);
    String lowerHalf = String.valueOf(Long.parseLong(half) + delta);

    return (lowerHalf.length() == half.length())
        ? Long.parseLong(
            lowerHalf + new StringBuilder(lowerHalf).reverse().toString().substring(n.length() % 2))
        : null;
  }

  Long buildLessLength(String n) {
    return (n.length() == 1) ? null : Long.parseLong("9".repeat(n.length() - 1));
  }

  long buildMoreLength(String n) {
    return Long.parseLong("1" + "0".repeat(n.length() - 1) + "1");
  }
}
